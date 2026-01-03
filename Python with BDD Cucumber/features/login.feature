Feature: ParaBank Login Functionality
  As a ParaBank customer
  I want to login to my account
  So that I can access my banking features

  Scenario: Successful login with valid credentials
    Given I am on the ParaBank login page
    When I enter username "john" and password "demo"
    And I click the login button
    Then I should see the dashboard page
    And I should see the welcome message

  Scenario: Login with invalid credentials
    Given I am on the ParaBank login page
    When I enter username "invalid" and password "invalid"
    And I click the login button
    Then I should see an error message

  Scenario: Login with empty username
    Given I am on the ParaBank login page
    When I leave username empty
    And I enter password "demo"
    And I click the login button
    Then I should remain on the login page

  Scenario Outline: Login with different credentials
    Given I am on the ParaBank login page
    When I enter username "<username>" and password "<password>"
    And I click the login button
    Then Login attempt is made with <username>

    Examples:
      | username | password |
      | john     | demo     |
      | testuser | test123  |
