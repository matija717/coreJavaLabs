package hr.java.shop.bencic8.production.exception;

public class NoCategoryException extends RuntimeException{
    public NoCategoryException() {
    }

    public NoCategoryException(String message) {
        super(message);
    }
}
