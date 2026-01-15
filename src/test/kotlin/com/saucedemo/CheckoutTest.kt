package com.saucedemo

import com.saucedemo.pages.CheckoutPage
import com.saucedemo.pages.LoginPage
import com.saucedemo.pages.ProductsPage
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.qameta.allure.Epic
import io.qameta.allure.Feature
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.slf4j.LoggerFactory

@Epic("E-commerce Workflow")
@Feature("Checkout Process")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CheckoutTest : BaseTest() {

    private val logger = LoggerFactory.getLogger(CheckoutTest::class.java)

    private lateinit var loginPage: LoginPage
    private lateinit var productsPage: ProductsPage
    private lateinit var checkoutPage: CheckoutPage

    /**
     * Sets up the test environment before each test method.
     * Initializes the browser context and page, and instantiates the LoginPage, ProductsPage, and CheckoutPage Page Objects.
     */
    @BeforeEach
    fun setUp() {
        logger.info("Setting up for checkout test...")
        super.createContextAndPage()
        loginPage = LoginPage(page)
        productsPage = ProductsPage(page)
        checkoutPage = CheckoutPage(page)
        logger.info("Login, Products, and Checkout pages initialized.")
    }

    /**
     * Verifies the successful completion of the checkout process after adding an item to the cart.
     * This test covers login, adding an item, navigating to cart, entering checkout info, and finalizing the purchase.
     */
    @Test
    fun `successful checkout of an item`() {
        logger.info("Starting 'successful checkout of an item' test.")
        loginPage.loginAs("standard_user", "secret_sauce")
        productsPage.addItemToCart("Sauce Labs Backpack")
        productsPage.getCartItemsCount() shouldBe "1"

        checkoutPage.navigateToCart()
        page.url() shouldContain "cart.html"
        checkoutPage.clickCheckout()
        page.url() shouldContain "checkout-step-one.html"

        checkoutPage.enterCheckoutInformation("John", "Doe", "12345")
        page.url() shouldContain "checkout-step-two.html"

        checkoutPage.clickFinish()
        page.url() shouldContain "checkout-complete.html"
        checkoutPage.getThankYouMessage() shouldBe "Thank you for your order!"
        logger.info("'successful checkout of an item' test completed.")
    }

    /**
     * Verifies that a previously added item is correctly displayed in the cart before proceeding to checkout.
     * This test covers login, adding an item, and navigating to the cart page to verify its presence.
     */
    @Test
    fun `verify item in cart before checkout`() {
        logger.info("Starting 'verify item in cart before checkout' test.")
        loginPage.loginAs("standard_user", "secret_sauce")
        productsPage.addItemToCart("Sauce Labs Bike Light")
        productsPage.getCartItemsCount() shouldBe "1"

        checkoutPage.navigateToCart()
        page.url() shouldContain "cart.html"
        // Verify the item is visible in the cart page
        page.locator(".inventory_item_name:has-text('Sauce Labs Bike Light')").isVisible shouldBe true
        logger.info("'verify item in cart before checkout' test completed.")
    }
}
