package com.saucedemo.pages

import com.microsoft.playwright.Page
import io.qameta.allure.Step
import org.slf4j.LoggerFactory

class LoginPage(private val page: Page) {

    private val logger = LoggerFactory.getLogger(LoginPage::class.java)

    private val usernameInput = page.locator("[data-test=username]")
    private val passwordInput = page.locator("[data-test=password]")
    private val loginButton = page.locator("[data-test=login-button]")
    private val error = page.locator("[data-test=error]")

    /**
     * Navigates to the SauceDemo login page.
     * @return The current LoginPage instance.
     */
    @Step("Navigate to login page")
    fun navigate(): LoginPage {
        logger.info("Navigating to login page: https://www.saucedemo.com/")
        page.navigate("https://www.saucedemo.com/")
        return this
    }

    /**
     * Retrieves the text content of the error message element on the login page.
     * @return The error message as a String.
     */
    @Step("Get error message")
    fun getErrorMessage(): String {
        val message = error.textContent()
        logger.info("Retrieved error message: '$message'")
        return message
    }

    /**
     * Enters the provided username into the username input field.
     * @param username The username to enter.
     * @return The current LoginPage instance.
     */
    @Step("Enter username: {username}")
    fun enterUsername(username: String): LoginPage {
        logger.info("Entering username: '$username'")
        usernameInput.fill(username)
        return this
    }

    /**
     * Enters the provided password into the password input field.
     * The password is not logged for security reasons.
     * @param password The password to enter.
     * @return The current LoginPage instance.
     */
    @Step("Enter password")
    fun enterPassword(password: String): LoginPage {
        logger.info("Entering password (hidden for security)")
        passwordInput.fill(password)
        return this
    }

    /**
     * Clicks the login button on the login page.
     */
    @Step("Click login button")
    fun clickLoginButton() {
        logger.info("Clicking login button")
        loginButton.click()
    }

    /**
     * Performs a complete login action by navigating to the login page,
     * entering credentials, and clicking the login button.
     * @param username The username to use for login.
     * @param password The password to use for login.
     */
    @Step("Login as user: {username}")
    fun loginAs(username: String, password: String) {
        logger.info("Attempting to log in as user: '$username'")
        navigate()
        enterUsername(username)
        enterPassword(password)
        clickLoginButton()
        logger.info("Login attempt completed for user: '$username'")
    }
}
