package jack;

/**
 * Custom exception class for the Jack application.
 * Used to represent application-specific exceptions with custom error messages.
 */
public class Excep extends Exception {
    /**
     * Constructs a new Excep with the specified error message.
     * @param message The error message for this exception.
     */
    public Excep(String message) {
        super(message);
    }
}

