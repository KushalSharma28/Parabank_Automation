Feature: ParaBank Account Operations
  As a registered user
  I want to perform various account operations
  So that I can manage my bank accounts

  Background:
    Given user is logged in with username "john" and password "demo"

  Scenario: View accounts overview
    When user navigates to accounts overview
    Then user should see list of all accounts
    And each account should display account number and balance

  Scenario: Open new account
    Given user is on accounts overview page
    When user clicks on "Open New Account" button
    And user selects account type as "Checking"
    And user selects account from dropdown
    And user clicks create account button
    Then new account should be created successfully
    And confirmation message should be displayed

  Scenario: Transfer funds between accounts
    Given user has multiple accounts
    When user navigates to transfer funds page
    And user selects source account
    And user selects destination account
    And user enters amount "500"
    And user clicks transfer button
    Then transfer should be successful
    And confirmation message should contain "Transfer Complete"

  Scenario: View account details
    Given user is on accounts overview page
    When user clicks on first account
    Then user should see detailed account information
    And transactions should be listed
