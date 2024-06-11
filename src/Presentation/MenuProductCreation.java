package Presentation;

/**
 * Enumeration representing the menu options for product creation and shop management.
 * This enum provides options for actions related to creating shops, expanding or reducing shop catalogs, and returning to the previous menu.
 */
public enum MenuProductCreation {
    /**
     * Option to create a new shop.
     * This action initiates the process of adding a new shop to the system.
     */
    CREATE_SHOP,

    /**
     * Option to expand a shop's catalog.
     * This action allows the addition of new products to an existing shop's catalog.
     */
    EXPAND_SHOP_CATALOGUE,

    /**
     * Option to reduce a shop's catalog.
     * This action allows the removal of products from an existing shop's catalog.
     */
    REDUCE_SHOP_CATALOGUE,

    /**
     * Option to go back to the previous menu.
     * This action cancels the current operation and returns to the previous menu.
     */
    BACK,
}
