package Exceptions;

/**
 * Exception thrown when a requested product does not exist.
 * This can be used to signal an error in product-related operations
 * where the specified product could not be found.
 */
public class ProductDoesNotExist extends Exception {

    /**
     * Constructs a new ProductDoesNotExist exception with a default error message.
     * The message indicates that the product in question does not exist.
     */
    public ProductDoesNotExist() {
        super("This product does not exist.");
    }
}
