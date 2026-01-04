Feature: ParaBank Registration
  As a new user
  I want to register for ParaBank
  So that I can access the banking services

  Scenario: Successful registration with valid details
    Given user is on ParaBank registration page
    When user fills first name "John"
    And user fills last name "Doe"
    And user fills address "123 Main Street"
    And user fills city "New York"
    And user fills state "NY"
    And user fills zip code "10001"
    And user fills phone number "2125551234"
    And user fills SSN "123456789"
    And user fills username "johndoe123"
    And user fills password "SecurePass123"
    And user confirms password "SecurePass123"
    And user clicks register button
    Then registration should be successful
    And user should see "Your login information was created successfully"

  Scenario: Registration fails with mismatched passwords
    Given user is on ParaBank registration page
    When user fills registration form with valid data
    And user fills password "SecurePass123"
    And user confirms password "DifferentPass123"
    And user clicks register button
    Then error message should be displayed "Passwords do not match"

  Scenario: Registration fails when required fields are empty
    Given user is on ParaBank registration page
    When user clicks register button without filling any field
    Then error messages should be displayed for required fields
