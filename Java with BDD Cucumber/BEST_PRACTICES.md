# Best Practices & Standards Guide

## ✅ Test Automation Best Practices

### 1. Page Object Model (POM)

**✓ DO:**
```java
// Good: All interactions through page class
public class LoginPage extends BaseClass {
    private By usernameField = By.name("username");
    private By passwordField = By.name("password");
    private By loginButton = By.xpath("//button[@id='login']");
    
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }
}
```

**✗ DON'T:**
```java
// Bad: Direct locators in test/step
driver.findElement(By.name("username")).sendKeys("user");
driver.findElement(By.name("password")).sendKeys("pass");
driver.findElement(By.xpath("//button[@id='login']")).click();
```

### 2. Explicit Waits Over Implicit

**✓ DO:**
```java
protected WebElement findElement(By locator) {
    return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
}

protected void clickElement(By locator) {
    WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
    element.click();
}
```

**✗ DON'T:**
```java
// Bad: Implicit waits are unreliable
driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
driver.findElement(By.id("element")).click(); // Might fail unexpectedly
```

### 3. Proper Locator Strategies

**✓ DO (in order of preference):**
```java
// 1. ID (unique, fast)
private By element = By.id("unique-id");

// 2. Name (usually unique)
private By element = By.name("fieldname");

// 3. CSS Selector (fast)
private By element = By.cssSelector("button.login-btn");

// 4. XPath (as last resort)
private By element = By.xpath("//button[@type='submit']");
```

**✗ DON'T:**
```java
// Bad: Brittle XPaths
private By element = By.xpath("/html/body/div[2]/div[3]/div[1]/button[5]");

// Bad: Overly complex selectors
private By element = By.xpath("//*[contains(text(), 'Login') and @class='btn']");

// Bad: Hardcoded text
private By element = By.xpath("//*[text()='Click Me']");
```

### 4. Test Data Management

**✓ DO:**
```java
// Use externalized test data
String testUsername = ConfigReader.getProperty("testUsername"); // "john"
String testPassword = ConfigReader.getProperty("testPassword");  // "demo"

// Generate dynamic data when needed
String randomEmail = TestDataUtils.generateRandomEmail();
String randomPhoneNumber = TestDataUtils.generateRandomPhoneNumber();
```

**✗ DON'T:**
```java
// Bad: Hardcoded test data
String username = "john";
String password = "demo";
String email = "test@test.com"; // What if email changes?
```

### 5. Meaningful Assertions

**✓ DO:**
```java
// Clear, specific assertions
Assert.assertTrue(dashboardPage.isWelcomeMessageDisplayed(), 
    "Welcome message should be displayed after login");

Assert.assertEquals(actualPageTitle, expectedPageTitle, 
    "Page title should match after navigation");

Assert.assertTrue(actualError.contains("username"), 
    "Error message should mention username field");
```

**✗ DON'T:**
```java
// Bad: Vague assertions
Assert.assertTrue(isDisplayed()); // What is displayed?
Assert.assertEquals(actual, expected); // No message context
assertTrue(result); // What result? Why should it be true?
```

### 6. Error Handling & Logging

**✓ DO:**
```java
public void login(String username, String password) {
    try {
        LogUtils.info("Attempting login with username: " + username);
        enterUsername(username);
        LogUtils.debug("Username entered");
        enterPassword(password);
        LogUtils.debug("Password entered");
        clickLoginButton();
        LogUtils.info("Login successful");
    } catch (TimeoutException e) {
        LogUtils.error("Login failed - element not found: " + e.getMessage());
        throw e;
    } catch (Exception e) {
        LogUtils.error("Unexpected error during login", e);
        throw e;
    }
}
```

**✗ DON'T:**
```java
// Bad: No logging or error info
public void login(String username, String password) {
    enterUsername(username);
    enterPassword(password);
    clickLoginButton();
}

// Bad: Swallowing exceptions
try {
    // Code
} catch (Exception e) {
    // Silent failure
}
```

### 7. Code Organization & Structure

**✓ DO:**
```
src/
├── main/java/com/parabank/
│   ├── base/          # Base classes
│   ├── config/        # Configuration
│   ├── pages/         # Page Objects
│   └── utils/         # Utilities
└── test/java/com/parabank/
    ├── hooks/         # Setup/Teardown
    ├── runners/       # Test Runners
    ├── stepdefinitions/ # Step Implementations
    └── resources/features/ # Feature Files
```

**✗ DON'T:**
```
// Bad: Everything in one place
src/
├── TestLogin.java
├── TestRegistration.java
├── TestAccounts.java
├── Utils.java
└── Helpers.java
```

### 8. DRY Principle (Don't Repeat Yourself)

**✓ DO:**
```java
// BaseClass with common methods
protected void clickElement(By locator) { }
protected void typeText(By locator, String text) { }
protected String getText(By locator) { }
protected boolean isElementDisplayed(By locator) { }

// Page classes extend BaseClass
public class LoginPage extends BaseClass {
    public void enterUsername(String username) {
        typeText(usernameField, username); // Reuse common method
    }
}
```

