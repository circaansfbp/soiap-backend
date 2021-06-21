package pt.fmbp.soiapbackend.exception;

// Excepción para manejar el conflicto de horarios.
public class AppointmentConflictException extends RuntimeException {

    public AppointmentConflictException(String message) {
        super(message);
    }

    public AppointmentConflictException(String message, Throwable cause) {
        super(message, cause);
    }
}
