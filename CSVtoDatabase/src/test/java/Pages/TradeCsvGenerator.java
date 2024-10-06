package Pages;

import java.io.FileWriter;
import java.io.IOException;

public class TradeCsvGenerator {
    public static void generateCsv(String filePath) {
        String[] header = {"OrderID", "Quantity", "Price", "Timestamp"};
        String[] trade1 = {"T001", "100", "1500", "2024-09-29 17:30:00"};
        String[] trade2 = {"T002", "200", "3200", "2024-09-29 17:45:00"};

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.append(String.join(",", header)).append("\n");
            writer.append(String.join(",", trade1)).append("\n");
            writer.append(String.join(",", trade2)).append("\n");
            System.out.println("CSV file generated successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        generateCsv("trades.csv");  // Generates trades.csv file
    }
}

