Feature: ParaBank Dashboard Functionality
  As a logged-in customer
  I want to access dashboard features
  So that I can manage my accounts

  Scenario: Navigate to Accounts Overview
    Given I am logged in to ParaBank
    When I click on Accounts Overview link
    Then I should navigate to the accounts overview page
    And The page URL should contain "overview"

  Scenario: Navigate to Transfer Funds
    Given I am logged in to ParaBank
    When I click on Transfer Funds link
    Then I should navigate to the transfer funds page
    And The page URL should contain "transfer"

  Scenario: Navigate to Request Loan
    Given I am logged in to ParaBank
    When I click on Request Loan link
    Then I should navigate to the loan request page
    And The page URL should contain "loan"

  Scenario: Logout from Dashboard
    Given I am logged in to ParaBank
    When I click on the logout link
    Then I should return to the login page
    And The login page should be displayed
