package com.saucedemo

import com.saucedemo.pages.LoginPage
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.qameta.allure.Epic
import io.qameta.allure.Feature
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.slf4j.LoggerFactory

@Epic("User Management")
@Feature("Login")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LoginTest : BaseTest() {

    private val logger = LoggerFactory.getLogger(LoginTest::class.java)

    private lateinit var loginPage: LoginPage

    /**
     * Sets up the test environment before each test method.
     * Initializes the browser context and page, and instantiates the LoginPage Page Object.
     */
    @BeforeEach
    fun setUp() {
        logger.info("Setting up for login test...")
        super.createContextAndPage()
        loginPage = LoginPage(page)
        logger.info("Login page initialized.")
    }

    /**
     * Verifies that a standard user can successfully log in and is redirected to the inventory page.
     */
    @Test
    fun `successful login`() {
        logger.info("Starting 'successful login' test.")
        loginPage.loginAs("standard_user", "secret_sauce")
        page.url() shouldContain "inventory.html"
        logger.info("'successful login' test completed.")
    }

    /**
     * Verifies that a locked-out user cannot log in and receives the appropriate error message.
     */
    @Test
    fun `locked out user login`() {
        logger.info("Starting 'locked out user login' test.")
        loginPage.loginAs("locked_out_user", "secret_sauce")
        loginPage.getErrorMessage() shouldBe "Epic sadface: Sorry, this user has been locked out."
        logger.info("'locked out user login' test completed.")
    }
}
