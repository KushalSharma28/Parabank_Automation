import logging

logger = logging.getLogger(__name__)


class BasePage:
    """Base page class for all page objects"""
    
    def __init__(self, driver):
        self.driver = driver
        self.logger = logger
    
    def navigate_to(self, url):
        """Navigate to URL"""
        try:
            self.driver.get(url)
            self.logger.info(f"Navigated to: {url}")
        except Exception as e:
            self.logger.error(f"Error navigating to {url}: {str(e)}")
            raise
    
    def get_current_url(self):
        """Get current URL"""
        current_url = self.driver.current_url
        self.logger.info(f"Current URL: {current_url}")
        return current_url
    
    def get_title(self):
        """Get page title"""
        title = self.driver.title
        self.logger.info(f"Page title: {title}")
        return title
    
    def wait_for_url_contains(self, url, timeout=10):
        """Wait for URL to contain specific text"""
        from selenium.webdriver.support.ui import WebDriverWait
        from selenium.webdriver.support import expected_conditions as EC
        
        try:
            WebDriverWait(self.driver, timeout).until(
                EC.url_contains(url)
            )
            self.logger.info(f"URL contains: {url}")
        except Exception as e:
            self.logger.error(f"Timeout waiting for URL to contain {url}: {str(e)}")
            raise
    
    def refresh_page(self):
        """Refresh the page"""
        self.driver.refresh()
        self.logger.info("Page refreshed")
    
    def go_back(self):
        """Go back to previous page"""
        self.driver.back()
        self.logger.info("Navigated back")
    
    def go_forward(self):
        """Go forward to next page"""
        self.driver.forward()
        self.logger.info("Navigated forward")
