package gr.surfapp.cf.dao;

import com.mysql.cj.Session;
import gr.surfapp.cf.dao.exceptions.SessionDaoException;

import java.util.List;

public interface ISessionDao {

    // Basic Services
    Session insert(Session session) throws SessionDaoException;
    Session update(Session session) throws SessionDaoException;
    void delete(String uuid) throws SessionDaoException;
    List<Session> getAll() throws SessionDaoException;

    // Queries
    Session getByUUID(String uuid) throws SessionDaoException;
    List<Session> getBySurfspot(String Surfspot) throws SessionDaoException;
}
