package com.parabank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.parabank.base.BaseClass;

/**
 * Login Page Object Model
 */
public class LoginPage extends BaseClass {
    // Locators
    private By usernameField = By.name("customer.username");
    private By passwordField = By.name("customer.password");
    private By loginButton = By.xpath("//input[@value='Log In']");
    private By errorMessage = By.cssSelector("p.error");
    private By welcomeMessage = By.cssSelector("h2");

    public LoginPage(WebDriver driver) {
        this.setDriver(driver);
    }

    /**
     * Navigate to login page
     */
    public void navigateToLoginPage() {
        navigateToUrl("https://www.parabank.example.com/parabank/index.htm");
    }

    /**
     * Enter username
     *
     * @param username Username to enter
     */
    public void enterUsername(String username) {
        typeText(usernameField, username);
    }

    /**
     * Enter password
     *
     * @param password Password to enter
     */
    public void enterPassword(String password) {
        typeText(passwordField, password);
    }

    /**
     * Click login button
     */
    public void clickLoginButton() {
        clickElement(loginButton);
    }

    /**
     * Get error message
     *
     * @return Error message text
     */
    public String getErrorMessage() {
        return getText(errorMessage);
    }

    /**
     * Check if error message is displayed
     *
     * @return true if error is displayed
     */
    public boolean isErrorMessageDisplayed() {
        return isElementDisplayed(errorMessage);
    }

    /**
     * Get welcome message
     *
     * @return Welcome message text
     */
    public String getWelcomeMessage() {
        return getText(welcomeMessage);
    }

    /**
     * Perform login
     *
     * @param username Username
     * @param password Password
     */
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }
}
