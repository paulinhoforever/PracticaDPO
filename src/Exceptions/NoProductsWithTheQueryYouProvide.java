package Exceptions;

/**
 * Exception thrown when no products match the provided query.
 * This exception indicates that a search operation has failed to find any products
 * that match the criteria specified by the user.
 */
public class NoProductsWithTheQueryYouProvide extends Exception {

    /**
     * Constructs a new NoProductsWithTheQueryYouProvide exception with a default error message.
     * The message indicates that no products exist with the provided information.
     */
    public NoProductsWithTheQueryYouProvide() {
        super("No products exist with the information you provided.");
    }
}
