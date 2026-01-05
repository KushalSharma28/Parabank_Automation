# 🏦 Parabank Automation

![Automation](https://img.shields.io/badge/Test-Automation-blue)
![Selenium](https://img.shields.io/badge/Selenium-WebDriver-green)
![Cypress](https://img.shields.io/badge/Cypress-JS-brightgreen)
![BDD](https://img.shields.io/badge/BDD-Cucumber-orange)
![Language](https://img.shields.io/badge/Languages-Java%20%7C%20Python%20%7C%20JavaScript-yellow)
![License](https://img.shields.io/badge/License-MIT-lightgrey)

---

## 📌 Project Overview

**Parabank Automation** is an end-to-end test automation framework built to validate the core functionalities of the **ParaBank demo banking application**.
The project is designed to demonstrate **real-world test automation practices**, including **BDD (Behavior-Driven Development)**, **cross-language support**, and **scalable test architecture**.

This repository is ideal for:

* Learning Selenium & Cypress automation
* Practicing BDD with Cucumber
* Demonstrating automation skills for QA portfolios

---

## 📑 Table of Contents

* [Tech Stack](#-tech-stack)
* [Project Structure](#-project-structure)
* [Features Covered](#-features-covered)
* [Installation & Setup](#-installation--setup)
* [Running the Tests](#-running-the-tests)
* [Usage Examples](#-usage-examples)
* [Test Run Visuals](#-test-run-visuals)
* [Best Practices Followed](#-best-practices-followed)
* [Future Enhancements](#-future-enhancements)
* [Contributing](#-contributing)
* [License](#-license)

---

## 🧰 Tech Stack

| Tool / Technology      | Purpose                      |
| ---------------------- | ---------------------------- |
| **Selenium WebDriver** | Web UI automation            |
| **Cypress**            | Fast, modern browser testing |
| **Cucumber (BDD)**     | Gherkin-based test scenarios |
| **Java**               | Selenium test framework      |
| **Python**             | Selenium automation          |
| **JavaScript**         | Cypress automation           |
| **Git & GitHub**       | Version control              |

---

## 📁 Project Structure

```
Parabank_Automation/
│
├── selenium-java/
│   ├── src/test/java/
│   ├── features/
│   └── runners/
│
├── selenium-python/
│   ├── tests/
│   └── utilities/
│
├── cypress/
│   ├── e2e/
│   ├── fixtures/
│   └── support/
│
├── README.md
└── requirements / pom.xml
```

---

## ✅ Features Covered

* 🧾 User Registration
* 🔐 User Login & Logout
* 🏦 Account Creation
* 💰 Account Overview
* 🔄 Basic banking workflows
* 📋 BDD Scenarios using Gherkin

---

## ⚙️ Installation & Setup

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/KushalSharma28/Parabank_Automation.git
cd Parabank_Automation
```

---

### 2️⃣ Setup for Selenium (Java)

* Install **Java (JDK 8+)**
* Install **Maven**

```bash
mvn clean install
```

---

### 3️⃣ Setup for Selenium (Python)

```bash
pip install -r requirements.txt
```

---

### 4️⃣ Setup for Cypress

```bash
npm install
```

---

## ▶️ Running the Tests

### 🔹 Selenium (Java)

```bash
mvn test
```

---

### 🔹 Selenium (Python)

```bash
pytest
```

---

### 🔹 Cypress

```bash
npx cypress open
```

or

```bash
npx cypress run
```

---

## 🧪 Usage Examples

### Example Gherkin Scenario

```gherkin
Feature: User Login

  Scenario: Successful login with valid credentials
    Given user is on the Parabank login page
    When user enters valid username and password
    Then user should be redirected to the dashboard
```

---

### Example Cypress Test

```javascript
it('User Login Test', () => {
  cy.visit('/login')
  cy.get('#username').type('testuser')
  cy.get('#password').type('password')
  cy.get('button').click()
  cy.contains('Accounts Overview').should('be.visible')
})
```

---

## 📸 Test Run Visuals

> *Add screenshots or GIFs here to showcase test execution*

### 🔹 Cypress Test Execution

![Cypress Run](screenshots/cypress-run.png)

### 🔹 Selenium Test Report

![Selenium Report](screenshots/selenium-report.png)

*(Create a `screenshots/` folder and add images for better presentation)*

---

## 🧠 Best Practices Followed

* ✔ Page Object Model (POM)
* ✔ Reusable utilities & helpers
* ✔ BDD-driven test scenarios
* ✔ Clean folder structure
* ✔ Scalable automation design

---

## 🚀 Future Enhancements

* API automation integration
* CI/CD pipeline (GitHub Actions / Jenkins)
* Parallel test execution
* Advanced reporting (Allure / Extent)
* Data-driven testing

---

## 🤝 Contributing

Contributions are welcome!
If you’d like to improve this project:

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Open a Pull Request

---

### ⭐ If you like this project, don’t forget to star the repository!
