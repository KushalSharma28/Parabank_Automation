# Test Execution and Reporting Guide

## Pre-Execution Checklist

- [ ] Java 11+ installed: `java -version`
- [ ] Maven installed: `mvn --version`
- [ ] Project built: `mvn clean install`
- [ ] config.properties updated with correct URL
- [ ] Network connection available (for WebDriver downloads)
- [ ] Browser(s) installed (Chrome/Firefox/Edge)

## Test Execution Methods

### Method 1: Maven Command Line

#### Run All Tests
```bash
cd "Java with BDD Cucumber"
mvn clean test
```

#### Run with Specific Browser
```bash
# Chrome (default)
mvn test -Dbrowser=chrome

# Firefox
mvn test -Dbrowser=firefox

# Edge
mvn test -Dbrowser=edge
```

#### Run Specific Feature
```bash
# Login feature only
mvn test -Dcucumber.features="src/test/resources/features/login.feature"

# Registration feature only
mvn test -Dcucumber.features="src/test/resources/features/registration.feature"

# Accounts feature only
mvn test -Dcucumber.features="src/test/resources/features/accounts.feature"
```

#### Run by Test Class
```bash
mvn test -Dtest=CucumberTestRunner
mvn test -Dtest=LoginSteps
mvn test -Dtest=RegistrationSteps
mvn test -Dtest=AccountsSteps
```

### Method 2: IDE Execution (IntelliJ)

1. **Run Test Class**
   - Right-click on test class (CucumberTestRunner.java)
   - Select "Run" or "Debug"

2. **Run Feature File**
   - Right-click on .feature file
   - Select "Run Feature"

3. **Run Specific Scenario**
   - Right-click on scenario name
   - Select "Run Scenario"

4. **Debug Mode**
   - Right-click → "Debug"
   - Set breakpoints in code
   - Use IntelliJ debugger

### Method 3: IDE Execution (VS Code)

1. Install extension: "Test Explorer UI"
2. Open Test Explorer sidebar
3. Browse and run tests
4. View results in explorer

### Method 4: TestNG XML Configuration

Run with TestNG XML file:
```bash
mvn test -DsuiteXmlFile=src/test/resources/testng.xml
```

Edit `src/test/resources/testng.xml` to customize test suite:
- Add/remove test classes
- Configure parallel execution
- Set thread counts
- Skip specific tests

## Expected Test Output

### Console Output Example
```
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.parabank.runners.CucumberTestRunner
Feature: ParaBank Login Functionality

  Scenario: Successful login with valid credentials
    Given user is on ParaBank login page
    When user enters username "john"
    And user enters password "demo"
    And user clicks login button
    Then user should be navigated to dashboard page
    And user should see welcome message "Welcome john"

[INFO] Tests run: 1, Failures: 0, Skipped: 0, Time elapsed: 5.234 s
[INFO] -------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] -------------------------------------------------------
```

## Test Reports

### 1. Cucumber HTML Report
**Location**: `target/cucumber-reports/cucumber.html`

**View Report**:
- Open in web browser
- Shows all scenarios with status
- Includes step details
- Displays duration

### 2. TestNG Reports
**Location**: `target/surefire-reports/`

**Files Generated**:
- `index.html` - Main test report
- `emailable-report.html` - Email-friendly report
- `failsafe-report.html` - Integration test report

### 3. Logs
**Location**: `logs/`

**Files Generated**:
- `app.log` - All application logs
- `error.log` - Error logs only

### 4. Screenshots (if configured)
**Location**: `target/screenshots/`
- Automatic screenshots on failure
- Named with timestamp

## Test Scenarios Breakdown

### Login Tests (login.feature)
```
1. Successful login with valid credentials
   - Duration: ~5 seconds
   - Expected: Navigates to dashboard

2. Login fails with invalid credentials
   - Duration: ~3 seconds
   - Expected: Error message displayed

3. Login with empty username
   - Duration: ~2 seconds
   - Expected: Error message displayed

4. Login with empty password
   - Duration: ~2 seconds
   - Expected: Error message displayed
```

### Registration Tests (registration.feature)
```
1. Successful registration with valid details
   - Duration: ~8 seconds
   - Expected: Success confirmation

2. Registration fails with mismatched passwords
   - Duration: ~5 seconds
   - Expected: Error message displayed

3. Registration fails when required fields are empty
   - Duration: ~2 seconds
   - Expected: Error messages for fields
```

### Account Operations Tests (accounts.feature)
```
1. View accounts overview
   - Duration: ~5 seconds
   - Expected: Account list displayed

2. Open new account
   - Duration: ~7 seconds
   - Expected: New account created

3. Transfer funds between accounts
   - Duration: ~8 seconds
   - Expected: Transfer successful

4. View account details
   - Duration: ~4 seconds
   - Expected: Details displayed
```

## Performance Metrics

### Expected Execution Times
- **Single scenario**: 2-8 seconds
- **Feature file**: 10-20 seconds
- **All tests**: 45-60 seconds (sequential)
- **All tests**: 15-25 seconds (parallel)

### System Requirements
- **RAM**: 2GB minimum (4GB recommended)
- **Disk**: 500MB for dependencies
- **Internet**: Required for WebDriver download

## Troubleshooting Test Failures

