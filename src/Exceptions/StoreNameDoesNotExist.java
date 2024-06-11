package Exceptions;

/**
 * Exception thrown when an attempt is made to access a store with a name
 * that does not exist in the system. This exception is used to indicate
 * that the specified store name could not be found.
 */
public class StoreNameDoesNotExist extends Exception {

    /**
     * Constructs a new StoreNameDoesNotExist exception with a default error message.
     * The message indicates that the specified store name does not exist in the system.
     */
    public StoreNameDoesNotExist() {
        super("The name does not exist!");
    }
}
