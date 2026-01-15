package com.saucedemo.pages

import com.microsoft.playwright.Page
import io.qameta.allure.Step
import org.slf4j.LoggerFactory

class ProductsPage(private val page: Page) {

    private val logger = LoggerFactory.getLogger(ProductsPage::class.java)

    private val inventoryItems = page.locator(".inventory_item")
    private val shoppingCartLink = page.locator(".shopping_cart_link")

    /**
     * Adds a specified item to the shopping cart by clicking its corresponding button.
     * @param itemName The visible name of the item to add to the cart.
     */
    @Step("Add item to cart: {itemName}")
    fun addItemToCart(itemName: String) {
        logger.info("Adding item '$itemName' to cart")
        page.locator(".inventory_item:has-text('$itemName') button").click()
        logger.info("Item '$itemName' added to cart")
    }

    /**
     * Retrieves the current count of items displayed in the shopping cart icon.
     * @return The text content representing the number of items in the cart as a String.
     */
    @Step("Get cart items count")
    fun getCartItemsCount(): String {
        val count = shoppingCartLink.textContent()
        logger.info("Retrieved cart items count: '$count'")
        return count
    }
}
