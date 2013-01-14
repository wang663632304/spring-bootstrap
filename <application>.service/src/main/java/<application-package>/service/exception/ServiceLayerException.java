package <application-group-id>.service.exception;

import <application-group-id>.exception.ApplicationException;

public class ServiceLayerException extends ApplicationException {
    public ServiceLayerException(String message) {
        super(message);
    }

    public ServiceLayerException(Exception ex) {
        super(ex);
    }

    public ServiceLayerException(String message, Exception ex) {
        super(message, ex);
    }
}
