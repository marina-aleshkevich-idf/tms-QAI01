package com.saucedemo.pages

import com.microsoft.playwright.Page
import io.qameta.allure.Step
import org.slf4j.LoggerFactory

class CheckoutPage(private val page: Page) {

    private val logger = LoggerFactory.getLogger(CheckoutPage::class.java)

    private val firstNameInput = page.locator("[data-test=firstName]")
    private val lastNameInput = page.locator("[data-test=lastName]")
    private val postalCodeInput = page.locator("[data-test=postalCode]")
    private val continueButton = page.locator("[data-test=continue]")
    private val finishButton = page.locator("[data-test=finish]")
    private val thankYouHeader = page.locator(".complete-header")

    /**
     * Navigates directly to the shopping cart page.
     */
    @Step("Navigate to cart page")
    fun navigateToCart() {
        logger.info("Navigating to cart page.")
        page.navigate("https://www.saucedemo.com/cart.html")
    }

    /**
     * Clicks the 'Checkout' button on the cart page to proceed to the checkout information step.
     */
    @Step("Click checkout button")
    fun clickCheckout() {
        logger.info("Clicking checkout button.")
        page.locator("[data-test=checkout]").click()
    }

    /**
     * Enters the provided personal information into the checkout form fields and clicks 'Continue'.
     * @param firstName The first name to enter.
     * @param lastName The last name to enter.
     * @param postalCode The postal code to enter.
     */
    @Step("Enter checkout information: {firstName}, {lastName}, {postalCode}")
    fun enterCheckoutInformation(firstName: String, lastName: String, postalCode: String) {
        logger.info("Entering checkout information - First Name: '$firstName', Last Name: '$lastName', Postal Code: '$postalCode'")
        firstNameInput.fill(firstName)
        lastNameInput.fill(lastName)
        postalCodeInput.fill(postalCode)
        continueButton.click()
        logger.info("Checkout information entered and continue button clicked.")
    }

    /**
     * Clicks the 'Finish' button on the checkout overview page to complete the order.
     */
    @Step("Click finish button")
    fun clickFinish() {
        logger.info("Clicking finish button.")
        finishButton.click()
    }

    /**
     * Retrieves the "Thank you" message displayed on the checkout complete page.
     * @return The thank you message as a String.
     */
    @Step("Get thank you message")
    fun getThankYouMessage(): String {
        val message = thankYouHeader.textContent()
        logger.info("Retrieved thank you message: '$message'")
        return message
    }
}
