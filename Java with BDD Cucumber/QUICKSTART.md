# Quick Start Guide - ParaBank Automation Framework

## Prerequisites
- Java 11+ installed
- Maven installed
- Git (optional)

## Step-by-Step Setup

### 1. Navigate to Project Directory
```bash
cd "Java with BDD Cucumber"
```

### 2. Install Dependencies
```bash
mvn clean install
```
This will download all required dependencies specified in pom.xml.

### 3. Run Tests
```bash
mvn test
```

### 4. View Test Report
After test execution, open the HTML report:
```
target/cucumber-reports/cucumber.html
```

## Project Structure Overview

```
src/
├── main/java/com/parabank/
│   ├── base/              # WebDriver and Base classes
│   ├── config/            # Configuration reader
│   ├── pages/             # Page Object Model
│   └── utils/             # Utilities (logging, screenshots, etc.)
├── main/resources/
│   ├── config.properties  # Configuration file
│   └── log4j2.xml        # Logging setup
└── test/
    ├── java/com/parabank/
    │   ├── hooks/         # Test setup/teardown
    │   ├── runners/       # Test runners
    │   └── stepdefinitions/ # Cucumber step implementations
    └── resources/
        ├── features/      # Cucumber feature files
        └── testng.xml     # TestNG configuration
```

## Feature Files Included

1. **login.feature** - Login scenarios
   - Successful login
   - Login with invalid credentials
   - Empty field validation

2. **registration.feature** - Registration scenarios
   - Successful registration
   - Password mismatch validation
   - Empty fields validation

3. **accounts.feature** - Account operations
   - View accounts
   - Create new account
   - Transfer funds
   - View transaction details

## Configuration

Edit `src/main/resources/config.properties` to customize:
- Browser (chrome, firefox, edge)
- Base URL
- Wait times
- Test credentials

## Running Specific Tests

### Run by tag
```bash
mvn test -Dcucumber.filter.tags="@login"
```

### Run by feature
```bash
mvn test -Dcucumber.features="src/test/resources/features/login.feature"
```

### Run with different browser
```bash
mvn test -Dbrowser=firefox
```

## Important Notes

1. **Locators**: Current locators are examples. Update them based on actual ParaBank website
2. **Base URL**: Update the base URL in feature files or page objects
3. **Wait Times**: Configured in config.properties
4. **Logging**: Check logs/ directory for detailed logs

## Troubleshooting

### Tests not running
- Check Java version: `java -version` (should be 11+)
- Reinstall dependencies: `mvn clean install`
- Clear cache: `mvn clean`

### Browser not launching
- Ensure WebDriverManager can access the internet for driver downloads
- Or manually download and set driver path

### Flaky tests
- Increase wait times in config.properties
- Check internet connection
- Verify website is accessible

## Next Steps

1. Update locators in page classes for actual application
2. Add more test scenarios in feature files
3. Implement additional utility methods
4. Set up CI/CD integration
5. Add screenshot on failure
6. Configure parallel test execution

## IDE Setup (IntelliJ)

1. Open project: File → Open
2. Choose project directory
3. Configure SDK: File → Project Structure → Project SDK → Java 11+
4. Install Cucumber plugin: File → Settings → Plugins → Search "Cucumber"
5. Mark directories: 
   - Right-click src/test/resources → Mark Directory as → Test Resources Root

## IDE Setup (VS Code)

1. Install extensions:
   - Language Support for Java (Red Hat)
   - Test Explorer UI
   - Cucumber (Gherkin) Full Support

2. Open workspace folder

## Questions?

Refer to:
- [Selenium Documentation](https://www.selenium.dev/documentation/)
- [Cucumber BDD](https://cucumber.io/docs/cucumber/)
- [TestNG](https://testng.org/doc/)

---

**Ready to test!** Run `mvn test` and watch your automated tests execute.
