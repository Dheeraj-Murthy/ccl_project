package com.meenigam.ccl_project_take_5.database_interaction;


import com.meenigam.ccl_project_take_5.utils.EmailValidator;

import java.awt.*;
import java.sql.*;

enum Relation_to_employee {
    SELF, SPOUSE, CHILD1, CHILD2
}

enum Disease_type {
    critical, non_critical
}

enum Treatment_type {
    OPD, IPD
}

enum User_type {
    super_user, observation_desk, pharmacist, accountant, CMO, CMS, data_entry_operator
}

enum application_status {
    REGISTERED, PHARMACY, ACCOUNTS, CMO, CMS, data_entry_operator, APPROVED
}

public class DBManagement {

    static String url = "jdbc:mysql://localhost:3306/";

    static String databaseName = "ccl_employee_database";
    static String userName = "root";
    static String password = "dheerujaanu";

    static String table = "table_of_application";
    static String history = "history";

    private static void add_entry_into_application_table(String employee_name, String employee_id, String patient_name,
                                                         Relation_to_employee relation_to_employee, String patient_UHID, Disease_type disease_type,
                                                         Treatment_type treatment_type,
                                                         String phone_number) throws Exception {
        try {

            Connection connection = DriverManager.getConnection(url, userName, password);

            String useDB = "USE " + databaseName;

            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(useDB);
            }

            // String[] employeeNames = { "Dheeraj", "Aman", "Robert", "Mathew" };
            // String[] employeeIds = { "E45678", "N12345", "E09876", "N56789" };
            // String[] patientNames = { "Dheeraj", "Adashu", "Gill", "Joseph" };
            // String[] relationToEmployee = { "SELF", "CHILD1", "SPOUSE", "CHILD2" };
            String[] arr = {employee_name, employee_id, patient_name, relation_to_employee.toString(), patient_UHID,
                    disease_type.toString(), treatment_type.toString(), phone_number};

            String makeEntries = "INSERT INTO " + table
                    + " (employee_name, employee_id, patient_name, relation_to_employee, patient_uhid, disease_type, treatment_type, phone_number, application_status) "
                    + " VALUES ";

            String values = "(";
            for (int j = 0; j < 8; j++) {
                values += "'" + arr[j] + "', ";
            }
            // values = values.substring(0, values.length() - 2);
            values += "'" + application_status.PHARMACY.toString() + "'";
            values += ')';
            makeEntries += values;

            makeEntries += ";";
            System.out.println(makeEntries);

            Statement statement = connection.createStatement();
            statement.executeUpdate(makeEntries);

//            JOptionPane.showMessageDialog(null, databaseName + " Database has been changed successfully",
//                    "System Message", JOptionPane.INFORMATION_MESSAGE);

            ResultSet rs = null;
            Statement query = connection.createStatement();
            String q = "SELECT application_id FROM table_of_application ORDER BY application_id DESC LIMIT 1;";
            rs = query.executeQuery(q);
            String applicationId = null;
            if (rs.next()) {
                applicationId = rs.getString(1);
                System.out.println(applicationId);
            }

            String command2 = "INSERT INTO " + history + "(application_id, Processed_by, Date) VALUES ('" + applicationId + "' , 'reception' , NOW() );";
            connection.createStatement().executeUpdate(command2);

        } catch (HeadlessException | SQLException e) {
            e.printStackTrace();
            System.out.println("error: " + e);
        }
    }

    @SuppressWarnings("unused")
    private static void add_entry_into_user_table(String UserName, String user_password, User_type user_type, String employeeID, String emailID)
            throws Exception {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, userName, password);

            String useDB = "USE " + databaseName;

            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(useDB);
            }

            // String[] UserNames = { "Dheeraj", "Aman", "Robert", "Mathew" };
            // String[] employeeIds = { "E45678", "N12345", "E09876", "N56789" };
            // String[] UserType = { "super_user", "pharmacist", "accountant", "CMO" };
            String[] arr = {UserName, user_password, user_type.toString(), employeeID, emailID};

            String userTable = "user_table";
            String makeEntries = "INSERT INTO " + userTable
                    + " (User_name, Password, User_type, employee_id, email_id) " + " VALUES ";

            StringBuilder values = new StringBuilder("(");
            for (int j = 0; j < 5; j++) {
                values.append("'").append(arr[j]).append("', ");
            }
            values = new StringBuilder(values.substring(0, values.length() - 2));
            values.append(')');
            makeEntries += values;

            makeEntries += ";";
            System.out.println(makeEntries);

            Statement statement = connection.createStatement();
            statement.executeUpdate(makeEntries);

