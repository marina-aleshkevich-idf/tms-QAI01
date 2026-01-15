# SauceDemo Test Automation Framework

This project provides an automated test suite for the SauceDemo e-commerce web application (`https://www.saucedemo.com/`), demonstrating best practices in test automation using Kotlin, Playwright, JUnit 5, Kotest, Allure reporting, and SLF4J/Logback for comprehensive logging.

## Technologies Used

*   **Kotlin**: Primary programming language.
*   **Gradle Kotlin DSL**: Build automation tool.
*   **Playwright**: Fast and reliable browser automation library.
*   **JUnit 5**: Test framework for writing and running tests.
*   **Kotest**: A flexible and extensive testing toolkit for Kotlin, used here for fluent assertions.
*   **Allure Report**: A flexible lightweight test report tool that shows a clear and concise representation of test execution.
*   **SLF4J & Logback**: Logging facade and its implementation for structured and detailed logging.

## Project Structure

The project follows a standard Gradle and Page Object Model (POM) structure:

```
tms-QAI01/
├── build.gradle.kts          // Gradle build script with dependencies and configurations
├── gradlew                   // Gradle wrapper for Linux/macOS
├── gradlew.bat               // Gradle wrapper for Windows
├── README.md                 // Project documentation
└── src/
    └── test/
        ├── kotlin/
        │   └── com/
        │       └── saucedemo/
        │           ├── BaseTest.kt         // Base class for all tests, handles Playwright setup/teardown and logging initialization
        │           ├── LoginTest.kt        // Tests related to user login functionality
        │           ├── ProductsTest.kt     // Tests related to product interaction (e.g., adding to cart)
        │           └── CheckoutTest.kt     // Tests related to the checkout process
        │           └── pages/
        │               ├── LoginPage.kt    // Page Object for the SauceDemo login page
        │               ├── ProductsPage.kt // Page Object for the SauceDemo products page
        │               └── CheckoutPage.kt // Page Object for the SauceDemo checkout pages (step one, step two, complete)
        └── resources/
            └── logback.xml     // Logback configuration for detailed logging
```

## Getting Started

### Prerequisites

*   **Java Development Kit (JDK)**: Version 17 or higher.
*   **Gradle**: (Optional, as `gradlew` is provided)
*   **Internet Connection**: To download dependencies and access the SauceDemo website.

### Installation

1.  **Clone the repository:**
    ```bash
    git clone <repository_url>
    cd tms-QAI01
    ```
2.  **Ensure Playwright browsers are installed:**
    Playwright requires browser binaries. Run the following command to install them:
    ```bash
    ./gradlew playwrightInstall
    ```
    (On Windows, use `gradlew.bat playwrightInstall`)

## Running Tests

To run all tests using Gradle:

```bash
./gradlew clean test
```
(On Windows, use `gradlew.bat clean test`)

This command will:
*   Clean the build directory.
*   Compile the test sources.
*   Execute all tests.
*   Generate Allure results in the `build/allure-results` directory.
*   Output logs to the console as configured in `logback.xml`.

## Allure Reports

After running the tests, Allure reports can be generated and served locally.

### Generate and Serve Report

```bash
./gradlew allureServe
```
(On Windows, use `gradlew.bat allureServe`)

This command will:
*   Generate a comprehensive Allure HTML report from the `build/allure-results` data.
*   Open the report automatically in your default web browser.

You can also generate the report without serving it:
```bash
./gradlew allureReport
```
The report will be located in the `build/reports/allure-report` directory.

## Best Practices Demonstrated

*   **Page Object Model (POM)**: Encapsulates UI elements and interactions into classes, making tests more readable, maintainable, and reusable.
*   **Structured Test Architecture**: Clear separation of concerns with a `BaseTest` for common setup, Page Objects for UI interactions, and dedicated test classes for specific functionalities.
*   **Comprehensive Logging**: Integration with SLF4J and Logback ensures that all significant actions and events during test execution are logged, aiding in debugging and post-execution analysis.
*   **Rich Reporting with Allure**: Utilizes Allure annotations (`@Epic`, `@Feature`, `@Story`, `@Step`) to generate detailed and interactive test reports, providing clear insights into test results, execution steps, and failures.
*   **Fluent Assertions**: Using Kotest for assertions makes test expectations more expressive and readable.

This framework is designed to be easily extensible and maintainable for future test automation efforts on the SauceDemo application.
