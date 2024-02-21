package hr.java.shop.bencic7.production.exception;

public class NoItemException extends RuntimeException{
    public NoItemException() {
    }

    public NoItemException(String message) {
        super(message);
    }
}
