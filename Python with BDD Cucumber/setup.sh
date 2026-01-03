#!/bin/bash
# Setup script for ParaBank Automation Testing Framework

echo "ParaBank Automation Testing Framework Setup"
echo "============================================"

# Check if Python is installed
if ! command -v python &> /dev/null; then
    echo "ERROR: Python is not installed. Please install Python 3.8+"
    exit 1
fi

echo "✓ Python is installed"
python --version

# Create virtual environment
echo ""
echo "Creating virtual environment..."
python -m venv venv

# Activate virtual environment
echo "Activating virtual environment..."
source venv/bin/activate  # For macOS/Linux
# For Windows: venv\Scripts\activate

# Install dependencies
echo ""
echo "Installing dependencies..."
pip install -r requirements.txt

echo ""
echo "✓ Setup completed successfully!"
echo ""
echo "Next steps:"
echo "1. Activate virtual environment:"
echo "   - macOS/Linux: source venv/bin/activate"
echo "   - Windows: venv\\Scripts\\activate"
echo ""
echo "2. Run tests:"
echo "   - pytest (run all tests)"
echo "   - pytest -m smoke (run smoke tests)"
echo "   - pytest features/ (run BDD tests)"
echo ""
echo "3. View reports in reports/ directory"
