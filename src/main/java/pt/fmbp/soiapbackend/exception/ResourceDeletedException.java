package pt.fmbp.soiapbackend.exception;

public class ResourceDeletedException extends RuntimeException {
    public ResourceDeletedException(String message) {
        super(message);
    }
}
