package com.parabank.stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.parabank.base.DriverFactory;
import com.parabank.pages.RegistrationPage;
import com.parabank.utils.LogUtils;

/**
 * Step definitions for Registration feature
 */
public class RegistrationSteps {
    private WebDriver driver;
    private RegistrationPage registrationPage;

    @Given("user is on ParaBank registration page")
    public void userIsOnRegistrationPage() {
        driver = DriverFactory.getDriver();
        registrationPage = new RegistrationPage(driver);
        registrationPage.navigateToRegistrationPage();
        LogUtils.info("User navigated to registration page");
    }

    @When("user fills first name {string}")
    public void userFillsFirstName(String firstName) {
        registrationPage.enterFirstName(firstName);
        LogUtils.info("Entered first name: " + firstName);
    }

    @When("user fills last name {string}")
    public void userFillsLastName(String lastName) {
        registrationPage.enterLastName(lastName);
        LogUtils.info("Entered last name: " + lastName);
    }

    @When("user fills address {string}")
    public void userFillsAddress(String address) {
        registrationPage.enterAddress(address);
        LogUtils.info("Entered address: " + address);
    }

    @When("user fills city {string}")
    public void userFillsCity(String city) {
        registrationPage.enterCity(city);
        LogUtils.info("Entered city: " + city);
    }

    @When("user fills state {string}")
    public void userFillsState(String state) {
        registrationPage.enterState(state);
        LogUtils.info("Entered state: " + state);
    }

    @When("user fills zip code {string}")
    public void userFillsZipCode(String zipCode) {
        registrationPage.enterZipCode(zipCode);
        LogUtils.info("Entered zip code: " + zipCode);
    }

    @When("user fills phone number {string}")
    public void userFillsPhoneNumber(String phone) {
        registrationPage.enterPhoneNumber(phone);
        LogUtils.info("Entered phone number: " + phone);
    }

    @When("user fills SSN {string}")
    public void userFillsSSN(String ssn) {
        registrationPage.enterSSN(ssn);
        LogUtils.info("Entered SSN");
    }

    @When("user fills username {string}")
    public void userFillsUsername(String username) {
        registrationPage.enterUsername(username);
        LogUtils.info("Entered username: " + username);
    }

    @When("user fills password {string}")
    public void userFillsPassword(String password) {
        registrationPage.enterPassword(password);
        LogUtils.info("Entered password");
    }

    @When("user confirms password {string}")
    public void userConfirmsPassword(String password) {
        registrationPage.confirmPassword(password);
        LogUtils.info("Confirmed password");
    }

    @When("user clicks register button")
    public void userClicksRegisterButton() {
        registrationPage.clickRegisterButton();
        LogUtils.info("Clicked register button");
    }

    @Then("registration should be successful")
    public void registrationShouldBeSuccessful() {
        boolean isSuccessMessageDisplayed = registrationPage.isSuccessMessageDisplayed();
        Assert.assertTrue(isSuccessMessageDisplayed, "Registration success message not displayed");
        LogUtils.info("Registration was successful");
    }

    @Then("user should see {string}")
    public void userShouldSee(String expectedMessage) {
        String actualMessage = registrationPage.getSuccessMessage();
        Assert.assertTrue(actualMessage.contains(expectedMessage), 
            "Expected message not found. Expected: " + expectedMessage + ", Actual: " + actualMessage);
        LogUtils.info("Message verified: " + actualMessage);
    }

    @Then("error message should be displayed {string}")
    public void errorMessageShouldBeDisplayed(String expectedError) {
        boolean isErrorDisplayed = registrationPage.isErrorMessageDisplayed();
        Assert.assertTrue(isErrorDisplayed, "Error message not displayed. Expected: " + expectedError);
        LogUtils.info("Error message displayed as expected");
    }

    @When("user fills registration form with valid data")
    public void userFillsRegistrationFormWithValidData() {
        registrationPage.enterFirstName("John");
        registrationPage.enterLastName("Doe");
        registrationPage.enterAddress("123 Main Street");
        registrationPage.enterCity("New York");
        registrationPage.enterState("NY");
        registrationPage.enterZipCode("10001");
        registrationPage.enterPhoneNumber("2125551234");
        registrationPage.enterSSN("123456789");
        registrationPage.enterUsername("johndoe");
        LogUtils.info("Filled registration form with valid data");
    }

    @When("user clicks register button without filling any field")
    public void userClicksRegisterWithoutFillingForm() {
        registrationPage.clickRegisterButton();
        LogUtils.info("Clicked register button without filling form");
    }

    @Then("error messages should be displayed for required fields")
    public void errorMessagesDisplayedForRequiredFields() {
        boolean isErrorDisplayed = registrationPage.isErrorMessageDisplayed();
        Assert.assertTrue(isErrorDisplayed, "Error messages not displayed for required fields");
        LogUtils.info("Error messages displayed for required fields");
    }
}
