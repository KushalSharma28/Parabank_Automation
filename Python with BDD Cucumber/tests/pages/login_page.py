from selenium.webdriver.common.by import By
from tests.utils.base_page import BasePage
from tests.utils.base_element import BaseElement


class LoginPage(BasePage):
    """Login page objects for ParaBank"""
    
    # Locators
    USERNAME_INPUT = (By.NAME, "username")
    PASSWORD_INPUT = (By.NAME, "password")
    LOGIN_BUTTON = (By.CSS_SELECTOR, "input[value='Log In']")
    FORGOT_LOGIN_LINK = (By.LINK_TEXT, "Forgot login info?")
    REGISTER_LINK = (By.LINK_TEXT, "Register")
    ERROR_MESSAGE = (By.CSS_SELECTOR, ".error")
    
    def __init__(self, driver):
        super().__init__(driver)
        self.base_url = "https://parabank.parasoft.com/"
    
    def open_login_page(self):
        """Open ParaBank login page"""
        self.navigate_to(self.base_url)
        self.logger.info("Opened ParaBank login page")
    
    def enter_username(self, username):
        """Enter username"""
        element = BaseElement(self.driver, self.USERNAME_INPUT)
        element.send_keys(username)
    
    def enter_password(self, password):
        """Enter password"""
        element = BaseElement(self.driver, self.PASSWORD_INPUT)
        element.send_keys(password)
    
    def click_login(self):
        """Click login button"""
        element = BaseElement(self.driver, self.LOGIN_BUTTON)
        element.click()
    
    def login(self, username, password):
        """Complete login process"""
        self.enter_username(username)
        self.enter_password(password)
        self.click_login()
        self.logger.info(f"Attempted login with username: {username}")
    
    def is_login_page_displayed(self):
        """Check if login page is displayed"""
        element = BaseElement(self.driver, self.USERNAME_INPUT)
        return element.is_displayed()
    
    def get_error_message(self):
        """Get error message"""
        element = BaseElement(self.driver, self.ERROR_MESSAGE)
        return element.get_text()
    
    def click_register_link(self):
        """Click register link"""
        element = BaseElement(self.driver, self.REGISTER_LINK)
        element.click()
    
    def click_forgot_login_link(self):
        """Click forgot login link"""
        element = BaseElement(self.driver, self.FORGOT_LOGIN_LINK)
        element.click()
