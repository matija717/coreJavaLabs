package hr.java.shop.bencic8.production.exception;

public class NoCityException extends RuntimeException{
    public NoCityException() {
    }

    public NoCityException(String message) {
        super(message);
    }
}
