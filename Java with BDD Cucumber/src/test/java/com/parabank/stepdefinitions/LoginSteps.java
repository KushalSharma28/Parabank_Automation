package com.parabank.stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.parabank.base.DriverFactory;
import com.parabank.pages.LoginPage;
import com.parabank.pages.DashboardPage;
import com.parabank.utils.LogUtils;

/**
 * Step definitions for Login feature
 */
public class LoginSteps {
    private WebDriver driver;
    private LoginPage loginPage;
    private DashboardPage dashboardPage;

    @Given("user is on ParaBank login page")
    public void userIsOnLoginPage() {
        driver = DriverFactory.getDriver();
        loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        LogUtils.info("User navigated to login page");
    }

    @When("user enters username {string}")
    public void userEntersUsername(String username) {
        loginPage.enterUsername(username);
        LogUtils.info("Entered username: " + username);
    }

    @When("user enters password {string}")
    public void userEntersPassword(String password) {
        loginPage.enterPassword(password);
        LogUtils.info("Entered password");
    }

    @When("user clicks login button")
    public void userClicksLoginButton() {
        loginPage.clickLoginButton();
        LogUtils.info("Clicked login button");
    }

    @Then("user should be navigated to dashboard page")
    public void userShouldBeNavigatedToDashboard() {
        dashboardPage = new DashboardPage(driver);
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("overview"), 
            "User not navigated to dashboard. Current URL: " + currentUrl);
        LogUtils.info("User successfully navigated to dashboard");
    }

    @Then("user should see welcome message {string}")
    public void userShouldSeeWelcomeMessage(String expectedMessage) {
        dashboardPage = new DashboardPage(driver);
        String actualMessage = dashboardPage.getWelcomeMessage();
        Assert.assertTrue(actualMessage.contains(expectedMessage), 
            "Welcome message not found. Expected: " + expectedMessage + ", Actual: " + actualMessage);
        LogUtils.info("Welcome message verified: " + actualMessage);
    }

    @Then("error message should be displayed {string}")
    public void errorMessageShouldBeDisplayed(String expectedError) {
        boolean isErrorDisplayed = loginPage.isErrorMessageDisplayed();
        Assert.assertTrue(isErrorDisplayed, "Error message not displayed");
        String actualError = loginPage.getErrorMessage();
        Assert.assertTrue(actualError.contains(expectedError), 
            "Expected error: " + expectedError + ", Actual: " + actualError);
        LogUtils.info("Error message verified: " + actualError);
    }

    @Then("error message should be displayed")
    public void errorMessageShouldBeDisplayedGeneric() {
        boolean isErrorDisplayed = loginPage.isErrorMessageDisplayed();
        Assert.assertTrue(isErrorDisplayed, "Error message not displayed");
        LogUtils.info("Error message is displayed as expected");
    }

    @When("user leaves username empty")
    public void userLeavesUsernameEmpty() {
        loginPage.enterUsername("");
        LogUtils.info("Username field left empty");
    }

    @When("user leaves password empty")
    public void userLeavesPasswordEmpty() {
        loginPage.enterPassword("");
        LogUtils.info("Password field left empty");
    }
}
