package hr.java.shop.bencic8.production.exception;

/**
 * The SameItemException class is an exception that is thrown when an attempt is made to add an item to a collection
 * or data structure, but the same item already exists and is not allowed.
 */
public class SameItemException extends Exception {
    /**
     * Constructs a new SameItemException with no detail message.
     */
    public SameItemException() {
    }

    /**
     * Constructs a new SameItemException with the specified detail message.
     *
     * @param message The detail message explaining the exception.
     */
    public SameItemException(String message) {
        super(message);
    }

    /**
     * Constructs a new SameItemException with the specified detail message and a cause.
     *
     * @param message The detail message explaining the exception.
     * @param cause   The cause of the exception.
     */
    public SameItemException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new SameItemException with the specified cause.
     *
     * @param cause The cause of the exception.
     */
    public SameItemException(Throwable cause) {
        super(cause);
    }
}
