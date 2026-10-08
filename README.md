# Selenium Java Automation Framework

A scalable UI test automation framework built using **Java, Selenium WebDriver, TestNG, Maven, Page Object Model (POM), and Extent Reports**.

This project demonstrates an industry-oriented approach to automating an e-commerce application, including login, product selection, cart validation, checkout, and order confirmation.

---

## 🚀 Tech Stack

| Technology | Purpose |
|------------|---------|
| Java | Programming language |
| Selenium WebDriver | Web UI automation |
| TestNG | Test execution and test management |
| Maven | Build and dependency management |
| Page Object Model | Framework design pattern |
| Apache POI | Excel test data handling |
| JSON | Test data/configuration |
| Log4j2 | Application/test logging |
| Extent Reports | HTML test reporting |
| Allure Reports | Test reporting |
| Git | Version control |
| GitHub | Source code management |

---

## 📌 Project Features

- Selenium WebDriver based UI automation
- Java + TestNG test framework
- Maven project structure
- Page Object Model (POM)
- Reusable page components
- Explicit waits for synchronization
- Data-driven testing
- TestNG DataProviders
- Excel test data handling
- JSON test data support
- Browser configuration through properties
- Chrome and Edge browser support
- Logging using Log4j2
- Extent HTML reporting
- Allure reporting support
- Screenshots for failed tests
- TestNG listeners
- Maven command-line execution
- Git/GitHub integration

---

## 🏗️ Project Structure

```text
Maven_Project
│
├── src
│   │
│   ├── main
│   │   ├── java
│   │   │   ├── PageObjects
│   │   │   ├── AbstractComponents
│   │   │   └── Utilities
│   │   │
│   │   └── resources
│   │       └── GlobalData.properties
│   │
│   └── test
│       ├── java
│       │   ├── Tests
│       │   ├── TestComponents
│       │   └── Listeners
│       │
│       └── resources
│           ├── testdata.json
│           ├── loginData.xlsx
│           ├── payload.json
│           ├── sample.pdf
│           └── testng.xml
│
├── Reports
├── allure-results
├── logs
├── target
│
├── pom.xml
└── README.md
