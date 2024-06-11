package Exceptions;

/**
 * Exception thrown when there are no products available in a store's catalogue.
 * This exception is used to indicate that the catalogue is empty and therefore
 * no products can be listed or accessed.
 */
public class NoProductsInCatalogue extends Exception {

    /**
     * Constructs a new NoProductsInCatalogue exception with a default error message.
     * The message indicates that there are no products in the catalogue.
     */
    public NoProductsInCatalogue() {
        super("There are no products in this catalogue.");
    }
}
