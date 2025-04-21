package gr.surfapp.cf.dao.exceptions;

import java.io.Serial;
import java.sql.SQLException;

public class SessionDaoException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public SessionDaoException(String s) {

        super(s);
    }
}
