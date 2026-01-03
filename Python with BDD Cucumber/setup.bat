@echo off
REM Setup script for ParaBank Automation Testing Framework (Windows)

echo ParaBank Automation Testing Framework Setup
echo ============================================
echo.

REM Check if Python is installed
python --version >nul 2>&1
if errorlevel 1 (
    echo ERROR: Python is not installed. Please install Python 3.8+
    exit /b 1
)

echo [OK] Python is installed
python --version
echo.

REM Create virtual environment
echo Creating virtual environment...
python -m venv venv
echo.

REM Activate virtual environment
echo Activating virtual environment...
call venv\Scripts\activate.bat
echo.

REM Install dependencies
echo Installing dependencies...
pip install -r requirements.txt
echo.

echo [OK] Setup completed successfully!
echo.
echo Next steps:
echo 1. Activate virtual environment:
echo    - venv\Scripts\activate
echo.
echo 2. Run tests:
echo    - pytest (run all tests)
echo    - pytest -m smoke (run smoke tests)
echo    - pytest features/ (run BDD tests)
echo.
echo 3. View reports in reports/ directory
