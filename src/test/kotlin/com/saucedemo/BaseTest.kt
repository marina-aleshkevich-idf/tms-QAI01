package com.saucedemo

import com.microsoft.playwright.Browser
import com.microsoft.playwright.BrowserContext
import com.microsoft.playwright.Page
import com.microsoft.playwright.Playwright
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestInstance

import org.slf4j.LoggerFactory

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class BaseTest {

    private val logger = LoggerFactory.getLogger(BaseTest::class.java)

    private lateinit var playwright: Playwright
    private lateinit var browser: Browser
    protected lateinit var context: BrowserContext
    protected lateinit var page: Page

    /**
     * Initializes Playwright and launches a new Chromium browser instance before all tests.
     * The browser runs in non-headless mode for visibility during execution.
     */
    @BeforeAll
    fun launchBrowser() {
        logger.info("Launching browser...")
        playwright = Playwright.create()
        browser = playwright.chromium().launch(
            com.microsoft.playwright.BrowserType.LaunchOptions().setHeadless(false)
        )
        logger.info("Browser launched.")
    }

    /**
     * Closes the Playwright instance after all tests have completed.
     */
    @AfterAll
    fun closeBrowser() {
        logger.info("Closing browser...")
        playwright.close()
        logger.info("Browser closed.")
    }

    /**
     * Creates a new browser context and page before each test.
     * This ensures each test runs in an isolated environment.
     */
    @BeforeEach
    fun createContextAndPage() {
        logger.info("Creating new browser context and page...")
        context = browser.newContext()
        page = context.newPage()
        logger.info("Browser context and page created.")
    }

    /**
     * Closes the browser context after each test.
     * This cleans up the test environment.
     */
    @AfterEach
    fun closeContext() {
        logger.info("Closing browser context...")
        context.close()
        logger.info("Browser context closed.")
    }
}
