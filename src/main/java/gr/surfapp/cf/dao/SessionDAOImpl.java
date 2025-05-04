package gr.surfapp.cf.dao;


import gr.surfapp.cf.dao.exceptions.SessionDaoException;
import gr.surfapp.cf.model.Session;
import gr.surfapp.cf.util.DBUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The SessionDAOImpl class implements the ISessionDAO interface, providing methods for
 * interacting with the database to manage session data (insert, update, delete, get).
 */


public class SessionDAOImpl implements ISessionDAO {

    @Override
    public Session insert(Session session) throws SessionDaoException {
        String sql = "INSERT INTO sessions (surfspots, surfboards, conditions, opinions, uuid, created_at, updated_at)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        Session insertedSession = null;


        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {


            ps.setString(1, session.getSurfspots());
            ps.setString(2, session.getSurfboards());
            ps.setString(3, session.getConditions());
            ps.setString(4, session.getOpinions());
            ps.setString(5, session.getUuid());
            //System.out.println("generated uuid" + session.getUuid());
            ps.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
            ps.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
            ps.executeUpdate();

            ResultSet rsGeneratedKeys = ps.getGeneratedKeys();

            if (rsGeneratedKeys.next()) {
                int generatedId = rsGeneratedKeys.getInt(1);
                insertedSession = getById(generatedId);
            }

            // Logging

            return insertedSession;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SessionDaoException("SQL error in insert session: " + e.getMessage());
        }
    }

    @Override
    public Session update(Session session) throws SessionDaoException {
        String sql = "UPDATE sessions SET surfspots = ?, surfboards = ?, conditions = ?, opinions = ?, updated_at = ? WHERE uuid = ?";

        Session updatedSession;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, session.getSurfspots());
            ps.setString(2, session.getSurfboards());
            ps.setString(3, session.getConditions());
            ps.setString(4, session.getOpinions());
            ps.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(6, session.getUuid());

            ps.executeUpdate();

            updatedSession = getByUuid(session.getUuid());

            // Logging
            return updatedSession;


        } catch (SQLException e) {
            e.printStackTrace();
            // logging
            throw new SessionDaoException("SQL error in update session with uuid: " + session.getUuid());

        }
    }

    @Override
    public void delete(Integer id) throws SessionDaoException {
        String sql = "DELETE FROM sessions WHERE id = ?";

        try (Connection connection = DBUtil.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            // Logging
        } catch (SQLException e) {
            throw new SessionDaoException("SQL error in delete with session uuid: " + getById(id));
        }
    }


    @Override
    public List<Session> getAll() throws SessionDaoException {
        String sql = "SELECT * FROM sessions";
        List<Session> sessions = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Session session = new Session(
                        rs.getInt("id"),
                        rs.getString("surfspots"),
                        rs.getString("surfboards"),
                        rs.getString("conditions"),
                        rs.getString("opinions"),
                        rs.getString("uuid"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("updated_at").toLocalDateTime()
                );
                sessions.add(session);
            }
            return sessions;
        } catch (SQLException e) {
            throw new SessionDaoException("SQL error in getAll()");
        }

    }

    @Override
    public Session getByUuid(String uuid) throws SessionDaoException {
        String sql = "SELECT * FROM sessions WHERE uuid = ?";

        Session session = null;
        ResultSet rs;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, uuid);
            rs = ps.executeQuery();

            if (rs.next()) {
                session = new Session(
                        rs.getInt("id"),
                        rs.getString("surfspots"),
                        rs.getString("surfboards"),
                        rs.getString("conditions"),
                        rs.getString("opinions"),
                        rs.getString("uuid"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("updated_at").toLocalDateTime()
                );

            }
            return session;
        } catch (SQLException e) {
            throw new SessionDaoException("SQL error in get by uuid with uuid: " + uuid);
        }
    }

    @Override
    public List<Session> getBySurfspot(String surfspots) throws SessionDaoException {
        String sql = "SELECT * FROM sessions WHERE surfspots LIKE ?";
        List<Session> sessions = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "%" + surfspots + "%"); // Enable partial matches
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                sessions.add(new Session(
                        rs.getInt("id"),
                        rs.getString("surfspots"),
                        rs.getString("surfboards"),
                        rs.getString("conditions"),
                        rs.getString("opinions"),
                        rs.getString("uuid"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("updated_at").toLocalDateTime()
                ));
            }
        } catch (SQLException e) {
            throw new SessionDaoException("SQL error in getBySurfspot: " + surfspots);
        }

        return sessions;
    }


    @Override
    public Session getById(Integer id) throws SessionDaoException {
        String sql = "SELECT * FROM sessions WHERE id = ?";

        Session session = null;
        ResultSet rs;

        try(Connection connection = DBUtil.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                session = new Session(rs.getInt("id"), rs.getString("surfspots"), rs.getString("surfboards"),
                        rs.getString("conditions"), rs.getString("opinions"), rs.getString("uuid"),
                        rs.getTimestamp("created_at").toLocalDateTime(), rs.getTimestamp("updated_at").toLocalDateTime());
            }
            return session;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SessionDaoException("SQL error in get by id with id: " + id);
        }
    }
}

