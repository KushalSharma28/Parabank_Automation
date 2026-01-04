package com.parabank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.parabank.base.BaseClass;

/**
 * Registration Page Object Model
 */
public class RegistrationPage extends BaseClass {
    // Locators
    private By firstNameField = By.id("customer.firstName");
    private By lastNameField = By.id("customer.lastName");
    private By addressField = By.id("customer.address.street");
    private By cityField = By.id("customer.address.city");
    private By stateField = By.id("customer.address.state");
    private By zipCodeField = By.id("customer.address.zipCode");
    private By phoneField = By.id("customer.phoneNumber");
    private By ssnField = By.id("customer.ssn");
    private By usernameField = By.id("customer.username");
    private By passwordField = By.id("customer.password");
    private By confirmPasswordField = By.id("customer.repeatPassword");
    private By registerButton = By.xpath("//input[@value='Register']");
    private By successMessage = By.cssSelector("p");
    private By errorMessages = By.cssSelector("span.error");

    public RegistrationPage(WebDriver driver) {
        this.setDriver(driver);
    }

    /**
     * Navigate to registration page
     */
    public void navigateToRegistrationPage() {
        navigateToUrl("https://www.parabank.example.com/parabank/register.htm");
    }

    /**
     * Enter first name
     */
    public void enterFirstName(String firstName) {
        typeText(firstNameField, firstName);
    }

    /**
     * Enter last name
     */
    public void enterLastName(String lastName) {
        typeText(lastNameField, lastName);
    }

    /**
     * Enter address
     */
    public void enterAddress(String address) {
        typeText(addressField, address);
    }

    /**
     * Enter city
     */
    public void enterCity(String city) {
        typeText(cityField, city);
    }

    /**
     * Enter state
     */
    public void enterState(String state) {
        typeText(stateField, state);
    }

    /**
     * Enter zip code
     */
    public void enterZipCode(String zipCode) {
        typeText(zipCodeField, zipCode);
    }

    /**
     * Enter phone number
     */
    public void enterPhoneNumber(String phone) {
        typeText(phoneField, phone);
    }

    /**
     * Enter SSN
     */
    public void enterSSN(String ssn) {
        typeText(ssnField, ssn);
    }

    /**
     * Enter username
     */
    public void enterUsername(String username) {
        typeText(usernameField, username);
    }

    /**
     * Enter password
     */
    public void enterPassword(String password) {
        typeText(passwordField, password);
    }

    /**
     * Confirm password
     */
    public void confirmPassword(String password) {
        typeText(confirmPasswordField, password);
    }

    /**
     * Click register button
     */
    public void clickRegisterButton() {
        clickElement(registerButton);
    }

    /**
     * Get success message
     */
    public String getSuccessMessage() {
        return getText(successMessage);
    }

    /**
     * Check if success message is displayed
     */
    public boolean isSuccessMessageDisplayed() {
        return isElementDisplayed(successMessage);
    }

    /**
     * Check if error message is displayed
     */
    public boolean isErrorMessageDisplayed() {
        return isElementDisplayed(errorMessages);
    }
}
