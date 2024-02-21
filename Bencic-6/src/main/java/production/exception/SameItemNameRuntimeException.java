package production.exception;

/**
 * The SameItemNameRuntimeException class is a runtime exception that is thrown when an attempt is made to
 * add an item with the same name to a collection or data structure, and it's not allowed.
 */
public class SameItemNameRuntimeException extends RuntimeException {
    /**
     * Constructs a new SameItemNameRuntimeException with no detail message.
     */
    public SameItemNameRuntimeException() {
    }

    /**
     * Constructs a new SameItemNameRuntimeException with the specified detail message.
     *
     * @param message The detail message explaining the runtime exception.
     */
    public SameItemNameRuntimeException(String message) {
        super(message);
    }

    /**
     * Constructs a new SameItemNameRuntimeException with the specified detail message and a cause.
     *
     * @param message The detail message explaining the runtime exception.
     * @param cause   The cause of the runtime exception.
     */
    public SameItemNameRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
