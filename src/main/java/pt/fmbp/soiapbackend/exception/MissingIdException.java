package pt.fmbp.soiapbackend.exception;

public class MissingIdException extends RuntimeException {
    public MissingIdException(String message) {
        super(message);
    }
}
