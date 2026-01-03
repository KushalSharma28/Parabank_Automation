# pytest-bdd configuration for BDD tests
import os
import sys

# Add features directory to path
sys.path.insert(0, os.path.join(os.path.dirname(__file__), 'features'))

def pytest_configure(config):
    config.addinivalue_line(
        "markers", "bdd: mark test as BDD scenario"
    )