**✗ DON'T:**
```java
// Bad: Duplicate methods in every page
public class LoginPage {
    public void clickLogin() {
        driver.findElement(loginButton).click();
    }
}
public class RegistrationPage {
    public void clickRegister() {
        driver.findElement(registerButton).click(); // Duplicate!
    }
}
```

### 9. Naming Conventions

**✓ DO:**
```java
// Clear, descriptive names
private By userNameInputField;
private By submitLoginButton;
private By errorMessageAlert;

public void navigateToLoginPage();
public void loginWithValidCredentials();
public boolean isWelcomeMessageDisplayed();
public String getErrorMessageText();

// Feature scenarios with business language
Scenario: User successfully logs in with valid credentials
Scenario: Login fails when password is incorrect
Scenario: Required fields show validation errors
```

**✗ DON'T:**
```java
// Bad: Vague names
private By field1;
private By btn;
private By msg;

public void test1();
public void doLogin();
public boolean check();

// Bad: Unclear scenarios
Scenario: Test 1
Scenario: Something happens
Scenario: User does action
```

### 10. Configuration Management

**✓ DO:**
```properties
# config.properties - All configuration in one place
browser=chrome
baseUrl=https://www.parabank.example.com/parabank/
implicitWait=10
explicitWait=20
headlessMode=false
takeScreenshot=true
```

**✗ DON'T:**
```java
// Bad: Hardcoded values scattered throughout code
public class LoginTest {
    private String baseUrl = "https://...";
    private int wait = 10;
    private boolean headless = false;
}

public class AccountTest {
    private String baseUrl = "https://..."; // Duplicate!
    private int wait = 10; // Duplicate!
}
```

---

## 🎯 Cucumber BDD Best Practices

### 1. Feature File Standards

**✓ DO:**
```gherkin
Feature: ParaBank Login Functionality
  As a user
  I want to login to ParaBank
  So that I can access my account

  Background:
    Given user is on login page

  Scenario: Successful login with valid credentials
    When user enters username "john"
    And user enters password "demo"
    And user clicks login button
    Then user should be navigated to dashboard
    And welcome message should be displayed
```

**✗ DON'T:**
```gherkin
# Bad: Unclear description
Feature: Test Login
  
  # Bad: No background
  Scenario: Login Test 1
    Given user navigates to login page
    When user does login
    Then something happens
    
  # Bad: Multiple assertions in one step
  Scenario: Test 2
    When user logs in and navigates and updates profile
    Then everything should work
```

### 2. Step Definition Standards

**✓ DO:**
```java
// Clear, business-friendly step definitions
@Given("user is on login page")
public void userIsOnLoginPage() {
    loginPage.navigateToLoginPage();
}

@When("user enters username {string}")
public void userEntersUsername(String username) {
    loginPage.enterUsername(username);
}

@Then("user should be navigated to dashboard")
public void userShouldBeNavigatedToDashboard() {
    Assert.assertTrue(dashboardPage.isDashboardDisplayed());
}
```

**✗ DON'T:**
```java
// Bad: Generic implementations
@When("user does something")
public void userDoesSomething() {
    // What does this do?
}

// Bad: Multiple actions in one step
@When("user logs in and navigates and searches")
public void userDoesMultipleThings(String text) {
    // Too much logic
}

// Bad: Magic numbers
@Given("user waits for {int} seconds")
public void userWaits(int seconds) {
    Thread.sleep(seconds * 1000); // Hardcoded!
}
```

### 3. Test Data in Scenarios

**✓ DO:**
```gherkin
Scenario Outline: Login with different credentials
  When user enters username "<username>"
  And user enters password "<password>"
  Then login should "<result>"
  
  Examples:
    | username | password | result    |
    | john     | demo     | succeed   |
    | invalid  | wrong    | fail      |
    | blank    | blank    | fail      |
```

**✗ DON'T:**
```gherkin
# Bad: One scenario per credential combo
Scenario: Login with john/demo
  When user enters username "john"
  And user enters password "demo"
  Then login should succeed

Scenario: Login with invalid/wrong
  When user enters username "invalid"
  And user enters password "wrong"
  Then login should fail
```

---

## 🏗️ Code Quality Standards

### 1. Method Length

**✓ DO:**
```java
// Small, focused methods
public void completeLogin(String username, String password) {
    enterCredentials(username, password);
    submitLogin();
    verifyLoginSuccess();
}

private void enterCredentials(String username, String password) {
    enterUsername(username);
    enterPassword(password);
}

private void submitLogin() {
    clickLoginButton();
}

private void verifyLoginSuccess() {
    Assert.assertTrue(isDashboardDisplayed());
}
```

