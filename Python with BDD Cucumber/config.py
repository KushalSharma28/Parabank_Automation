# ParaBank Automation Test Configuration

import os
from dotenv import load_dotenv

# Load environment variables from .env file
load_dotenv()

# Base URLs
BASE_URL = os.getenv("BASE_URL", "https://parabank.parasoft.com/")

# Browser Settings
BROWSER = os.getenv("BROWSER", "chrome")
HEADLESS = os.getenv("HEADLESS", "false").lower() == "true"
WINDOW_SIZE = os.getenv("WINDOW_SIZE", "1920,1080")

# Timeouts
IMPLICIT_WAIT = int(os.getenv("IMPLICIT_WAIT", "10"))
EXPLICIT_WAIT = int(os.getenv("EXPLICIT_WAIT", "15"))

# Logging
LOG_LEVEL = os.getenv("LOG_LEVEL", "INFO")
LOG_DIR = os.getenv("LOG_DIR", "logs")

# Report Settings
REPORT_DIR = os.getenv("REPORT_DIR", "reports")

# Test Data
TEST_USERNAME = os.getenv("TEST_USERNAME", "john")
TEST_PASSWORD = os.getenv("TEST_PASSWORD", "demo")

# Retry Settings
RETRY_ATTEMPTS = int(os.getenv("RETRY_ATTEMPTS", "3"))
RETRY_DELAY = int(os.getenv("RETRY_DELAY", "2"))

class Config:
    """Configuration class for test settings"""
    base_url = BASE_URL
    browser = BROWSER
    headless = HEADLESS
    window_size = WINDOW_SIZE
    implicit_wait = IMPLICIT_WAIT
    explicit_wait = EXPLICIT_WAIT
    log_level = LOG_LEVEL
    log_dir = LOG_DIR
    report_dir = REPORT_DIR
    test_username = TEST_USERNAME
    test_password = TEST_PASSWORD
    retry_attempts = RETRY_ATTEMPTS
    retry_delay = RETRY_DELAY
