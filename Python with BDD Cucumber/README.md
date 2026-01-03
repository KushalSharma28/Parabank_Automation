# ParaBank Automation Testing Framework

A comprehensive test automation framework built with **Pytest**, **Selenium WebDriver**, and **Cucumber (BDD)** for testing the ParaBank website.

## Project Structure

```
ParaBank_Automation/
├── features/                    # Cucumber feature files
│   ├── login.feature           # Login scenarios
│   ├── dashboard.feature       # Dashboard scenarios
│   └── steps/                  # Step implementations
│       ├── login_steps.py      # Login step definitions
│       └── dashboard_steps.py  # Dashboard step definitions
├── tests/                       # Pytest test files
│   ├── pages/                  # Page Object Model
│   │   ├── login_page.py
│   │   └── dashboard_page.py
│   ├── utils/                  # Utility classes
│   │   ├── base_page.py
│   │   └── base_element.py
│   ├── test_parabank_login.py
│   └── test_parabank_accounts.py
├── conftest.py                 # Pytest configuration and fixtures
├── pytest.ini                  # Pytest settings
├── requirements.txt            # Python dependencies
├── README.md                   # This file
└── logs/                       # Test logs
```

## Features

- **Page Object Model (POM)**: Structured approach to page management
- **Pytest Framework**: Modern Python testing framework
- **Cucumber/BDD**: Behavior-driven development with Gherkin syntax
- **Selenium WebDriver**: Cross-browser automation
- **WebDriver Manager**: Automatic driver management
- **Logging**: Comprehensive test logging
- **HTML Reports**: Pytest HTML report generation
- **Parallel Execution**: Support for running tests in parallel

## Prerequisites

- Python 3.8+
- pip (Python package manager)
- Git

## Installation

1. **Clone or navigate to the project directory:**
   ```bash
   cd ParaBank_Automation
   ```

2. **Create a virtual environment (optional but recommended):**
   ```bash
   python -m venv venv
   venv\Scripts\activate  # Windows
   source venv/bin/activate  # macOS/Linux
   ```

3. **Install dependencies:**
   ```bash
   pip install -r requirements.txt
   ```

## Running Tests

### Run all pytest tests:
```bash
pytest
```

### Run with specific marker:
```bash
pytest -m smoke
pytest -m regression
pytest -m integration
```

### Run specific test file:
```bash
pytest tests/test_parabank_login.py
```

### Run with Firefox browser:
```bash
pytest --browser=firefox
```

### Run in headless mode (modify conftest.py):
Uncomment the headless option in `conftest.py`

### Generate HTML report:
```bash
pytest --html=reports/report.html --self-contained-html
```

### Run BDD tests with pytest-bdd:
```bash
pytest features/
```

### Run tests in parallel:
```bash
pytest -n 4  # Run on 4 workers
```

### Run with verbose output:
```bash
pytest -v
```

## Test Markers

- `@pytest.mark.smoke` - Smoke tests (critical functionality)
- `@pytest.mark.regression` - Regression tests (existing functionality)
- `@pytest.mark.integration` - Integration tests (multiple components)
- `@pytest.mark.bdd` - BDD tests with Cucumber

## Page Objects

### LoginPage
- `open_login_page()` - Navigate to login page
- `enter_username(username)` - Enter username
- `enter_password(password)` - Enter password
- `click_login()` - Click login button
- `login(username, password)` - Complete login
- `get_error_message()` - Get error message

### DashboardPage
- `is_dashboard_displayed()` - Check if dashboard visible
- `get_welcome_message()` - Get welcome message
- `click_logout()` - Click logout button
- `click_accounts_overview()` - Navigate to accounts
- `click_transfer_funds()` - Navigate to transfer
- `click_request_loan()` - Navigate to loan request

## BDD Feature Files

### login.feature
Tests login functionality with valid/invalid credentials

### dashboard.feature
Tests dashboard navigation and logout functionality

## Utilities

### BaseElement
Common element operations (click, send_keys, wait, etc.)

### BasePage
Common page operations (navigate, get title, etc.)

## Logging

Logs are saved to `logs/` directory with timestamp. Each test run creates a new log file.

## Reports

HTML reports are generated in `reports/` directory after test execution.

## Configuration

### conftest.py
- WebDriver initialization
- Fixture setup
- Command-line options

### pytest.ini
- Pytest settings
- Test discovery patterns
- Report configuration

## Browsers Supported

- Chrome (default)
- Firefox

## Notes

- Test data can be parameterized using `@pytest.mark.parametrize`
- Extend page objects with new methods as needed
- Create new feature files for additional BDD scenarios
- Update selectors in page objects if website changes

## Troubleshooting

### Driver not found
- WebDriver Manager automatically downloads drivers
- Ensure internet connection is available

### Element not found
- Check selectors in page objects
- Add explicit waits in base_element.py
- Verify website structure hasn't changed

### Import errors
- Ensure all files have proper `__init__.py`
- Check Python path configuration

## Future Enhancements

- Database integration for test data
- API testing integration
- Performance testing
- Screenshot capture on failure
- Email reports
- CI/CD pipeline integration

## License

This project is open source and available under the MIT License.

## Support

For issues or questions, please create an issue in the repository.
