from pytest_bdd import scenarios, given, when, then
from tests.pages.login_page import LoginPage
from tests.pages.dashboard_page import DashboardPage
import logging

logger = logging.getLogger(__name__)

# Load all scenarios from the dashboard feature file
scenarios('../dashboard.feature')


@given('I am logged in to ParaBank')
def login_to_parabank(driver):
    login_page = LoginPage(driver)
    login_page.open_login_page()
    login_page.login("john", "demo")
    
    dashboard = DashboardPage(driver)
    assert dashboard.is_dashboard_displayed(), "Dashboard not displayed after login"
    logger.info("Logged in to ParaBank")


@when('I click on Accounts Overview link')
def click_accounts_overview(driver):
    dashboard = DashboardPage(driver)
    dashboard.click_accounts_overview()
    logger.info("Clicked Accounts Overview")


@then('I should navigate to the accounts overview page')
def verify_accounts_overview_page(driver):
    dashboard = DashboardPage(driver)
    current_url = dashboard.get_current_url()
    logger.info(f"Current URL: {current_url}")


@when('I click on Transfer Funds link')
def click_transfer_funds(driver):
    dashboard = DashboardPage(driver)
    dashboard.click_transfer_funds()
    logger.info("Clicked Transfer Funds")


@then('I should navigate to the transfer funds page')
def verify_transfer_funds_page(driver):
    dashboard = DashboardPage(driver)
    current_url = dashboard.get_current_url()
    logger.info(f"Navigated to: {current_url}")


@when('I click on Request Loan link')
def click_request_loan(driver):
    dashboard = DashboardPage(driver)
    dashboard.click_request_loan()
    logger.info("Clicked Request Loan")


@then('I should navigate to the loan request page')
def verify_loan_request_page(driver):
    dashboard = DashboardPage(driver)
    current_url = dashboard.get_current_url()
    logger.info(f"Navigated to: {current_url}")


@when('I click on the logout link')
def click_logout(driver):
    dashboard = DashboardPage(driver)
    dashboard.click_logout()
    logger.info("Clicked logout")


@then('I should return to the login page')
def verify_return_to_login(driver):
    login_page = LoginPage(driver)
    logger.info("Returned to login page")


@then('The login page should be displayed')
def verify_login_page_displayed(driver):
    login_page = LoginPage(driver)
    assert login_page.is_login_page_displayed(), "Login page not displayed"
    logger.info("Login page is displayed")


@then('The page URL should contain "<text>"')
def verify_url_contains(driver, text):
    dashboard = DashboardPage(driver)
    current_url = dashboard.get_current_url()
    assert text.lower() in current_url.lower(), f"URL should contain {text}"
    logger.info(f"URL contains: {text}")
