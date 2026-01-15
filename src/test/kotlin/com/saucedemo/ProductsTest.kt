package com.saucedemo

import com.saucedemo.pages.LoginPage
import com.saucedemo.pages.ProductsPage
import io.kotest.matchers.shouldBe
import io.qameta.allure.Epic
import io.qameta.allure.Feature
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.slf4j.LoggerFactory

@Epic("E-commerce Workflow")
@Feature("Product Interaction")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductsTest : BaseTest() {

    private val logger = LoggerFactory.getLogger(ProductsTest::class.java)

    private lateinit var loginPage: LoginPage
    private lateinit var productsPage: ProductsPage

    /**
     * Sets up the test environment before each test method.
     * Initializes the browser context and page, and instantiates the LoginPage and ProductsPage Page Objects.
     */
    @BeforeEach
    fun setUp() {
        logger.info("Setting up for product interaction test...")
        super.createContextAndPage()
        loginPage = LoginPage(page)
        productsPage = ProductsPage(page)
        logger.info("Login and Products pages initialized.")
    }

    /**
     * Verifies that an item can be successfully added to the cart and the cart count updates correctly.
     */
    @Test
    fun `add item to cart`() {
        logger.info("Starting 'add item to cart' test.")
        loginPage.loginAs("standard_user", "secret_sauce")
        productsPage.addItemToCart("Sauce Labs Backpack")
        productsPage.getCartItemsCount() shouldBe "1"
        logger.info("'add item to cart' test completed.")
    }
}
