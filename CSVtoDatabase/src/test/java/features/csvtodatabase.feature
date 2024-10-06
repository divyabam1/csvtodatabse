Feature: Trade Submission
  Scenario: Submitting trades from CSV file to the database
    Given I have a CSV file with trade data
    When I transport the data to the database
    Then the trades should be stored in the database