//            JOptionPane.showMessageDialog(null, databaseName + " Database has been changed successfully",
//                    "System Message", JOptionPane.INFORMATION_MESSAGE);

        } catch (HeadlessException | SQLException e) {
            e.printStackTrace();
            System.out.println("error: " + e);
            throw new Exception(e);
        }
    }

    @SuppressWarnings("unused")
    private static void update_by_observation_desk(String application_id, Boolean is_approved) throws Exception {
        Connection connection = DriverManager.getConnection(url, userName, password);
        String useDB = "USE " + databaseName;
        connection.createStatement().executeUpdate(useDB);
        // UPDATE table_name SET column1 = value1, column2 = value2, ... where employee
        // ID = 6
        String command;
        if (is_approved) {
            command = "UPDATE " + table
                    + " SET application_status = 'PHARMACY'  WHERE application_id = "
                    + application_id;
            connection.createStatement().executeUpdate(command);
            String command2 = "INSERT INTO " + history + "(application_id, Processed_by, Date, verdict) VALUES ('"
                    + application_id + "' , 'observation_desk' , NOW(), 'approved');";
            connection.createStatement().executeUpdate(command2);
        } else {
            command = "DELETE FROM " + table
                    + " WHERE application_id = "
                    + application_id;
            connection.createStatement().executeUpdate(command);
            String command2 = "INSERT INTO " + history + "(application_id, Processed_by, Date, verdict) VALUES ('"
                    + application_id + "' , 'observation_desk' , NOW(), 'rejected');";
            connection.createStatement().executeUpdate(command2);
        }

    }


    @SuppressWarnings("unused")
    private static void update_by_pharmacist(String application_id, Boolean is_approved) throws Exception {
        Connection connection = DriverManager.getConnection(url, userName, password);
        String useDB = "USE " + databaseName;
        connection.createStatement().executeUpdate(useDB);
        // UPDATE table_name SET column1 = value1, column2 = value2, ... where employee
        // ID = 6
        String command;
        if (is_approved) {
            command = "UPDATE " + table
                    + " SET application_status = 'ACCOUNTS'  WHERE application_id = "
                    + application_id;
            connection.createStatement().executeUpdate(command);
            String command2 = "INSERT INTO " + history + "(application_id, Processed_by, Date, verdict) VALUES ('"
                    + application_id + "' , 'pharmacist' , NOW(), 'approved');";
            connection.createStatement().executeUpdate(command2);
        } else {
            command = "UPDATE " + table
                    + " SET application_status = 'REGISTERED'  WHERE application_id = "
                    + application_id;
            connection.createStatement().executeUpdate(command);
            String command2 = "INSERT INTO " + history + "(application_id, Processed_by, Date, verdict) VALUES ('"
                    + application_id + "' , 'pharmacist' , NOW(), 'rejected');";
            connection.createStatement().executeUpdate(command2);
        }

    }

    @SuppressWarnings("unused")
    private static void update_by_accountant(String application_id, Boolean is_approved) throws Exception {
        Connection connection = DriverManager.getConnection(url, userName, password);
        String useDB = "USE " + databaseName;
        connection.createStatement().executeUpdate(useDB);
        // UPDATE table_name SET column1 = value1, column2 = value2, ... where employee
        // ID = 6
        String command;
        if (is_approved) {
            command = "UPDATE " + table + " SET application_status = 'CMO'  WHERE application_id = "
                    + application_id;
            connection.createStatement().executeUpdate(command);
            String command2 = "INSERT INTO " + history + "(application_id, Processed_by, Date, verdict) VALUES ('"
                    + application_id + "' , 'accountant' , NOW(), 'approved');";
            connection.createStatement().executeUpdate(command2);
        } else {
            command = "UPDATE " + table + " SET application_status = 'REGISTERED'  WHERE application_id = "
                    + application_id;
            connection.createStatement().executeUpdate(command);
            String command2 = "INSERT INTO " + history + "(application_id, Processed_by, Date, verdict) VALUES ('"
                    + application_id + "' , 'accountant' , NOW(), 'rejected');";
            connection.createStatement().executeUpdate(command2);
        }

    }

    @SuppressWarnings("unused")
    private static void update_by_CMO(String application_id, Boolean is_approved) throws Exception {
        Connection connection = DriverManager.getConnection(url, userName, password);
        String useDB = "USE " + databaseName;
        connection.createStatement().executeUpdate(useDB);
        // UPDATE table_name SET column1 = value1, column2 = value2, ... where employee
        // ID = 6
        String command;
        if (is_approved) {
            command = "UPDATE " + table + " SET application_status = 'CMS'  WHERE application_id = "
                    + application_id;
            connection.createStatement().executeUpdate(command);
            String command2 = "INSERT INTO " + history + "(application_id, Processed_by, Date, verdict) VALUES ('"
                    + application_id + "' , 'CMO' , NOW(), 'approved');";
            connection.createStatement().executeUpdate(command2);
        } else {
            command = "UPDATE " + table + " SET application_status = 'REGISTERED'  WHERE application_id = "
                    + application_id;
            connection.createStatement().executeUpdate(command);
            String command2 = "INSERT INTO " + history + "(application_id, Processed_by, Date, verdict) VALUES ('"
                    + application_id + "' , 'CMO' , NOW(), 'rejected');";
            connection.createStatement().executeUpdate(command2);
        }

    }

    @SuppressWarnings("unused")
    private static void update_by_CMS(String application_id, Boolean is_approved) throws Exception {
        Connection connection = DriverManager.getConnection(url, userName, password);
        String useDB = "USE " + databaseName;
        connection.createStatement().executeUpdate(useDB);
        // UPDATE table_name SET column1 = value1, column2 = value2, ... where employee
        // ID = 6
        String command;
        if (is_approved) {
            command = "UPDATE " + table
                    + " SET application_status = 'data_entry_operator'  WHERE application_id = "
                    + application_id;
            connection.createStatement().executeUpdate(command);
            String command2 = "INSERT INTO " + history + "(application_id, Processed_by, Date, verdict) VALUES ('"
                    + application_id + "' , 'CMS' , NOW(), 'approved');";
            connection.createStatement().executeUpdate(command2);
        } else {
            command = "UPDATE " + table
                    + " SET application_status = 'REGISTERED'  WHERE application_id = "
                    + application_id;
            connection.createStatement().executeUpdate(command);
            String command2 = "INSERT INTO " + history + "(application_id, Processed_by, Date, verdict) VALUES ('"
                    + application_id + "' , 'CMS' , NOW(), 'rejected');";
            connection.createStatement().executeUpdate(command2);
        }

    }

    @SuppressWarnings("unused")
    private static void update_by_data_entry_operator(String application_id, Boolean is_approved) throws Exception {
        Connection connection = DriverManager.getConnection(url, userName, password);
        String useDB = "USE " + databaseName;
        connection.createStatement().executeUpdate(useDB);
        // UPDATE table_name SET column1 = value1, column2 = value2, ... where employee
        // ID = 6
        String command;
        if (is_approved) {
            command = "UPDATE " + table
                    + " SET application_status = 'APPROVED'  WHERE application_id = "
                    + application_id;
            connection.createStatement().executeUpdate(command);
            String command2 = "INSERT INTO " + history + "(application_id, Processed_by, Date, verdict) VALUES ('"
                    + application_id + "' , 'data_entry_operator' , NOW(), 'approved');";
            connection.createStatement().executeUpdate(command2);
        } else {
            command = "UPDATE " + table
                    + " SET application_status = 'REGISTERED'  WHERE application_id = "
                    + application_id;
            connection.createStatement().executeUpdate(command);
            String command2 = "INSERT INTO " + history + "(application_id, Processed_by, Date, verdict) VALUES ('"
                    + application_id + "' , 'data_entry_operator' , NOW(), 'rejected');";
            connection.createStatement().executeUpdate(command2);
        }

    }

    public static void add_user(String UserName, String user_password, String user_type, String employeeID, String emailID) throws Exception {
        if (UserName == null || user_password == null || user_type == null || employeeID == null || emailID == null) {
            System.out.println("user_type or employee_id or email_id or something is null");
            throw new RuntimeException("user_type or employee_id or email_id or something is null");
        } else if (!EmailValidator.isValid(emailID)) {
            System.out.println("email_id is invalid");
            throw new RuntimeException("email_id is invalid");
        } else {
            User_type t = null;
            for (User_type type : User_type.values()) {
                if (user_type.equals(type.toString())) t = type;
            }
            if (t == null) {
                System.out.println("user_type is not valid");
                throw new RuntimeException("user_type is not valid");
            } else {
                try {
                    add_entry_into_user_table(UserName, user_password, t, employeeID, emailID);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new Exception(e);
                }
            }
        }
    }

    public static String[] retrieve_user(String email_or_username, String user_password) throws Exception {
        Connection connection = DriverManager.getConnection(url, userName, password);
        String useDB = "USE " + databaseName;
        connection.createStatement().executeUpdate(useDB);

        boolean isUserName = EmailValidator.isValid(email_or_username);

        ResultSet rs = null;
        Statement query = connection.createStatement();
        String q;
        if (isUserName) {
            q = "SELECT User_id, Password, User_type FROM user_table WHERE email_id = '" + email_or_username + "';";
        } else {
            q = "SELECT User_id, Password, User_type FROM user_table WHERE User_name = '" + email_or_username + "';";
        }
        rs = query.executeQuery(q);
        String UserId = null;
        if (rs.next()) {
            UserId = rs.getString(1);
            String pass = rs.getString(2);
            String type = rs.getString(3);
            if (pass.equals(user_password)) {
                System.out.println("User_id is " + UserId);
                System.out.println("user_password is " + pass);
                System.out.println("type is " + type);
                return new String[]{UserId, type};
            }
        } else {
            System.out.println("User_id is null");
            throw new RuntimeException("User not found");
        }
        return null;
    }

    public static ResultSet retrieve_rows(String usertype) throws Exception {
        Connection connection = DriverManager.getConnection(url, userName, password);
        String useDB = "USE " + databaseName;

        connection.createStatement().executeUpdate(useDB);

        String query = "SELECT * FROM table_of_application WHERE application_status = '" + usertype + "';";
        ResultSet rs = connection.createStatement().executeQuery(query);
        return rs;
    }

    public static ResultSet get_row(String id) throws Exception {
        Connection connection = DriverManager.getConnection(url, userName, password);
        String useDB = "USE " + databaseName;

        connection.createStatement().executeUpdate(useDB);

        String query = "SELECT * FROM table_of_application WHERE application_id = '" + id + "';";
        ResultSet rs = connection.createStatement().executeQuery(query);
        return rs;
    }

    public static void create_application(String employee_name, String employee_id, String patient_name,
                                          String relation_to_employee, String patient_UHID, String disease_type,
                                          String treatment_type,
                                          String phone_number) throws Exception {
        Relation_to_employee r = null;
        for (Relation_to_employee rel : Relation_to_employee.values()) {
            if (rel.toString().equals(relation_to_employee)) {
                r = rel;
            }
        }
        if (r == null) {
            throw new Exception("relation to employee is invalid: " + relation_to_employee);
        }

        Disease_type d = null;
        for (Disease_type dis : Disease_type.values()) {
            if (dis.toString().equals(disease_type)) {
                d = dis;
            }
        }
        if (d == null) {
            throw new Exception("disease type is invalid: " + disease_type);
        }

        Treatment_type t = null;
        for (Treatment_type tre : Treatment_type.values()) {
            if (tre.toString().equals(treatment_type)) {
                t = tre;
            }
        }
        if (t == null) {
            throw new Exception("treatment type is invalid: " + treatment_type);
        }
        add_entry_into_application_table(employee_name, employee_id, patient_name, r, patient_UHID, d, t, phone_number);
    }

    public static void approve (String user_type, String application_id) throws Exception {
        if (User_type.observation_desk.toString().equals(user_type)) {
            update_by_observation_desk(application_id, true);
        } else if (User_type.pharmacist.toString().equals(user_type)) {
            update_by_pharmacist(application_id, true);
        } else if (User_type.accountant.toString().equals(user_type)) {
            update_by_accountant(application_id,true);
        } else if (User_type.CMO.toString().equals(user_type)) {
            update_by_CMO(application_id,true);
        } else if (User_type.CMS.toString().equals(user_type)) {
            update_by_CMS(application_id,true);
        } else if (User_type.data_entry_operator.toString().equals(user_type)) {
            update_by_data_entry_operator(application_id,true);
        } else {
            throw new Exception("user_type is not valid");
        }
    }

    public static void reject (String user_type, String application_id) throws Exception {
        if (User_type.observation_desk.toString().equals(user_type)) {
            update_by_observation_desk(application_id, false);
        } else if (User_type.pharmacist.toString().equals(user_type)) {
            update_by_pharmacist(application_id, false);
        } else if (User_type.accountant.toString().equals(user_type)) {
            update_by_accountant(application_id,false);
        } else if (User_type.CMO.toString().equals(user_type)) {
            update_by_CMO(application_id,false);
        } else if (User_type.CMS.toString().equals(user_type)) {
            update_by_CMS(application_id,false);
        } else if (User_type.data_entry_operator.toString().equals(user_type)) {
            update_by_data_entry_operator(application_id,false);
        } else {
            throw new Exception("user_type is not valid");
        }
    }

    public static void main(String[] args) {
        try {
//            add_entry_into_application_table("Adarsh", "N000000", "Siddharth",
//                    relation_to_employee.SPOUSE, "7777777777",
//                    disease_type.non_critical, treatment_type.OPD, "999999999");
            update_by_pharmacist("19", false);
//             update_by_accountant("14", true);
            // update_by_CMO("1", true);
//             update_by_CMS("9", true);
            // update_by_data_entry_operator("1", true);

//            add_entry_into_user_table("Dheeraj", "helloworld", User_type.super_user, "tallahasse", "dcompany2004@gmail.com");

        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }


}

