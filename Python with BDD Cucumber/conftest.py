import pytest
import logging
from datetime import datetime
from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.firefox.service import Service as FirefoxService
from webdriver_manager.firefox import GeckoDriverManager
import os

# Configure logging
log_dir = "logs"
if not os.path.exists(log_dir):
    os.makedirs(log_dir)

log_file = os.path.join(log_dir, f"test_log_{datetime.now().strftime('%Y%m%d_%H%M%S')}.log")
logging.basicConfig(
    level=logging.INFO,
    format='%(asctime)s - %(name)s - %(levelname)s - %(message)s',
    handlers=[
        logging.FileHandler(log_file),
        logging.StreamHandler()
    ]
)

logger = logging.getLogger(__name__)


@pytest.fixture(scope="session")
def browser_type(request):
    """Get browser type from command line or default to chrome"""
    return request.config.getoption("--browser", default="chrome")


@pytest.fixture
def driver(browser_type):
    """Initialize WebDriver"""
    logger.info(f"Initializing {browser_type} driver")
    
    if browser_type.lower() == "firefox":
        service = FirefoxService(GeckoDriverManager().install())
        driver = webdriver.Firefox(service=service)
    else:  # Default to Chrome
        service = Service(ChromeDriverManager().install())
        options = webdriver.ChromeOptions()
        # Uncomment for headless mode
        # options.add_argument("--headless")
        options.add_argument("--no-sandbox")
        options.add_argument("--disable-dev-shm-usage")
        driver = webdriver.Chrome(service=service, options=options)
    
    driver.maximize_window()
    driver.implicitly_wait(10)
    
    yield driver
    
    logger.info("Closing driver")
    driver.quit()


@pytest.fixture
def logger_fixture():
    """Provide logger fixture to tests"""
    return logger


def pytest_addoption(parser):
    """Add custom command line options"""
    parser.addoption(
        "--browser",
        action="store",
        default="chrome",
        help="Browser to use: chrome or firefox"
    )


@pytest.fixture(autouse=True)
def log_test_info(request):
    """Log test information"""
    logger.info(f"\n{'='*50}")
    logger.info(f"Starting test: {request.node.name}")
    logger.info(f"{'='*50}")
    
    yield
    
    logger.info(f"Test {request.node.name} completed")
    logger.info(f"{'='*50}\n")
