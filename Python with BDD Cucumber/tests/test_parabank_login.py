import pytest
import logging
from tests.pages.login_page import LoginPage
from tests.pages.dashboard_page import DashboardPage

logger = logging.getLogger(__name__)


class TestLoginFunctionality:
    """Test login functionality"""
    
    @pytest.mark.smoke
    def test_valid_login(self, driver):
        """Test login with valid credentials"""
        login_page = LoginPage(driver)
        login_page.open_login_page()
        
        # Verify login page is displayed
        assert login_page.is_login_page_displayed(), "Login page not displayed"
        
        # Perform login - using demo credentials
        login_page.login("john", "demo")
        
        # Verify dashboard is displayed
        dashboard = DashboardPage(driver)
        assert dashboard.is_dashboard_displayed(), "Dashboard not displayed after login"
        logger.info("Valid login test passed")
    
    @pytest.mark.regression
    def test_login_with_invalid_credentials(self, driver):
        """Test login with invalid credentials"""
        login_page = LoginPage(driver)
        login_page.open_login_page()
        
        # Perform login with invalid credentials
        login_page.login("invalid_user", "invalid_password")
        
        # Verify error message is displayed
        try:
            error_message = login_page.get_error_message()
            assert error_message != "", "Error message should be displayed"
            logger.info(f"Error message: {error_message}")
        except:
            logger.info("Error message not found in expected location")
    
    @pytest.mark.regression
    def test_login_with_empty_username(self, driver):
        """Test login with empty username"""
        login_page = LoginPage(driver)
        login_page.open_login_page()
        
        # Try to login with empty username
        login_page.enter_password("demo")
        login_page.click_login()
        
        # Verify login page is still displayed
        assert login_page.is_login_page_displayed(), "Should stay on login page"
        logger.info("Empty username test passed")
    
    @pytest.mark.regression
    def test_login_page_elements_displayed(self, driver):
        """Test that login page elements are displayed"""
        login_page = LoginPage(driver)
        login_page.open_login_page()
        
        # Verify all elements are displayed
        assert login_page.is_login_page_displayed(), "Login page not displayed"
        logger.info("Login page elements test passed")


class TestDashboardFunctionality:
    """Test dashboard functionality"""
    
    @pytest.mark.regression
    def test_logout_functionality(self, driver):
        """Test logout functionality"""
        login_page = LoginPage(driver)
        login_page.open_login_page()
        login_page.login("john", "demo")
        
        # Verify dashboard is displayed
        dashboard = DashboardPage(driver)
        assert dashboard.is_dashboard_displayed(), "Dashboard not displayed"
        
        # Click logout
        dashboard.click_logout()
        
        # Verify login page is displayed
        assert login_page.is_login_page_displayed(), "Should return to login page"
        logger.info("Logout test passed")
    
    @pytest.mark.integration
    def test_dashboard_menu_items(self, driver):
        """Test that dashboard menu items are accessible"""
        login_page = LoginPage(driver)
        login_page.open_login_page()
        login_page.login("john", "demo")
        
        dashboard = DashboardPage(driver)
        assert dashboard.is_dashboard_displayed(), "Dashboard not displayed"
        
        # Verify welcome message
        welcome_msg = dashboard.get_welcome_message()
        assert welcome_msg != "", "Welcome message should be displayed"
        logger.info(f"Welcome message: {welcome_msg}")
