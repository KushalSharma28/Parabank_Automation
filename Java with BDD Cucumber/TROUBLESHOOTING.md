# Troubleshooting Guide

## 🆘 Common Issues & Solutions

### Issue 1: Java Version Mismatch

**Error Message:**
```
ERROR: JAVA_HOME is not set and no 'java' command could be found
```

**Solution:**
```bash
# Check Java version
java -version

# Should be Java 11 or higher
# If not installed, download from: https://adoptopenjdk.net/

# Set JAVA_HOME
# Linux/Mac:
export JAVA_HOME=/path/to/java/installation
# Windows:
set JAVA_HOME=C:\Program Files\Java\jdk-11.0.x
```

**Verify:**
```bash
mvn -v  # Should show Java 11+
```

---

### Issue 2: Maven Not Found

**Error Message:**
```
'mvn' is not recognized as an internal or external command
```

**Solution:**
```bash
# Install Maven from: https://maven.apache.org/

# Add Maven to PATH
# Linux/Mac:
export PATH=$PATH:/path/to/maven/bin
# Windows:
set PATH=%PATH%;C:\path\to\maven\bin

# Verify installation
mvn --version
```

---

### Issue 3: Dependencies Not Downloaded

**Error Message:**
```
[ERROR] Failed to read artifact descriptor for org.seleniumhq.selenium:selenium-java:jar:4.15.0
```

**Solution:**
```bash
# Clear Maven cache
mvn clean dependency:purge-local-repository

# Reinstall dependencies
mvn clean install -U

# Check internet connection
# Maven needs internet for first download
```

---

### Issue 4: WebDriver Not Found

**Error Message:**
```
WebDriverManager unable to read chromedriver binary file
```

**Solution:**
```bash
# Clear WebDriverManager cache
mvn clean

# Let WebDriverManager download drivers
mvn test -X

# Or manually download from:
# Chrome: https://chromedriver.chromium.org/
# Firefox: https://github.com/mozilla/geckodriver/releases
# Edge: https://developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/
```

---

### Issue 5: Port Already in Use

**Error Message:**
```
Address already in use
```

**Solution:**
```bash
# Linux/Mac: Kill process on port
lsof -i :8080
kill -9 <PID>

# Windows: Kill process on port
netstat -ano | findstr :8080
taskkill /PID <PID> /F

# Or use different port
mvn test -Dport=8081
```

---

### Issue 6: Tests Timeout

**Error Message:**
```
Timed out waiting for page load
```

**Solution:**

Edit `src/main/resources/config.properties`:
```properties
# Increase wait times
explicitWait=30
pageLoadTimeout=40
driverTimeout=50
```

Or in code:
```java
// Increase wait in BaseClass.java
private static final long TIMEOUT = 30; // Changed from 10
```

---

### Issue 7: Element Not Found

**Error Message:**
```
NoSuchElementException: no such element: Unable to locate element
```

**Solution:**

1. **Verify Locator:**
   ```bash
   # Open browser developer tools (F12)
   # Right-click element → Inspect
   # Copy XPath or CSS selector
   ```

2. **Update Page Class:**
   ```java
   // In LoginPage.java or other page class
   private By usernameField = By.name("customer.username");
   // Update to match actual website
   ```

3. **Check Wait Times:**
   ```java
   protected WebElement findElement(By locator) {
       return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
   }
   ```

4. **Increase Timeout:**
   ```properties
   # In config.properties
   explicitWait=20
   ```

---

### Issue 8: Tests Pass Locally but Fail in CI/CD

**Cause:** Environment differences (headless mode, resolution, etc.)

**Solution:**

Enable headless mode in `config.properties`:
```properties
headlessMode=true
windowWidth=1920
windowHeight=1080
```

Or set in code:
```java
// In WebDriverManager.java
options.addArguments("--headless");
options.addArguments("--window-size=1920,1080");
```

---

### Issue 9: Screenshot/Log Files Permission Denied

