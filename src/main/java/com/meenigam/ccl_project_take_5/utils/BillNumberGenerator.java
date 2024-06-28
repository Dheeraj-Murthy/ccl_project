package com.meenigam.ccl_project_take_5.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;

public class BillNumberGenerator {

    private final Connection connection;

    public BillNumberGenerator(Connection connection) {
        this.connection = connection;
    }

    public String generateBillNumber() throws SQLException {
        int currentYear = Year.now().getValue();
        String yearString = String.valueOf(currentYear);

        String query = "SELECT MAX(bill_number) FROM table_of_application WHERE bill_number LIKE ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, "CPRMS_" + yearString + "_%");
            ResultSet resultSet = preparedStatement.executeQuery();

            String lastBillNumber = null;
            if (resultSet.next()) {
                lastBillNumber = resultSet.getString(1);
            }

            int newNumber = 1;
            if (lastBillNumber != null) {
                String[] parts = lastBillNumber.split("_");
                newNumber = Integer.parseInt(parts[2]) + 1;
            }

            return String.format("CPRMS_%s_%06d", yearString, newNumber);
        }
    }
}