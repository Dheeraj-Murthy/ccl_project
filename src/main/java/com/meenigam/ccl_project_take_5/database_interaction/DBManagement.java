package com.meenigam.ccl_project_take_5.database_interaction;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

enum relation_to_employee {
    SELF, SPOUSE, CHILD1, CHILD2
}

enum disease_type {
    critical, non_critical
}

enum treatment_type {
    OPD, IPD
}

enum User_type {
    super_user, pharmacist, accountant, CMO, CMS, data_entry_operator
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
                                                         relation_to_employee relation_to_employee, String patient_UHID, disease_type disease_type,
                                                         treatment_type treatment_type,
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
            String[] arr = { employee_name, employee_id, patient_name, relation_to_employee.toString(), patient_UHID,
                    disease_type.toString(), treatment_type.toString(), phone_number };

            String makeEntries = "INSERT INTO " + table
                    + " (employee_name, employee_id, patient_name, relation_to_employee, patient_uhid, disease_type, treatment_type, phone_number, application_status, observation_desk) "
                    + " VALUES ";

            String values = "(";
            for (int j = 0; j < 8; j++) {
                values += "'" + arr[j] + "', ";
            }
            // values = values.substring(0, values.length() - 2);
            values += "'" + application_status.PHARMACY.toString() + "'";
            values += ", NOW()";
            values += ')';
            makeEntries += values;

            makeEntries += ";";
            System.out.println(makeEntries);

            Statement statement = connection.createStatement();
            statement.executeUpdate(makeEntries);

            JOptionPane.showMessageDialog(null, databaseName + " Database has been changed successfully",
                    "System Message", JOptionPane.INFORMATION_MESSAGE);

            // Statement query = connection.createStatement();

            // ;

            // String command2 = "INSERT INTO " + history + "(application_id, Processed_by,
            // Date) VALUES ('"
            // + application_id + "' , 'pharmacist' , NOW() );";
            // connection.createStatement().executeUpdate(command2);

        } catch (HeadlessException | SQLException e) {
            e.printStackTrace();
            System.out.println("error: " + e);
        }
    }

    @SuppressWarnings("unused")
    private static void add_entry_into_user_table(String UserName, String password, User_type user_type)
            throws Exception {
        try {

            // Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, userName, password);

            String useDB = "USE " + databaseName;

            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(useDB);
            }

            // String[] UserNames = { "Dheeraj", "Aman", "Robert", "Mathew" };
            // String[] employeeIds = { "E45678", "N12345", "E09876", "N56789" };
            // String[] UserType = { "super_user", "pharmacist", "accountant", "CMO" };
            String[] arr = { UserName, password, user_type.toString() };

            String userTable = "user_table";
            String makeEntries = "INSERT INTO " + userTable
                    + " (User_name, Password, User_type) " + " VALUES ";

            String values = "(";
            for (int j = 0; j < 3; j++) {
                values += "'" + arr[j] + "', ";
            }
            values = values.substring(0, values.length() - 2);
            values += ')';
            makeEntries += values;

            makeEntries += ";";
            System.out.println(makeEntries);

            Statement statement = connection.createStatement();
            statement.executeUpdate(makeEntries);

            JOptionPane.showMessageDialog(null, databaseName + " Database has been changed successfully",
                    "System Message", JOptionPane.INFORMATION_MESSAGE);

        } catch (HeadlessException | SQLException e) {
            // e.printStackTrace();
            System.out.println("error: " + e);
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
            String command2 = "INSERT INTO " + history + "(application_id, Processed_by, Date) VALUES ('"
                    + application_id + "' , 'pharmacist' , NOW() );";
            connection.createStatement().executeUpdate(command2);
        } else
            ;
        // todo //

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
            String command2 = "INSERT INTO " + history + "(application_id, Processed_by, Date) VALUES ('"
                    + application_id + "' , 'accountant' , NOW() );";
            connection.createStatement().executeUpdate(command2);
        } else
            ;
        // todo //

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
            String command2 = "INSERT INTO " + history + "(application_id, Processed_by, Date) VALUES ('"
                    + application_id + "' , 'CMO' , NOW() );";
            connection.createStatement().executeUpdate(command2);
        } else
            ;
        // todo //

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
            String command2 = "INSERT INTO " + history + "(application_id, Processed_by, Date) VALUES ('"
                    + application_id + "' , 'CMS' , NOW() );";
            connection.createStatement().executeUpdate(command2);
        } else
            ;
        // todo //

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
            String command2 = "INSERT INTO " + history + "(application_id, Processed_by, Date) VALUES ('"
                    + application_id + "' , 'data_entry_operator' , NOW() );";
            connection.createStatement().executeUpdate(command2);
        } else
            ;
        // todo //

    }

    public static void main(String[] args) {
        try {
            add_entry_into_application_table("Adarsh", "N000000", "Siddharth",
                    relation_to_employee.SPOUSE, "7777777777",
                    disease_type.non_critical, treatment_type.OPD, "999999999");
            // update_by_pharmacist("4", true);
            // update_by_accountant("4", true);
            // update_by_CMO("1", true);
            // update_by_CMS("1", true);
            // update_by_data_entry_operator("1", true);

        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }

}

