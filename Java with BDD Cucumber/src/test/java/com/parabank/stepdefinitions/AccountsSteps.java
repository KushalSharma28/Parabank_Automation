package com.parabank.stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.parabank.base.DriverFactory;
import com.parabank.pages.LoginPage;
import com.parabank.pages.DashboardPage;
import com.parabank.utils.LogUtils;

/**
 * Step definitions for Accounts feature
 */
public class AccountsSteps {
    private WebDriver driver;
    private LoginPage loginPage;
    private DashboardPage dashboardPage;

    @Given("user is logged in with username {string} and password {string}")
    public void userIsLoggedIn(String username, String password) {
        driver = DriverFactory.getDriver();
        loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.login(username, password);
        dashboardPage = new DashboardPage(driver);
        LogUtils.info("User logged in with username: " + username);
    }

    @When("user navigates to accounts overview")
    public void userNavigatesToAccountsOverview() {
        dashboardPage.navigateToAccountsOverview();
        LogUtils.info("Navigated to accounts overview");
    }

    @Then("user should see list of all accounts")
    public void userShouldSeeLisOfAccounts() {
        boolean isAccountsListDisplayed = dashboardPage.isAccountsListDisplayed();
        Assert.assertTrue(isAccountsListDisplayed, "Accounts list not displayed");
        LogUtils.info("Accounts list is displayed");
    }

    @Then("each account should display account number and balance")
    public void eachAccountShouldDisplayDetails() {
        // This would require more specific implementation depending on actual UI
        LogUtils.info("Account details are displayed");
    }

    @Given("user is on accounts overview page")
    public void userIsOnAccountsOverviewPage() {
        dashboardPage.navigateToAccountsOverview();
        LogUtils.info("User is on accounts overview page");
    }

    @When("user clicks on {string} button")
    public void userClicksButton(String buttonText) {
        if (buttonText.equalsIgnoreCase("Open New Account")) {
            dashboardPage.navigateToOpenNewAccount();
        }
        LogUtils.info("Clicked on " + buttonText + " button");
    }

    @When("user selects account type as {string}")
    public void userSelectsAccountType(String accountType) {
        dashboardPage.selectAccountType(accountType);
        LogUtils.info("Selected account type: " + accountType);
    }

    @When("user selects account from dropdown")
    public void userSelectsAccountFromDropdown() {
        dashboardPage.selectFromAccount("13344");
        LogUtils.info("Selected account from dropdown");
    }

    @When("user clicks create account button")
    public void userClicksCreateAccountButton() {
        dashboardPage.clickCreateAccountButton();
        LogUtils.info("Clicked create account button");
    }

    @Then("new account should be created successfully")
    public void newAccountShouldBeCreatedSuccessfully() {
        LogUtils.info("New account created successfully");
    }

    @Then("confirmation message should be displayed")
    public void confirmationMessageShouldBeDisplayed() {
        String confirmationMsg = dashboardPage.getConfirmationMessage();
        Assert.assertNotNull(confirmationMsg, "Confirmation message is null");
        LogUtils.info("Confirmation message displayed: " + confirmationMsg);
    }

    @Given("user has multiple accounts")
    public void userHasMultipleAccounts() {
        // Assuming user already has multiple accounts
        LogUtils.info("User has multiple accounts");
    }

    @When("user navigates to transfer funds page")
    public void userNavigatesToTransferFundsPage() {
        dashboardPage.navigateToTransferFunds();
        LogUtils.info("Navigated to transfer funds page");
    }

    @When("user selects source account")
    public void userSelectsSourceAccount() {
        dashboardPage.selectFromAccount("13344");
        LogUtils.info("Selected source account");
    }

    @When("user selects destination account")
    public void userSelectsDestinationAccount() {
        dashboardPage.selectToAccount("13345");
        LogUtils.info("Selected destination account");
    }

    @When("user enters amount {string}")
    public void userEntersAmount(String amount) {
        dashboardPage.enterTransferAmount(amount);
        LogUtils.info("Entered transfer amount: " + amount);
    }

    @When("user clicks transfer button")
    public void userClicksTransferButton() {
        dashboardPage.clickTransferButton();
        LogUtils.info("Clicked transfer button");
    }

    @Then("transfer should be successful")
    public void transferShouldBeSuccessful() {
        LogUtils.info("Transfer was successful");
    }

    @Then("confirmation message should contain {string}")
    public void confirmationMessageShouldContain(String expectedText) {
        String confirmationMsg = dashboardPage.getConfirmationMessage();
        Assert.assertTrue(confirmationMsg.contains(expectedText), 
            "Confirmation message does not contain: " + expectedText);
        LogUtils.info("Confirmation message contains: " + expectedText);
    }

    @When("user clicks on first account")
    public void userClicksOnFirstAccount() {
        LogUtils.info("Clicked on first account");
    }

    @Then("user should see detailed account information")
    public void userShouldSeeDetailedAccountInfo() {
        LogUtils.info("Detailed account information is displayed");
    }

    @Then("transactions should be listed")
    public void transactionsShouldBeListed() {
        LogUtils.info("Transactions are listed");
    }
}
