# Maven Commands Reference

## Basic Commands

### Clean and Build
```bash
# Clean previous builds
mvn clean

# Build project
mvn build

# Clean and build
mvn clean build
```

### Install Dependencies
```bash
# Download and install all dependencies
mvn clean install

# Download and install, skip tests
mvn clean install -DskipTests

# Refresh snapshot dependencies
mvn clean install -U
```

## Running Tests

### Basic Test Execution
```bash
# Run all tests
mvn test

# Run tests with specific browser
mvn test -Dbrowser=firefox
mvn test -Dbrowser=edge
mvn test -Dbrowser=chrome

# Run tests without stopping on failure
mvn test -DfailIfNoTests=false
```

### Run Specific Tests
```bash
# Run specific test class
mvn test -Dtest=CucumberTestRunner

# Run specific test method
mvn test -Dtest=LoginSteps#userEntersUsername

# Run tests matching pattern
mvn test -Dtest=*Steps
```

### Run by Feature
```bash
# Run specific feature file
mvn test -Dcucumber.features="src/test/resources/features/login.feature"

# Run multiple feature files
mvn test -Dcucumber.features="src/test/resources/features/login.feature,src/test/resources/features/accounts.feature"

# Run all features in directory
mvn test -Dcucumber.features="src/test/resources/features"
```

### Run by Tags
```bash
# Run tests with specific tag
mvn test -Dcucumber.filter.tags="@login"

# Run tests with multiple tags (AND)
mvn test -Dcucumber.filter.tags="@login and @smoke"

# Run tests with tags (OR)
mvn test -Dcucumber.filter.tags="@login or @registration"

# Run tests excluding tags
mvn test -Dcucumber.filter.tags="not @skip"
```

## Advanced Options

### Skip Tests
```bash
# Build without running tests
mvn clean install -DskipTests

# Also skip test compilation
mvn clean install -DskipTests -Dmaven.test.skip=true
```

### Parallel Execution
```bash
# Run tests in parallel (example: 4 threads)
mvn test -DparallelTestCount=4

# Run with thread count
mvn test -Dthread-count=4
```

### Verbose Output
```bash
# Show all debug information
mvn test -X

# Show version information
mvn -v

# Show effective POM
mvn help:effective-pom
```

### Logging Levels
```bash
# Debug level logging
mvn test -X

# Info level logging (default)
mvn test

# Quiet mode (minimal output)
mvn test -q
```

## Dependency Management

### Download Dependencies
```bash
# Download all dependencies
mvn dependency:resolve

# Download with sources
mvn dependency:resolve -Dclassifier=sources

# Download with javadoc
mvn dependency:resolve -Dclassifier=javadoc
```

### Analyze Dependencies
```bash
# Show dependency tree
mvn dependency:tree

# Show dependency tree for specific artifact
mvn dependency:tree -Dincludes=org.testng

# Find unused dependencies
mvn dependency:analyze

# Find potentially unused dependencies
mvn dependency:analyze-only
```

## Code Quality

### Code Style Check
```bash
# Check code style
mvn spotbugs:check

# Generate spotbugs report
mvn spotbugs:gui
```

### Code Coverage
```bash
# Run tests with code coverage
mvn clean test jacoco:report

# View coverage report
# Open: target/site/jacoco/index.html
```

## Building

### Package Project
```bash
# Create JAR file
mvn package

# Create JAR without tests
mvn package -DskipTests
```

### Deploy to Repository
```bash
# Deploy to Maven repository
mvn deploy

# Deploy without tests
mvn deploy -DskipTests
```

## Troubleshooting

### Clear Cache
```bash
# Remove local repository cache
mvn clean

# Remove and recreate .m2 folder
rm -rf ~/.m2/repository  # Linux/Mac
rmdir %USERPROFILE%\.m2 /s  # Windows

# Redownload dependencies
mvn dependency:resolve
```

### Check Maven
```bash
# Verify Maven installation
mvn --version

# Verify Java
java -version

# Help command
mvn help
```

### Debug Issues
```bash
# Run with debug output
mvn test -X

# Run single test in debug
mvn test -Dtest=LoginSteps -X

# Show pom configuration
mvn help:effective-pom > effective-pom.xml
```

## Common Issues & Solutions

### Issue: Tests not found
```bash
# Verify test path
mvn test -Dtest=com.parabank.runners.CucumberTestRunner

# Run with full class name
mvn test -Dtest=com.parabank.stepdefinitions.LoginSteps
```

### Issue: JAR or dependency conflicts
```bash
# Clear all Maven cache
mvn clean dependency:purge-local-repository

# Reinstall
mvn clean install -U
```

### Issue: Out of memory
```bash
# Set memory for Maven
export MAVEN_OPTS=-Xmx1024m  # Linux/Mac
set MAVEN_OPTS=-Xmx1024m     # Windows

mvn clean install
```

## Quick Command Combinations

### Development Workflow
```bash
# Development: Clean, install, run tests, skip if fail
mvn clean install -DfailIfNoTests=false

# CI/CD: Clean, install, run all tests with detailed output
mvn clean install -X

# Quick test run
mvn test -DskipTests=false
```

### Feature Testing
```bash
# Test single feature
mvn test -Dcucumber.features="src/test/resources/features/login.feature" -Dbrowser=chrome

# Test with specific tags
mvn test -Dcucumber.filter.tags="@smoke" -Dbrowser=firefox
```

### Report Generation
```bash
# Generate Cucumber report
mvn clean test -Dcucumber.features="src/test/resources/features"

# Generate with full reports
mvn clean test && mvn site

# View reports
# Open: target/cucumber-reports/cucumber.html
# Open: target/surefire-reports/index.html
```

## Profile-Based Testing

### Run with Profile
```bash
# List available profiles
mvn help:active-profiles

# Run with specific profile
mvn test -P profile-name

# Run with multiple profiles
mvn test -P profile1,profile2
```

## Useful Aliases (Add to .bashrc or .bat)

### Linux/Mac (.bashrc)
```bash
alias mvn-test='mvn clean test'
alias mvn-install='mvn clean install -DskipTests'
alias mvn-full='mvn clean install'
alias mvn-debug='mvn clean test -X'
```

### Windows (Command Prompt)
```bat
doskey mvn-test=mvn clean test
doskey mvn-install=mvn clean install -DskipTests
doskey mvn-full=mvn clean install
doskey mvn-debug=mvn clean test -X
```

## Performance Optimization

### Run Tests Faster
```bash
# Skip tests
mvn clean install -DskipTests

# Run in offline mode (dependencies cached)
mvn test -o

# Parallel build threads
mvn -T 1C test  # 1 thread per core

# Skip javadoc
mvn clean install -Dmaven.javadoc.skip=true
```

---

## Quick Reference Table

| Command | Purpose |
|---------|---------|
| `mvn test` | Run all tests |
| `mvn clean install` | Clean, install dependencies |
| `mvn clean test` | Clean and run tests |
| `mvn test -DskipTests` | Build without tests |
| `mvn dependency:tree` | Show dependency tree |
| `mvn -v` | Show Maven version |
| `mvn help:describe` | Get help on goal |

---

**Pro Tips:**
1. Use `-q` flag for cleaner output
2. Use `-X` flag for debugging
3. Use `-o` flag when offline (after first build)
4. Set MAVEN_OPTS for memory issues
5. Use batch mode (-B) for CI/CD pipelines

```bash
# CI/CD friendly command
mvn clean test -B -DskipTests=false -Dcucumber.features="src/test/resources/features"
```
