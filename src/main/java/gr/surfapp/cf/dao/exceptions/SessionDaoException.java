package gr.surfapp.cf.dao.exceptions;

import java.io.Serial;

/**
 * Custom exception class for handling errors related to session data access operations.
 * This class extends {@link RuntimeException}, and it is thrown when there is an issue
 * interacting with session data in the database or other data sources.
 *
 * It is unchecked, meaning it does not require explicit handling (e.g., try-catch block)
 * by the caller, but can be caught if necessary.
 */


public class SessionDaoException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public SessionDaoException(String s) {

        super(s);
    }
}
