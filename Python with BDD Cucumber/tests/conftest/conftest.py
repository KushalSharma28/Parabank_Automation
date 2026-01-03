# This is the root conftest file for pytest fixture configuration
# All fixtures defined here are available to all tests

import sys
import os

# Add project root to path
sys.path.insert(0, os.path.dirname(__file__))
