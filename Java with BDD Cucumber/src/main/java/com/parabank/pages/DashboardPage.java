package com.parabank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.parabank.base.BaseClass;

/**
 * Dashboard/Accounts Page Object Model
 */
public class DashboardPage extends BaseClass {
    // Locators
    private By accountsOverviewLink = By.xpath("//a[contains(text(), 'Accounts Overview')]");
    private By openNewAccountLink = By.xpath("//a[contains(text(), 'Open New Account')]");
    private By transferFundsLink = By.xpath("//a[contains(text(), 'Transfer Funds')]");
    private By accountsList = By.xpath("//table[@class='accounts']/tbody/tr");
    private By logoutLink = By.xpath("//a[contains(text(), 'Log Out')]");
    private By welcomeText = By.cssSelector("h2");
    private By accountTypeDropdown = By.id("type");
    private By fromAccountDropdown = By.id("fromAccountId");
    private By toAccountDropdown = By.id("toAccountId");
    private By transferAmountField = By.id("amount");
    private By transferButton = By.xpath("//input[@value='Transfer']");
    private By createAccountButton = By.xpath("//input[@value='Open New Account']");
    private By confirmationMessage = By.cssSelector("div#rightPanel p");

    public DashboardPage(WebDriver driver) {
        this.setDriver(driver);
    }

    /**
     * Navigate to accounts overview
     */
    public void navigateToAccountsOverview() {
        clickElement(accountsOverviewLink);
    }

    /**
     * Navigate to open new account page
     */
    public void navigateToOpenNewAccount() {
        clickElement(openNewAccountLink);
    }

    /**
     * Navigate to transfer funds page
     */
    public void navigateToTransferFunds() {
        clickElement(transferFundsLink);
    }

    /**
     * Select account type
     */
    public void selectAccountType(String type) {
        selectByVisibleText(accountTypeDropdown, type);
    }

    /**
     * Select from account
     */
    public void selectFromAccount(String accountId) {
        selectByValue(fromAccountDropdown, accountId);
    }

    /**
     * Select to account
     */
    public void selectToAccount(String accountId) {
        selectByValue(toAccountDropdown, accountId);
    }

    /**
     * Enter transfer amount
     */
    public void enterTransferAmount(String amount) {
        typeText(transferAmountField, amount);
    }

    /**
     * Click transfer button
     */
    public void clickTransferButton() {
        clickElement(transferButton);
    }

    /**
     * Click create account button
     */
    public void clickCreateAccountButton() {
        clickElement(createAccountButton);
    }

    /**
     * Get confirmation message
     */
    public String getConfirmationMessage() {
        return getText(confirmationMessage);
    }

    /**
     * Get welcome message
     */
    public String getWelcomeMessage() {
        return getText(welcomeText);
    }

    /**
     * Check if accounts list is displayed
     */
    public boolean isAccountsListDisplayed() {
        return isElementDisplayed(accountsList);
    }

    /**
     * Logout
     */
    public void logout() {
        clickElement(logoutLink);
    }
}