**Error Message:**
```
Permission denied creating directory/file
```

**Solution:**
```bash
# Linux/Mac: Add write permissions
chmod -R 755 logs/
chmod -R 755 target/

# Windows: Run as Administrator
# Right-click Command Prompt → Run as administrator
```

---

### Issue 10: Flaky Tests (Intermittent Failures)

**Cause:** Timing issues, network latency, page load variations

**Solution:**

1. **Increase Wait Times:**
   ```properties
   explicitWait=20
   pageLoadTimeout=40
   ```

2. **Use Explicit Waits:**
   ```java
   wait.until(ExpectedConditions.elementToBeClickable(element));
   ```

3. **Add Retry Logic:**
   ```java
   @Retry(maxAttempts = 3)
   public void myTest() {
       // Test code
   }
   ```

4. **Fix Race Conditions:**
   ```java
   // Wait for element to be visible before interaction
   waitForElementVisibility(locator);
   clickElement(locator);
   ```

---

### Issue 11: Memory Out of Bounds

**Error Message:**
```
java.lang.OutOfMemoryError: Java heap space
```

**Solution:**
```bash
# Increase Maven memory
export MAVEN_OPTS=-Xmx1024m  # Linux/Mac
set MAVEN_OPTS=-Xmx1024m     # Windows

# Or set higher
MAVEN_OPTS=-Xmx2048m mvn test
```

---

### Issue 12: Feature File Not Recognized

**Error Message:**
```
No scenarios found in feature file
```

**Solution:**

1. **Check File Location:**
   - Must be in: `src/test/resources/features/`
   - File extension: `.feature`

2. **Verify Feature Syntax:**
   ```gherkin
   Feature: Test Feature     # Required
     Scenario: Test Case     # Required (not Scenario:)
       Given step 1
       When step 2
       Then step 3
   ```

3. **Check Runner Configuration:**
   ```java
   @CucumberOptions(
       features = "src/test/resources/features",
       glue = {"com.parabank.stepdefinitions", "com.parabank.hooks"}
   )
   ```

---

### Issue 13: Step Definition Not Found

**Error Message:**
```
Undefined step
```

**Solution:**

1. **Create Step Definition:**
   ```java
   @Given("user is on login page")
   public void userIsOnLoginPage() {
       // Implementation
   }
   ```

2. **Verify Glue Path:**
   ```java
   @CucumberOptions(
       glue = {"com.parabank.stepdefinitions"}  // Must match package
   )
   ```

3. **Rebuild Project:**
   ```bash
   mvn clean compile
   ```

---

### Issue 14: Browser Not Maximized

**Solution:**

Update `DriverFactory.java`:
```java
public static WebDriver initializeDriver(String browserName) {
    // ... driver setup ...
    driver.manage().window().maximize();
    return driver;
}
```

Or in `WebDriverManager.java`:
```java
options.addArguments("--start-maximized");
```

---

### Issue 15: Configuration File Not Found

**Error Message:**
```
FileNotFoundException: src/main/resources/config.properties
```

**Solution:**

1. **Verify File Exists:**
   ```bash
   # Check if file is present
   ls src/main/resources/config.properties  # Linux/Mac
   dir src\main\resources\config.properties  # Windows
   ```

2. **Check File Path:**
   ```java
   // In ConfigReader.java
   private static final String CONFIG_FILE_PATH = "src/main/resources/config.properties";
   // Path must be correct
   ```

3. **Rebuild Project:**
   ```bash
   mvn clean install
   ```

---

## 🔧 Advanced Troubleshooting

### Debug Test Execution

```bash
# Run with debug output
mvn test -X

# Run single test with debug
mvn test -Dtest=LoginSteps -X

# Show full stack trace
mvn test -e
```

### Check Logs

```bash
# View application logs
tail -f logs/app.log  # Linux/Mac
type logs/app.log     # Windows

# View error logs
tail -f logs/error.log
```

### Browser Console Output

