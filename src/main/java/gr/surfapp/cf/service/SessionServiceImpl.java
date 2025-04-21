package gr.surfapp.cf.service;

import gr.surfapp.cf.dao.ISessionDAO;
import gr.surfapp.cf.dao.exceptions.SessionDaoException;
import gr.surfapp.cf.dto.SessionInsertDTO;
import gr.surfapp.cf.dto.SessionReadOnlyDTO;
import gr.surfapp.cf.dto.SessionUpdateDTO;
import gr.surfapp.cf.exceptions.SessionNotFoundException;
import gr.surfapp.cf.mapper.Mapper;
import gr.surfapp.cf.model.Session;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SessionServiceImpl implements ISessionService {

    private final ISessionDAO sessionDAO;

    public SessionServiceImpl(ISessionDAO sessionDAO) {
        this.sessionDAO = sessionDAO;
    }

    @Override
    public SessionReadOnlyDTO insertSession(SessionInsertDTO dto) throws SessionDaoException, SessionNotFoundException {
        Session session;
        Session insertedSession;

        try {
            session = Mapper.mapSessionInsertToModel(dto);

            insertedSession = sessionDAO.insert(session);

            return Mapper.mapSessionToReadOnlyDTO(insertedSession)
                    .orElse(null);
        } catch (SessionDaoException e) {

            throw e;
        }
    }

    @Override
    public SessionReadOnlyDTO updateSession(Integer id, SessionUpdateDTO dto)
            throws SessionDaoException, SessionNotFoundException {

        try {
            // 1. Check if session exists
            Session existingSession = sessionDAO.getById(id);
            if (existingSession == null) {
                throw new SessionNotFoundException("Session with id " + id + " does not exist");
            }

            // 2. Map the DTO to the Session model
            Session sessionToUpdate = Mapper.mapSessionUpdateToModel(dto);
            sessionToUpdate.setId(existingSession.getId());      // Keep ID
            sessionToUpdate.setUuid(existingSession.getUuid());  // Keep UUID

            // 3. Update the session
            Session updatedSession = sessionDAO.update(sessionToUpdate);

            // 4. Map to ReadOnlyDTO and return
            return Mapper.mapSessionToReadOnlyDTO(updatedSession)
                    .orElseThrow(() -> new SessionNotFoundException("Failed to map updated session"));

        } catch (SessionDaoException | SessionNotFoundException e) {
            throw e;
        }
    }

//    @Override
//    public SessionReadOnlyDTO getSessionByUuid(Integer id) throws SessionDaoException, SessionNotFoundException {
//        return null;
//    }

    @Override
    public SessionReadOnlyDTO getSessionByUuid(String uuid) throws SessionDaoException, SessionNotFoundException {
        Session session = sessionDAO.getByUuid(uuid);
        if (session == null) throw new SessionNotFoundException("Session not found with ID: " + uuid);
        return Mapper.mapSessionToReadOnlyDTO(session)
                .orElseThrow(() -> new SessionNotFoundException("Mapping failed for session with ID: " + uuid));
    }

    @Override
    public SessionReadOnlyDTO getSessionById(Integer id) throws SessionDaoException, SessionNotFoundException {
        Session session;

        try {
            session = sessionDAO.getById(id);
            //return Mapper.mapTeacherToReadOnlyDTO(teacher).orElseThrow(() -> new TeacherNotFoundException("Teacher not found in get teacher by id"));
            return Mapper.mapSessionToReadOnlyDTO(session).orElseThrow(() -> new SessionNotFoundException("Session not found in get session by id"));
        }
        catch (SessionNotFoundException | SessionDaoException e) {
            //e.printStackTrace();
            // rollback
            throw e;
        }
    }

    @Override
    public void deleteSession(Integer id) throws SessionDaoException, SessionNotFoundException {
        try {
            if (sessionDAO.getById(id) == null) {
                throw new SessionNotFoundException("Session not exists");
            }
            sessionDAO.delete(id);
        } catch (SessionDaoException | SessionNotFoundException e) {
            throw e;
        }
    }

//    @Override
//    public SessionReadOnlyDTO getSessionByUuid(String uuid) throws SessionDaoException, SessionAlreadyExistsException, SessionNotFoundException {
//        Session session;
//
//        try {
//            session = (Session) sessionDAO.getByUuid(uuid);
//            return Mapper.mapSessionToReadOnlyDTO(session).orElseThrow(() -> new SessionNotFoundException("Session not found in get session by uuid"));
//
//        } catch (SessionNotFoundException | SessionDaoException e) {
//            throw e;
//        }
//    }


//    @Override
//    public List<SessionReadOnlyDTO> getAllSessions() throws SessionDaoException {
//        List<Session> sessions = List.of();
//
//        try {
//            sessions = sessionDAO.getAll();
//            return sessions.stream().map(Mapper::mapSessionToReadOnlyDTO).flatMap(Optional::stream).collect(Collectors.toList());
//        } catch (SessionDaoException e) {
//            throw e;
//        }
//        }


    @Override
    public List<SessionReadOnlyDTO> getSessionBySurfspot(String surfspots) throws SessionDaoException {
        List<Session> sessions;
        try {
            sessions = sessionDAO.getBySurfspot(surfspots);
            return sessions.stream().map(Mapper::mapSessionToReadOnlyDTO).flatMap(Optional::stream).collect(Collectors.toList());
        } catch (SessionDaoException e) {
            throw e;
        }
    }

    @Override
    public SessionReadOnlyDTO updateSession(String uuid, SessionUpdateDTO updateDTO) throws SessionDaoException, SessionNotFoundException {
        return null;
    }

}
