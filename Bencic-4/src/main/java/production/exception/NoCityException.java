package production.exception;

public class NoCityException extends RuntimeException {
    public NoCityException() {
    }

    public NoCityException(String message) {
        super(message);
    }

    public NoCityException(String message, Throwable cause) {
        super(message, cause);
    }
}
