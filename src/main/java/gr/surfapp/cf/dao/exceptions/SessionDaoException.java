package gr.surfapp.cf.dao.exceptions;

import java.io.Serial;

public class SessionDaoException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public SessionDaoException(String message) {

        super(message);
    }
}