### 1. Element Not Found
**Cause**: Incorrect locators or page not loaded
**Solution**:
- Verify locators in page objects
- Check wait times in config.properties
- Increase explicit wait timeout

### 2. WebDriver Not Initialized
**Cause**: Browser not installed or driver download failed
**Solution**:
- Ensure browser is installed
- Check internet connection
- Clear Maven cache: `mvn clean dependency:purge-local-repository`

### 3. Timeout Errors
**Cause**: Page loading slow or element not visible
**Solution**:
- Increase timeout in config.properties
- Check application performance
- Verify network connectivity

### 4. Script Timeout
**Cause**: JavaScript execution taking too long
**Solution**:
- Check application for JavaScript errors
- Increase pageLoadTimeout
- Check browser compatibility

### 5. Authentication Failure
**Cause**: Invalid test credentials
**Solution**:
- Verify credentials in config.properties
- Check if account is locked
- Confirm test data is valid

## Continuous Integration Setup

### For CI/CD Pipeline
Use this command in your CI/CD tool:
```bash
mvn clean test \
  -Dcucumber.features="src/test/resources/features" \
  -Dbrowser=chrome \
  -DfailIfNoTests=false \
  -Dheadless=true
```

### GitHub Actions Example
```yaml
name: Automated Tests
on: [push, pull_request]
jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - uses: actions/setup-java@v2
        with:
          java-version: '11'
      - run: mvn clean test -Dbrowser=chrome -Dheadless=true
      - uses: actions/upload-artifact@v2
        if: failure()
        with:
          name: test-reports
          path: target/cucumber-reports/
```

## Test Report Interpretation

### PASSED ✅
- All steps executed successfully
- All assertions passed
- No errors or warnings

### FAILED ❌
- One or more steps failed
- Assertion not met
- Exception thrown
- Check logs for details

### SKIPPED ⏭️
- Scenario/test was skipped
- @skip tag applied
- Previous step failed
- Configuration condition not met

### PENDING ⏸️
- Step implementation not complete
- Used as placeholder

## Advanced Testing Scenarios

### Parallel Test Execution
```bash
# Run tests in parallel (2 threads)
mvn test -Dthread-count=2

# Run tests in parallel (4 threads)
mvn test -Dthread-count=4
```

Edit `testng.xml` for parallel configuration:
```xml
<suite name="ParaBank Test Suite" parallel="tests" thread-count="4">
    ...
</suite>
```

### Test Isolation
Each test:
- Gets fresh browser instance
- Runs in separate JVM thread (if parallel)
- Has isolated test data
- Logs independently

### Data-Driven Testing
Example with TestNG:
```java
@DataProvider(name = "loginData")
public Object[][] getLoginData() {
    return new Object[][] {
        {"john", "demo"},
        {"user2", "pass2"}
    };
}

@Test(dataProvider = "loginData")
public void testLogin(String username, String password) {
    // Test code
}
```

## Post-Execution Steps

1. **Review Reports**
   - Check cucumber.html for summary
   - Review failed scenarios
   - Check logs for errors

2. **Analyze Failures**
   - Read error messages
   - Check screenshots (if taken)
   - Review logs

3. **Log Summary**
   ```
   Total Tests: 11
   Passed: 10
   Failed: 1
   Skipped: 0
   Pass Rate: 90.9%
   Duration: 52.3 seconds
   ```

4. **Communicate Results**
   - Email report to stakeholders
   - Create issue for failures
   - Plan fixes
   - Re-run tests

## Test Maintenance

### Regular Tasks
- [ ] Update locators monthly
- [ ] Review and update test data
- [ ] Check for deprecated methods
- [ ] Update dependencies quarterly
- [ ] Review and optimize wait times
- [ ] Archive old reports

### Version Control
```bash
# Commit test results (usually ignored)
git add src/
git add pom.xml
git commit -m "Update test scenarios"

# Don't commit:
# - target/ directory
# - logs/ directory
# - screenshots/
# - .idea/ (IDE files)
```

## Key Metrics to Track

| Metric | Target | Current |
|--------|--------|---------|
| Test Pass Rate | > 95% | ___ |
| Test Coverage | > 80% | ___ |
| Avg Execution Time | < 60 seconds | ___ |
| Failed Tests | 0 | ___ |
| Flaky Tests | 0 | ___ |

## Emergency Troubleshooting

### Tests Hanging
```bash
# Kill Maven process
# Linux/Mac: pkill -f maven
# Windows: taskkill /IM java.exe /F

# Clean and retry
mvn clean test -X
```

### Build Corruption
```bash
# Full clean
mvn clean
rm -rf .m2/repository (or %USERPROFILE%\.m2 on Windows)

# Reinstall
mvn clean install
```

### Permission Issues
```bash
# Linux/Mac: Add execute permission
chmod +x mvnw

# Windows: Run as Administrator
```

---

## Quick Command Reference

```bash
# Run all tests
mvn test

# Run with output
mvn test -e

# Run with debug
mvn test -X

# Run faster (parallel)
mvn test -T 1C

# Skip tests
mvn clean install -DskipTests

# View reports
open target/cucumber-reports/cucumber.html
```

**Remember**: Always review reports after test execution and maintain test infrastructure regularly.
