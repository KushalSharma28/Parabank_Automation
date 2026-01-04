# ParaBank Automation Framework

A comprehensive automation testing framework built with Selenium WebDriver, TestNG, and Cucumber BDD for testing the ParaBank application.

## 🎯 Features

- **Selenium WebDriver 4.x** - Latest WebDriver implementation
- **TestNG Framework** - Powerful testing framework with parallel execution support
- **Cucumber BDD** - Behavior Driven Development with Gherkin syntax
- **Page Object Model** - Maintainable and scalable test structure
- **WebDriverManager** - Automatic driver management
- **Log4j2** - Comprehensive logging
- **ExtentReports** - Beautiful HTML test reports
- **Maven** - Build automation and dependency management

## 📋 Project Structure

```
ParaBank_Automation/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/parabank/
│   │   │       ├── base/           # Base classes and driver factory
│   │   │       ├── config/         # Configuration management
│   │   │       ├── pages/          # Page Object Model classes
│   │   │       └── utils/          # Utility classes
│   │   └── resources/
│   │       ├── config.properties   # Configuration file
│   │       └── log4j2.xml         # Logging configuration
│   └── test/
│       ├── java/
│       │   └── com/parabank/
│       │       ├── hooks/          # Cucumber hooks
│       │       ├── runners/        # Test runners
│       │       └── stepdefinitions/# Step implementations
│       └── resources/
│           ├── features/           # Cucumber feature files
│           └── testng.xml         # TestNG configuration
├── pom.xml                         # Maven configuration
└── README.md                       # This file
```

## 🚀 Getting Started

### Prerequisites

- Java 11 or higher
- Maven 3.6 or higher
- Chrome/Firefox/Edge browser

### Installation

1. **Clone or download the project**
   ```bash
   cd "Java with BDD Cucumber"
   ```

2. **Install dependencies**
   ```bash
   mvn clean install
   ```

3. **Update configuration** (Optional)
   - Edit `src/main/resources/config.properties` for browser and URL settings

## 🏃 Running Tests

### Run all tests
```bash
mvn test
```

### Run specific test class
```bash
mvn test -Dtest=CucumberTestRunner
```

### Run with different browser
```bash
mvn test -Dbrowser=firefox
```

### Run with TestNG XML
```bash
mvn test -DsuiteXmlFile=src/test/resources/testng.xml
```

## 📝 Feature Files

Feature files are located in `src/test/resources/features/`:

- **login.feature** - Login functionality tests
- **registration.feature** - User registration tests
- **accounts.feature** - Account operations tests

## 🔧 Configuration

Edit `src/main/resources/config.properties`:

```properties
# Browser: chrome, firefox, edge
browser=chrome

# Base URL
baseUrl=https://www.parabank.example.com/parabank/

# Wait times (seconds)
implicitWait=10
explicitWait=20

# Test credentials
testUsername=john
testPassword=demo
```

## 📊 Test Reports

After test execution, reports are generated at:
- **Cucumber HTML Report**: `target/cucumber-reports/cucumber.html`
- **TestNG Report**: `target/surefire-reports/`

## 🏗️ Page Object Model

All pages are implemented in `src/main/java/com/parabank/pages/`:

- `LoginPage.java` - Login page actions
- `RegistrationPage.java` - Registration page actions
- `DashboardPage.java` - Dashboard/Account operations

## 🔍 Step Definitions

Step implementations are in `src/test/java/com/parabank/stepdefinitions/`:

- `LoginSteps.java` - Login related steps
- `RegistrationSteps.java` - Registration related steps
- `AccountsSteps.java` - Account operations steps

## 🪝 Hooks

Cucumber hooks for test setup/teardown:
- `Hooks.java` - Browser initialization and cleanup

## 📦 Dependencies

Key dependencies:
- Selenium WebDriver 4.15.0
- TestNG 7.8.1
- Cucumber 7.14.0
- WebDriverManager 5.7.0
- Log4j2 2.21.0
- ExtentReports 5.0.9

## 🐛 Logging

Logs are generated in the `logs/` directory:
- `app.log` - All application logs
- `error.log` - Error logs only

Log level can be configured in `log4j2.xml`

## 💡 Best Practices

1. **Page Object Model** - All UI interactions through page classes
2. **Explicit Waits** - Used instead of implicit waits for reliability
3. **Descriptive Step Names** - Steps written in plain English
4. **Proper Assertions** - Each test has clear assertions
5. **Logging** - All important actions are logged
6. **Configuration Management** - Centralized configuration

## 🚧 Future Enhancements

- [ ] API testing with RestAssured
- [ ] Screenshot on failure
- [ ] Mobile testing with Appium
- [ ] Performance testing
- [ ] Parallel execution configuration
- [ ] Docker support
- [ ] CI/CD integration

## 📞 Support

For issues or questions, please refer to:
- Selenium: https://www.selenium.dev/documentation/
- TestNG: https://testng.org/
- Cucumber: https://cucumber.io/docs/cucumber/

## 📄 License

This project is open source and available under the MIT License.

## ✍️ Author

Created as a comprehensive automation framework for ParaBank testing.

---

**Last Updated**: January 3, 2026
