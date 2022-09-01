package studentRegisterExample.model;

public class NotActiveSemesterException extends RuntimeException{

    public NotActiveSemesterException(String message) {
        super(message);
    }
}
