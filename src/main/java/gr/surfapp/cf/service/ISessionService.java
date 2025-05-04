package gr.surfapp.cf.service;

import gr.surfapp.cf.dao.exceptions.SessionDaoException;
import gr.surfapp.cf.dto.SessionInsertDTO;
import gr.surfapp.cf.dto.SessionReadOnlyDTO;
import gr.surfapp.cf.dto.SessionUpdateDTO;
import gr.surfapp.cf.exceptions.SessionNotFoundException;

import java.util.List;

/**
 * Interface defining service-level operations for managing surf sessions.
 */

public interface ISessionService {

    SessionReadOnlyDTO insertSession(SessionInsertDTO dto) throws SessionDaoException;
    SessionReadOnlyDTO updateSession(Integer id, SessionUpdateDTO dto) throws SessionDaoException,  SessionNotFoundException;
    //SessionReadOnlyDTO getSessionByUuid(Integer id) throws SessionDaoException, SessionNotFoundException;

    SessionReadOnlyDTO getSessionByUuid(String uuid) throws SessionDaoException, SessionNotFoundException;

    SessionReadOnlyDTO getSessionById(Integer Id) throws SessionDaoException, SessionNotFoundException;

    void deleteSession(Integer id) throws SessionDaoException,  SessionNotFoundException;
    //List<SessionReadOnlyDTO> getAllSessions() throws SessionDaoException;
    List<SessionReadOnlyDTO> getSessionBySurfspot(String surfspots) throws SessionDaoException;

    SessionReadOnlyDTO updateSession(String uuid, SessionUpdateDTO updateDTO)  throws SessionDaoException,  SessionNotFoundException;;
}
