package Exceptions;

/**
 * Exception thrown when an attempt is made to create a store with a name
 * that already exists in the system. This is used to ensure store names are unique.
 */
public class StoreNameAlreadyExists extends Exception {

    /**
     * Constructs a new StoreNameAlreadyExists exception with a default error message.
     * The message indicates that the store name already exists and suggests using a different name.
     */
    public StoreNameAlreadyExists() {
        super("Existing name! Please, use another one.");
    }
}
