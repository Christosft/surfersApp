package gr.surfapp.cf.exceptions;

import java.io.Serial;

/**
 * Custom exception to be thrown when a session cannot be found in the database.
 * Extends {@link RuntimeException} to allow unchecked exception handling.
 */

public class SessionNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public SessionNotFoundException(String message) {
        super(message);
    }
}
