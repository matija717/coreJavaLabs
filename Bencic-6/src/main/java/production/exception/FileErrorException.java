package production.exception;

public class FileErrorException extends RuntimeException{
    public FileErrorException() {
    }

    public FileErrorException(String message) {
        super(message);
    }
}