**✗ DON'T:**
```java
// Bad: Long methods doing too much
public void test() {
    driver.findElement(By.id("username")).sendKeys("john");
    driver.findElement(By.id("password")).sendKeys("demo");
    driver.findElement(By.xpath("//button")).click();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.elementToBeClickable(By.id("dashboard")));
    // ... 50 more lines
}
```

### 2. Code Comments

**✓ DO:**
```java
/**
 * Login with provided credentials
 * 
 * @param username Username to login
 * @param password Password to login
 * @throws LoginException if login fails
 */
public void login(String username, String password) {
    enterUsername(username);
    enterPassword(password);
    clickLoginButton();
}

// Complex logic deserves explanation
private WebElement waitForElement(By locator) {
    // Using explicit wait with ElementToBeClickable
    // ensures element is both visible and clickable
    return wait.until(ExpectedConditions.elementToBeClickable(locator));
}
```

**✗ DON'T:**
```java
// Bad: No comments for complex code
public void test() {
    // set username
    typeText(By.name("customer.username"), "john");
    // click button
    clickElement(By.xpath("//input[@value='Log In']"));
    // check result
    Assert.assertTrue(isElementDisplayed(By.cssSelector("h2")));
}
```

### 3. Exception Handling

**✓ DO:**
```java
public void login(String username, String password) {
    try {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    } catch (TimeoutException e) {
        LogUtils.error("Login element not found within timeout");
        ScreenshotUtils.captureScreenshot(driver, "login_timeout");
        throw new LoginException("Login timeout: " + e.getMessage(), e);
    } catch (Exception e) {
        LogUtils.error("Unexpected error during login", e);
        throw e;
    }
}
```

**✗ DON'T:**
```java
// Bad: Swallowing exceptions
public void login(String username, String password) {
    try {
        // Code
    } catch (Exception e) {
        // Silently fail
    }
}

// Bad: Generic exception handling
public void login(String username, String password) {
    try {
        // Code
    } catch (Exception e) {
        System.out.println("Error"); // What error?
    }
}
```

---

## 📚 Documentation Standards

**✓ DO:**
```markdown
## Project Overview
Brief project description

## Prerequisites
List of requirements

## Installation
Step-by-step setup

## Running Tests
Clear execution instructions

## Troubleshooting
Common issues and solutions
```

**✗ DON'T:**
```markdown
# MyProject
Some stuff

Setup:
1. Do things
2. More things

Run tests

Done
```

---

## 🚀 Performance Best Practices

### 1. Optimize Test Execution

**✓ DO:**
```bash
# Run tests in parallel for faster feedback
mvn test -Dthread-count=4

# Run only smoke tests for quick validation
mvn test -Dcucumber.filter.tags="@smoke"

# Skip slow operations when possible
mvn test -Dcucumber.filter.tags="not @slow"
```

### 2. Efficient Waits

**✓ DO:**
```java
// Wait only as long as necessary
protected void waitForElement(By locator) {
    wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    // Don't wait longer than needed
}

// Use appropriate wait conditions
wait.until(ExpectedConditions.visibilityOfElementLocated(locator)); // For visibility
wait.until(ExpectedConditions.elementToBeClickable(locator));       // For clicks
wait.until(ExpectedConditions.presenceOfElementLocated(locator));  // For presence
```

### 3. Resource Management

**✓ DO:**
```java
// Properly close resources in Hooks
@After
public void tearDown() {
    LogUtils.info("Closing resources");
    DriverFactory.quitDriver();
    // Clear memory
    ScenarioContext.clearScenarioContext();
}
```

---

## 🔒 Security Best Practices

**✓ DO:**
```properties
# Use environment variables for sensitive data
# NEVER hardcode credentials
testUsername=${TEST_USERNAME}
testPassword=${TEST_PASSWORD}
```

**✗ DON'T:**
```properties
# Bad: Hardcoded credentials
testUsername=john
testPassword=demo123
databasePassword=MySecurePassword!
```

---

## 📈 Continuous Improvement

### Regular Reviews
- [ ] Code review every merge
- [ ] Test coverage analysis monthly
- [ ] Performance benchmarking quarterly
- [ ] Security audit annually

### Metrics to Track
- Test execution time
- Pass rate percentage
- Flaky test count
- Code coverage percentage
- Bug detection rate

---

## ✨ Summary of Key Principles

1. **Page Object Model** - Encapsulate page interactions
2. **DRY** - Don't repeat yourself
3. **SOLID** - Single responsibility, Open/closed, Liskov, Interface, Dependency
4. **Clear Naming** - Code speaks for itself
5. **Explicit Waits** - Reliable, not brittle
6. **Logging** - Track everything important
7. **Error Handling** - Fail gracefully
8. **Documentation** - Keep it updated
9. **Maintainability** - Future-proof your code
10. **Performance** - Optimize where it matters

---

**Follow these practices for:**
- ✅ Maintainable code
- ✅ Reliable tests
- ✅ Fast execution
- ✅ Easy onboarding
- ✅ Reduced maintenance cost
