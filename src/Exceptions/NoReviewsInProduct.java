package Exceptions;

/**
 * Exception thrown when a requested product has no reviews.
 * This can be used to signal an error when attempting to access reviews
 * for a product that does not have any reviews.
 */
public class NoReviewsInProduct extends Exception {

    /**
     * Constructs a new NoReviewsInProduct exception with a default error message.
     * The message indicates that there are no reviews available for the product in question.
     */
    public NoReviewsInProduct() {
        super("There are no reviews for this product.");
    }
}
