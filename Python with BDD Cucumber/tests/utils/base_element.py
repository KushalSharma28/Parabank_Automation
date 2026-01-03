import logging
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.by import By
from selenium.common.exceptions import TimeoutException, NoSuchElementException

logger = logging.getLogger(__name__)


class BaseElement:
    """Base class for common element operations"""
    
    def __init__(self, driver, locator):
        self.driver = driver
        self.locator = locator
        self.wait = WebDriverWait(driver, 10)
    
    def find_element(self):
        """Find element"""
        try:
            element = self.driver.find_element(*self.locator)
            logger.info(f"Found element: {self.locator}")
            return element
        except NoSuchElementException:
            logger.error(f"Element not found: {self.locator}")
            raise
    
    def wait_for_element(self, timeout=10):
        """Wait for element to be visible"""
        try:
            element = WebDriverWait(self.driver, timeout).until(
                EC.visibility_of_element_located(self.locator)
            )
            logger.info(f"Element visible: {self.locator}")
            return element
        except TimeoutException:
            logger.error(f"Timeout waiting for element: {self.locator}")
            raise
    
    def wait_for_clickable(self, timeout=10):
        """Wait for element to be clickable"""
        try:
            element = WebDriverWait(self.driver, timeout).until(
                EC.element_to_be_clickable(self.locator)
            )
            logger.info(f"Element clickable: {self.locator}")
            return element
        except TimeoutException:
            logger.error(f"Timeout waiting for clickable element: {self.locator}")
            raise
    
    def click(self):
        """Click element"""
        try:
            element = self.wait_for_clickable()
            element.click()
            logger.info(f"Clicked element: {self.locator}")
        except Exception as e:
            logger.error(f"Error clicking element {self.locator}: {str(e)}")
            raise
    
    def send_keys(self, keys):
        """Send keys to element"""
        try:
            element = self.wait_for_element()
            element.clear()
            element.send_keys(keys)
            logger.info(f"Sent keys to element: {self.locator}")
        except Exception as e:
            logger.error(f"Error sending keys to {self.locator}: {str(e)}")
            raise
    
    def get_text(self):
        """Get element text"""
        try:
            element = self.wait_for_element()
            text = element.text
            logger.info(f"Got text from element: {self.locator} = {text}")
            return text
        except Exception as e:
            logger.error(f"Error getting text from {self.locator}: {str(e)}")
            raise
    
    def is_displayed(self):
        """Check if element is displayed"""
        try:
            element = self.find_element()
            is_displayed = element.is_displayed()
            logger.info(f"Element displayed check: {self.locator} = {is_displayed}")
            return is_displayed
        except NoSuchElementException:
            logger.warning(f"Element not found for display check: {self.locator}")
            return False
    
    def is_enabled(self):
        """Check if element is enabled"""
        try:
            element = self.find_element()
            is_enabled = element.is_enabled()
            logger.info(f"Element enabled check: {self.locator} = {is_enabled}")
            return is_enabled
        except NoSuchElementException:
            logger.warning(f"Element not found for enabled check: {self.locator}")
            return False
