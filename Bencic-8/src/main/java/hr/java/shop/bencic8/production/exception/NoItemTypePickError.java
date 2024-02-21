package hr.java.shop.bencic8.production.exception;

public class NoItemTypePickError extends RuntimeException{
    public NoItemTypePickError() {
    }

    public NoItemTypePickError(String message) {
        super(message);
    }

    public NoItemTypePickError(String message, Throwable cause) {
        super(message, cause);
    }

    public NoItemTypePickError(Throwable cause) {
        super(cause);
    }

    public NoItemTypePickError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
