# Project File Structure and Navigation Guide

## 📊 Visual Project Map

```
ParaBank_Automation/
│
├── 📄 pom.xml                          ← Maven configuration (All dependencies)
├── 📄 README.md                        ← Main documentation
├── 📄 QUICKSTART.md                    ← Quick setup guide
├── 📄 PROJECT_SUMMARY.md               ← Project overview
├── 📄 MAVEN_COMMANDS.md                ← Maven command reference
├── 📄 TEST_EXECUTION_GUIDE.md          ← Test running guide
├── 📄 .gitignore                       ← Git ignore rules
│
├── 📁 src/
│   ├── 📁 main/
│   │   ├── 📁 java/com/parabank/
│   │   │   ├── 📁 base/                ← WebDriver and Base Classes
│   │   │   │   ├── DriverFactory.java          → Basic driver setup
│   │   │   │   ├── WebDriverManager.java       → Advanced driver setup
│   │   │   │   └── BaseClass.java              → Common methods
│   │   │   │
│   │   │   ├── 📁 config/              ← Configuration Management
│   │   │   │   └── ConfigReader.java           → Properties reader
│   │   │   │
│   │   │   ├── 📁 pages/               ← Page Object Model
│   │   │   │   ├── LoginPage.java              → Login interactions
│   │   │   │   ├── RegistrationPage.java       → Registration interactions
│   │   │   │   └── DashboardPage.java          → Account operations
│   │   │   │
│   │   │   └── 📁 utils/               ← Utility Classes
│   │   │       ├── LogUtils.java               → Logging utility
│   │   │       ├── ScreenshotUtils.java        → Screenshot capture
│   │   │       ├── DateTimeUtils.java          → Date/time operations
│   │   │       ├── TestDataUtils.java          → Test data generation
│   │   │       └── ScenarioContext.java        → Data sharing
│   │   │
│   │   └── 📁 resources/
│   │       ├── config.properties               ← App configuration
│   │       └── log4j2.xml                      ← Logging configuration
│   │
│   └── 📁 test/
│       ├── 📁 java/com/parabank/
│       │   ├── 📁 hooks/               ← Test Hooks
│       │   │   └── Hooks.java                  → Setup/Teardown
│       │   │
│       │   ├── 📁 runners/             ← Test Runners
│       │   │   └── CucumberTestRunner.java     → TestNG Runner
│       │   │
│       │   └── 📁 stepdefinitions/    ← Step Definitions
│       │       ├── LoginSteps.java             → Login steps
│       │       ├── RegistrationSteps.java      → Registration steps
│       │       └── AccountsSteps.java          → Account steps
│       │
│       └── 📁 resources/
│           ├── 📁 features/            ← Cucumber Features
│           │   ├── login.feature               → Login scenarios
│           │   ├── registration.feature        → Registration scenarios
│           │   └── accounts.feature            → Account scenarios
│           │
│           └── testng.xml                      ← TestNG configuration
│
├── 📁 target/                          ← Build output (generated)
│   ├── cucumber-reports/               → Cucumber HTML reports
│   ├── surefire-reports/               → TestNG reports
│   └── screenshots/                    → Test screenshots
│
└── 📁 logs/                            ← Log files (generated)
    ├── app.log                         → Application logs
    └── error.log                       → Error logs
```

## 🔍 File Purpose Quick Reference

### Configuration Files
| File | Purpose | Edit? |
|------|---------|-------|
| pom.xml | Maven dependencies & plugins | ✏️ Yes |
| config.properties | Application settings | ✏️ Yes |
| log4j2.xml | Logging configuration | ✏️ Yes |
| testng.xml | Test execution config | ✏️ Yes |
| .gitignore | Git ignore rules | ✏️ Yes |

### Source Code (src/main/)
| File | Purpose | Modify? |
|------|---------|---------|
| DriverFactory.java | Basic WebDriver setup | ❌ No |
| WebDriverManager.java | Advanced WebDriver setup | ⚡ Maybe |
| BaseClass.java | Common methods | ❌ No |
| ConfigReader.java | Config file reader | ❌ No |
| LoginPage.java | Login page objects | ✏️ Yes |
| RegistrationPage.java | Registration page objects | ✏️ Yes |
| DashboardPage.java | Dashboard page objects | ✏️ Yes |
| Utility classes | Helper methods | ⚡ Maybe |

### Test Code (src/test/)
| File | Purpose | Modify? |
|------|---------|---------|
| LoginSteps.java | Login step implementations | ✏️ Yes |
| RegistrationSteps.java | Registration implementations | ✏️ Yes |
| AccountsSteps.java | Account step implementations | ✏️ Yes |
| Hooks.java | Test setup/teardown | ⚡ Maybe |
| CucumberTestRunner.java | Test runner | ❌ No |
| Feature files | Test scenarios | ✏️ Yes |

### Generated Files (target/)
| File | Purpose | Delete? |
|------|---------|---------|
| cucumber.html | Test report | ✏️ View |
| index.html | TestNG report | ✏️ View |
| Logs | Test logs | ✏️ View |
| Screenshots | Failure screenshots | ✏️ View |

