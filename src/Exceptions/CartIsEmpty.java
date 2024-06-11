package Exceptions;

/**
 * The `CartIsEmpty` exception is thrown when attempting to perform an operation on an empty cart.
 */

public class CartIsEmpty extends Exception{

    /**
     * Constructs a new `CartIsEmpty` exception with a default error message.
     */
    public CartIsEmpty(){ super("Cart is empty. Add products to manage what it's inside.");}
}
