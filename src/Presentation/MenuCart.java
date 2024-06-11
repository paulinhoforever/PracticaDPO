package Presentation;

/**
 * Enumeration representing the menu options for the shopping cart.
 * This enum provides options for actions that can be performed on the shopping cart,
 * such as checking out, clearing the cart, and going back to the previous menu.
 */
public enum MenuCart {
    /**
     * Option to proceed with the checkout process.
     * This action finalizes the purchase of items in the cart.
     */
    CHECKOUT,

    /**
     * Option to clear all items from the shopping cart.
     * This action removes all items from the cart.
     */
    CLEAR_CART,

    /**
     * Option to go back to the previous menu.
     * This action cancels the current operation and returns to the previous menu.
     */
    BACK,
}
