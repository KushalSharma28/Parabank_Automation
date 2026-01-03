import pytest
import logging
from tests.pages.login_page import LoginPage
from tests.pages.dashboard_page import DashboardPage

logger = logging.getLogger(__name__)


class TestAccountsFunctionality:
    """Test accounts-related functionality"""
    
    @pytest.mark.integration
    def test_accounts_overview_navigation(self, driver):
        """Test navigation to accounts overview"""
        login_page = LoginPage(driver)
        login_page.open_login_page()
        login_page.login("john", "demo")
        
        dashboard = DashboardPage(driver)
        assert dashboard.is_dashboard_displayed(), "Dashboard not displayed"
        
        # Navigate to accounts overview
        dashboard.click_accounts_overview()
        
        # Verify navigation
        current_url = dashboard.get_current_url()
        assert "overview" in current_url.lower(), "Should navigate to accounts overview"
        logger.info(f"Current URL: {current_url}")
    
    @pytest.mark.integration
    def test_transfer_funds_navigation(self, driver):
        """Test navigation to transfer funds"""
        login_page = LoginPage(driver)
        login_page.open_login_page()
        login_page.login("john", "demo")
        
        dashboard = DashboardPage(driver)
        dashboard.click_transfer_funds()
        
        # Verify navigation
        current_url = dashboard.get_current_url()
        assert "transfer" in current_url.lower(), "Should navigate to transfer funds"
        logger.info(f"Navigated to transfer funds: {current_url}")
    
    @pytest.mark.integration
    def test_request_loan_navigation(self, driver):
        """Test navigation to request loan"""
        login_page = LoginPage(driver)
        login_page.open_login_page()
        login_page.login("john", "demo")
        
        dashboard = DashboardPage(driver)
        dashboard.click_request_loan()
        
        # Verify navigation
        current_url = dashboard.get_current_url()
        assert "loan" in current_url.lower(), "Should navigate to loan request"
        logger.info(f"Navigated to request loan: {current_url}")
