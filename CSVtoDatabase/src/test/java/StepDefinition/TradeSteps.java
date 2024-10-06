package StepDefinition;

import Pages.TradeCsvGenerator;
import Pages.TradeDataTransporter;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class TradeSteps {
    @Given("I have a CSV file with trade data")
    public void i_have_a_csv_file_with_trade_data() {
        TradeCsvGenerator.generateCsv("trades.csv");
    }

    @When("I transport the data to the database")
    public void i_transport_the_data_to_the_database() {
        TradeDataTransporter.transportToDatabase("trades.csv");
    }

    @Then("the trades should be stored in the database")
    public void the_trades_should_be_stored_in_the_database() {
        // You can validate data in DB using JDBC queries
        System.out.println("Validation step - Check DB entries");
    }
}
