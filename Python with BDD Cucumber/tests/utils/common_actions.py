import logging
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

logger = logging.getLogger(__name__)


class CommonActions:
    """Common actions for test execution"""
    
    def __init__(self, driver):
        self.driver = driver
        self.wait = WebDriverWait(driver, 15)
    
    def accept_alert(self):
        """Accept JavaScript alert"""
        try:
            alert = self.wait.until(EC.alert_is_present())
            alert.accept()
            logger.info("Alert accepted")
        except:
            logger.warning("No alert found")
    
    def dismiss_alert(self):
        """Dismiss JavaScript alert"""
        try:
            alert = self.wait.until(EC.alert_is_present())
            alert.dismiss()
            logger.info("Alert dismissed")
        except:
            logger.warning("No alert found")
    
    def get_alert_text(self):
        """Get alert text"""
        try:
            alert = self.wait.until(EC.alert_is_present())
            text = alert.text
            logger.info(f"Alert text: {text}")
            return text
        except:
            logger.warning("No alert found")
            return None
    
    def switch_to_frame(self, frame_locator):
        """Switch to iframe"""
        try:
            frame = self.wait.until(EC.presence_of_element_located(frame_locator))
            self.driver.switch_to.frame(frame)
            logger.info(f"Switched to frame: {frame_locator}")
        except Exception as e:
            logger.error(f"Error switching to frame: {str(e)}")
            raise
    
    def switch_to_default_content(self):
        """Switch back to default content"""
        self.driver.switch_to.default_content()
        logger.info("Switched to default content")
    
    def switch_to_window(self, window_handle):
        """Switch to window by handle"""
        self.driver.switch_to.window(window_handle)
        logger.info(f"Switched to window: {window_handle}")
    
    def get_all_window_handles(self):
        """Get all window handles"""
        handles = self.driver.window_handles
        logger.info(f"Window handles: {handles}")
        return handles
    
    def scroll_to_element(self, locator):
        """Scroll to element"""
        try:
            element = self.wait.until(EC.presence_of_element_located(locator))
            self.driver.execute_script("arguments[0].scrollIntoView(true);", element)
            logger.info(f"Scrolled to element: {locator}")
        except Exception as e:
            logger.error(f"Error scrolling to element: {str(e)}")
            raise
    
    def get_page_source(self):
        """Get page source"""
        source = self.driver.page_source
        logger.info("Retrieved page source")
        return source
    
    def take_screenshot(self, filename="screenshot.png"):
        """Take screenshot"""
        try:
            self.driver.save_screenshot(filename)
            logger.info(f"Screenshot saved: {filename}")
        except Exception as e:
            logger.error(f"Error taking screenshot: {str(e)}")
            raise
