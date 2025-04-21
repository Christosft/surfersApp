package gr.surfapp.cf.exceptions;

import java.io.Serial;

public class SessionNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public SessionNotFoundException(String message) {
        super(message);
    }
}
