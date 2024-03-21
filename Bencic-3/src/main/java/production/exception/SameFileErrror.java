package production.exception;

public class SameFileErrror extends RuntimeException {
    public SameFileErrror() {
    }

    public SameFileErrror(String message) {
        super(message);
    }

    public SameFileErrror(String message, Throwable cause) {
        super(message, cause);
    }
}
