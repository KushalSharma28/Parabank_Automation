# Quick Start Guide for ParaBank Automation Testing

## Setup Instructions

### Step 1: Install Dependencies
```bash
pip install -r requirements.txt
```

### Step 2: Configure Environment
```bash
# Copy the example environment file
copy .env.example .env

# Edit .env with your configuration (optional)
```

### Step 3: Run Tests

#### Run all pytest tests:
```bash
pytest
```

#### Run tests by marker:
```bash
pytest -m smoke       # Run smoke tests
pytest -m regression  # Run regression tests
pytest -m integration # Run integration tests
```

#### Run specific test file:
```bash
pytest tests/test_parabank_login.py -v
```

#### Run with Firefox:
```bash
pytest --browser=firefox
```

#### Run tests in parallel:
```bash
pytest -n 4
```

#### Generate report:
```bash
pytest --html=reports/report.html --self-contained-html
```

### Step 4: Run BDD Tests

#### Run all BDD scenarios:
```bash
pytest features/ -v
```

#### Run specific feature:
```bash
pytest features/login.feature -v
```

## Project Features

✓ Page Object Model (POM)
✓ Pytest Framework
✓ Cucumber/BDD Support
✓ Selenium WebDriver 4.x
✓ Automatic Driver Management
✓ Comprehensive Logging
✓ HTML Report Generation
✓ Parallel Test Execution
✓ Cross-browser Support

## Test Structure

### Pytest Tests
- `tests/test_parabank_login.py` - Login functionality tests
- `tests/test_parabank_accounts.py` - Account functionality tests

### BDD Tests
- `features/login.feature` - Login scenarios
- `features/dashboard.feature` - Dashboard scenarios

## Page Objects

- `LoginPage` - Login page interactions
- `DashboardPage` - Dashboard page interactions

## Utilities

- `BaseElement` - Common element operations
- `BasePage` - Common page operations
- `CommonActions` - Common browser actions

## For More Information

See README.md for complete documentation.
