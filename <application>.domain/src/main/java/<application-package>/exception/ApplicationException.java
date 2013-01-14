package <application-group-id>.exception;

public class ApplicationException extends RuntimeException {
    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(Exception ex) {
        super(ex);
    }

    public ApplicationException(String message, Exception ex) {
        super(message, ex);
    }
}
