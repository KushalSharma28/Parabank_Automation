Feature: ParaBank Login Functionality
  As a user
  I want to login to ParaBank application
  So that I can access my bank account

  Background:
    Given user is on ParaBank login page

  Scenario: Successful login with valid credentials
    When user enters username "john"
    And user enters password "demo"
    And user clicks login button
    Then user should be navigated to dashboard page
    And user should see welcome message "Welcome john"

  Scenario: Login fails with invalid credentials
    When user enters username "invalid_user"
    And user enters password "invalid_pass"
    And user clicks login button
    Then error message should be displayed "The username and password could not be verified."

  Scenario: Login with empty username
    When user leaves username empty
    And user enters password "demo"
    And user clicks login button
    Then error message should be displayed

  Scenario: Login with empty password
    When user enters username "john"
    And user leaves password empty
    And user clicks login button
    Then error message should be displayed