## 🔗 File Dependencies

```
pom.xml
  └─ Contains dependencies for:
     ├─ Selenium WebDriver
     ├─ TestNG
     ├─ Cucumber
     ├─ WebDriverManager
     ├─ Log4j2
     └─ Other utilities

config.properties
  └─ Used by:
     ├─ ConfigReader.java
     └─ All classes accessing config

DriverFactory.java / WebDriverManager.java
  └─ Used by:
     ├─ Hooks.java (initialization)
     ├─ All page classes
     └─ All step definitions

BaseClass.java
  └─ Extended by:
     ├─ LoginPage.java
     ├─ RegistrationPage.java
     └─ DashboardPage.java

Page Classes
  └─ Used by:
     ├─ LoginSteps.java
     ├─ RegistrationSteps.java
     └─ AccountsSteps.java

Step Definitions
  └─ Called by:
     └─ Feature files (Gherkin)

Feature Files
  └─ Executed by:
     └─ CucumberTestRunner.java
```

## 📝 Common File Editing Scenarios

### Scenario 1: Add New Test Scenario
1. Edit feature file: `src/test/resources/features/login.feature`
2. Implement steps in step definition: `src/test/java/com/parabank/stepdefinitions/LoginSteps.java`
3. Use existing page methods from: `src/main/java/com/parabank/pages/LoginPage.java`

### Scenario 2: Update Page Locators
1. Inspect element in browser
2. Get new XPath/CSS selector
3. Update in page class: `src/main/java/com/parabank/pages/*.java`
4. Re-run tests

### Scenario 3: Change Application URL
1. Open: `src/main/resources/config.properties`
2. Update: `baseUrl=https://your-url`
3. Run: `mvn test`

### Scenario 4: Add New Page Class
1. Create: `src/main/java/com/parabank/pages/NewPage.java`
2. Extend: `BaseClass`
3. Add locators and methods
4. Use in step definitions

### Scenario 5: Add New Step Definitions
1. Create: `src/test/java/com/parabank/stepdefinitions/NewSteps.java`
2. Implement methods with Cucumber annotations:
   ```java
   @Given("...")
   @When("...")
   @Then("...")
   ```
3. Use page classes for interactions

## 🚀 File Organization Best Practices

### DO ✅
- Keep page objects in `pages/` folder
- Keep steps in `stepdefinitions/` folder
- Keep utilities in `utils/` folder
- Use clear, descriptive file names
- Document complex methods
- Group related functionality

### DON'T ❌
- Don't mix page and step logic
- Don't hardcode URLs or data
- Don't put everything in one class
- Don't ignore logging
- Don't skip comments
- Don't commit generated files

## 📊 File Size Reference

| File Type | Typical Size | Count |
|-----------|-------------|-------|
| Page class | 200-400 lines | 3 |
| Step definition | 200-300 lines | 3 |
| Utility class | 50-150 lines | 5 |
| Feature file | 20-40 lines | 3 |
| Config file | 20-30 lines | 2 |

## 🔄 Typical Development Workflow

```
1. Read Feature File
   └─ src/test/resources/features/*.feature

2. Identify New Steps
   └─ Write step in feature file

3. Implement Step Definition
   └─ src/test/java/.../StepDefinition.java

4. Create/Update Page Object
   └─ src/main/java/.../pages/Page.java

5. Update Locators
   └─ Browser inspection → Copy XPath/CSS

6. Add Assertions
   └─ In step definition

7. Run Tests
   └─ mvn test

8. View Reports
   └─ target/cucumber-reports/cucumber.html

9. Fix Issues
   └─ Update locators, methods, or assertions

10. Commit to Git
    └─ git add src/ pom.xml
    └─ git commit -m "Add test scenarios"
```

## 🎯 Navigation Shortcuts

### For Page Object Maintenance
→ Go to: `src/main/java/com/parabank/pages/`

### For Step Implementation
→ Go to: `src/test/java/com/parabank/stepdefinitions/`

### For Test Scenarios
→ Go to: `src/test/resources/features/`

### For Configuration
→ Go to: `src/main/resources/`

### For Utilities
→ Go to: `src/main/java/com/parabank/utils/`

### For Reports
→ Go to: `target/cucumber-reports/` or `target/surefire-reports/`

### For Logs
→ Go to: `logs/`

## 📋 Checklist: Adding New Feature

- [ ] Create `.feature` file in `features/` folder
- [ ] Write scenarios in Gherkin syntax
- [ ] Create step definition class
- [ ] Implement all @Given, @When, @Then methods
- [ ] Create or update page class
- [ ] Add locators to page class
- [ ] Add methods to page class
- [ ] Run tests: `mvn test`
- [ ] Review reports
- [ ] Fix any failures
- [ ] Commit changes

---

**Navigation Tip**: Use IDE's search function (Ctrl+Shift+F) to find references across all files quickly!
