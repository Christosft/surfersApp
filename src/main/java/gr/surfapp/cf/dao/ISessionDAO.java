package gr.surfapp.cf.dao;


import gr.surfapp.cf.dao.exceptions.SessionDaoException;
import gr.surfapp.cf.model.Session;

import java.util.List;

public interface ISessionDAO {

    // Basic Services
    Session insert(Session session) throws SessionDaoException;
    Session update(Session session) throws SessionDaoException;
    void delete(Integer id) throws SessionDaoException;
    List<Session> getAll() throws SessionDaoException;

    // Queries
    Session getByUuid(String uuid) throws SessionDaoException;
    Session getById(Integer id) throws SessionDaoException;
    List<Session> getBySurfspot(String Surfspot) throws SessionDaoException;
}