Add to BaseClass.java:
```java
// Capture browser logs
LoggingPreferences loggingPrefs = new LoggingPreferences();
loggingPrefs.enable(LogType.BROWSER, Level.ALL);

// Use in ChromeOptions
options.setLoggingPrefs(loggingPrefs);
```

### Network Issues

```bash
# Check if website is reachable
ping www.parabank.example.com

# Or in code
// Test connection before running tests
URL url = new URL(baseUrl);
URLConnection connection = url.openConnection();
connection.connect();
```

---

## 📋 Diagnostic Checklist

Before seeking help, verify:

- [ ] Java 11+ installed: `java -version`
- [ ] Maven installed: `mvn -v`
- [ ] Maven dependencies downloaded: `mvn dependency:resolve`
- [ ] Project builds: `mvn clean build`
- [ ] Website is accessible: `ping website.com`
- [ ] Browser installed (Chrome/Firefox/Edge)
- [ ] Correct file paths in code
- [ ] Correct locators in page objects
- [ ] Adequate wait times configured
- [ ] Sufficient disk space (500MB+)
- [ ] Sufficient RAM (2GB+)
- [ ] Internet connection available

---

## 🆘 Getting Help

### Step 1: Reproduce Issue
- Run test in isolation
- Note exact error message
- Check log files
- Take screenshot if possible

### Step 2: Check Documentation
- README.md - General info
- QUICKSTART.md - Setup issues
- TEST_EXECUTION_GUIDE.md - Execution issues
- This file - Common problems

### Step 3: Search Online
- Error message on Google
- StackOverflow for common issues
- Selenium documentation
- Cucumber documentation

### Step 4: Debug
```bash
# Run with maximum debug info
mvn clean test -X -e

# Check logs
cat logs/app.log | grep ERROR
```

### Step 5: Report Issue (if needed)
Include:
- Full error message
- Console output
- Log files (logs/app.log)
- Test that failed
- OS and Java version
- Steps to reproduce

---

## 📞 Resource Links

- **Java**: https://www.oracle.com/java/
- **Maven**: https://maven.apache.org/guides/
- **Selenium**: https://www.selenium.dev/documentation/
- **Cucumber**: https://cucumber.io/docs/
- **TestNG**: https://testng.org/doc/
- **WebDriverManager**: https://github.com/bonigarcia/webdrivermanager
- **Log4j2**: https://logging.apache.org/log4j/2.x/

---

## ⚡ Quick Fix Commands

```bash
# Fix everything - nuclear option
mvn clean dependency:purge-local-repository
mvn install
mvn clean test

# Fix memory issues
export MAVEN_OPTS=-Xmx2048m && mvn test

# Fix driver issues
mvn clean dependency:purge-local-repository
mvn test -U

# Fast rebuild
mvn clean compile

# Skip tests for quick build
mvn clean install -DskipTests
```

---

## 🎯 Prevention Tips

1. **Keep Dependencies Updated**
   ```bash
   mvn clean install -U
   ```

2. **Regular Testing**
   - Run tests after code changes
   - Check logs regularly
   - Monitor test success rate

3. **Maintenance**
   - Update locators when UI changes
   - Review and optimize waits
   - Archive old reports
   - Clean up logs periodically

4. **Documentation**
   - Document test data
   - Document environment setup
   - Keep README updated
   - Comment complex code

5. **Version Control**
   ```bash
   git add src/ pom.xml
   git commit -m "Descriptive message"
   ```

---

## 💡 Pro Tips

1. Use IDE debugger instead of print statements
2. Take screenshots of failures for analysis
3. Keep separate config for dev/test/prod
4. Monitor test execution time trends
5. Use parallel execution for faster feedback
6. Implement retry logic for flaky tests
7. Regular code reviews
8. Keep framework updated

---

**Still having issues?** 

Review the specific error in this guide or:
1. Check the relevant documentation file
2. Verify your environment setup
3. Review your code changes
4. Check the browser console (F12)
5. Review logs/ directory

Good luck! 🚀
