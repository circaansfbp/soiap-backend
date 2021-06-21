package pt.fmbp.soiapbackend.exception;

public class ResourceDeletionNotPossibleException extends RuntimeException {
    public ResourceDeletionNotPossibleException(String message) {
        super(message);
    }
}
