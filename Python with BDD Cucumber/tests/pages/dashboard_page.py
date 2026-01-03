from selenium.webdriver.common.by import By
from tests.utils.base_page import BasePage
from tests.utils.base_element import BaseElement


class DashboardPage(BasePage):
    """Dashboard page objects for ParaBank"""
    
    # Locators
    WELCOME_MESSAGE = (By.CSS_SELECTOR, "h2")
    LOGOUT_LINK = (By.LINK_TEXT, "Log Out")
    ACCOUNTS_OVERVIEW_LINK = (By.LINK_TEXT, "Accounts Overview")
    TRANSFER_FUNDS_LINK = (By.LINK_TEXT, "Transfer Funds")
    BILL_PAY_LINK = (By.LINK_TEXT, "Bill Pay")
    REQUEST_LOAN_LINK = (By.LINK_TEXT, "Request Loan")
    UPDATE_PROFILE_LINK = (By.LINK_TEXT, "Update Contact Info")
    
    def __init__(self, driver):
        super().__init__(driver)
    
    def is_dashboard_displayed(self):
        """Check if dashboard is displayed"""
        element = BaseElement(self.driver, self.WELCOME_MESSAGE)
        return element.is_displayed()
    
    def get_welcome_message(self):
        """Get welcome message"""
        element = BaseElement(self.driver, self.WELCOME_MESSAGE)
        return element.get_text()
    
    def click_logout(self):
        """Click logout link"""
        element = BaseElement(self.driver, self.LOGOUT_LINK)
        element.click()
    
    def click_accounts_overview(self):
        """Click accounts overview link"""
        element = BaseElement(self.driver, self.ACCOUNTS_OVERVIEW_LINK)
        element.click()
    
    def click_transfer_funds(self):
        """Click transfer funds link"""
        element = BaseElement(self.driver, self.TRANSFER_FUNDS_LINK)
        element.click()
    
    def click_bill_pay(self):
        """Click bill pay link"""
        element = BaseElement(self.driver, self.BILL_PAY_LINK)
        element.click()
    
    def click_request_loan(self):
        """Click request loan link"""
        element = BaseElement(self.driver, self.REQUEST_LOAN_LINK)
        element.click()
    
    def click_update_profile(self):
        """Click update profile link"""
        element = BaseElement(self.driver, self.UPDATE_PROFILE_LINK)
        element.click()
