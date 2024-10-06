package Pages;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class TradeDataTransporter {
    public static void transportToDatabase(String csvFilePath) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/trading";
        String username = "divyabam";
        String password = "divya12345$";

        // Try-with-resources for managing resources
        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
             BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {

            String line;
            String sql = "INSERT INTO trades (OrderID, Quantity, Price, Timestamp) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {

                // Skip the header row
                br.readLine(); // Skip the first line if it contains column names

                while ((line = br.readLine()) != null) {
                    String[] values = line.split(",");

                    // Check if values array has the expected number of columns
                    if (values.length < 4) {
                        System.out.println("Invalid line (not enough columns): " + line);
                        continue; // Skip this line if it doesn't have enough columns
                    }

                    try {
                        stmt.setString(1, values[0].trim());  // OrderID
                        stmt.setInt(2, Integer.parseInt(values[1].trim()));  // Quantity
                        stmt.setDouble(3, Double.parseDouble(values[2].trim()));  // Price
                        stmt.setString(4, values[3].trim());  // Timestamp
                        stmt.executeUpdate();
                    } catch (NumberFormatException e) {
                        System.out.println("Error parsing numbers in line: " + line + " - " + e.getMessage());
                    }
                }

                System.out.println("Data transported to database successfully!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        transportToDatabase("trades.csv");  // Transports the CSV data to database
    }
}
