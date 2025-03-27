package gr.surfapp.cf.dao;

import com.mysql.cj.Session;
import gr.surfapp.cf.dao.exceptions.SessionDaoException;
import gr.surfapp.cf.util.DBUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class SessionDaoImpl implements ISessionDao {

    @Override
    public Session insert(Session session) throws SessionDaoException {
        String sql = "INSERT INTO sessions (surfspots, surfboards, conditions, opinions)" +
                "VALUES (?, ?, ?, ?)";

        Session insertedSession = null;

        try (Connection connection = DBUtil.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, session.getSurfspots());
            ps.setString(2, session.getSurfboards());
            ps.setString(3, session.getConditions());
            ps.setString(4, session.getOpinions());
            ps.setString(5, UUID.randomUUID().toString());
            ps.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
            ps.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
            ps.executeUpdate();

            ResultSet rsGeneratedKeys = ps.getGeneratedKeys();


            // Logging
            return insertedSession;
        } catch (SQLException e) {
            throw new SessionDaoException("SQL error in insert session with surfspot: " + session.getSurfspots());

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
    public void delete(String uuid) throws SessionDaoException {
        String sql = "DELETE FROM sessions WHERE uuid = ?";

        try (Connection connection = DBUtil.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, uuid);
            ps.executeUpdate();
            // Logging
        } catch (SQLException e) {
            throw new SessionDaoException("SQL error in delete with session uuid: " + uuid);
        }
    }

    @Override
    public List<Session> getAll() throws SessionDaoException {
        return List.of();
    }

    @Override
    public Session getByUUID(String uuid) throws SessionDaoException {
        String sql = "SELECT *FROM sessions WHERE uuid = ?";

        Session session = null;
        ResultSet rs;

        try (Connection connection = DBUtil.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setString(1, uuid);
            rs = ps.executeQuery();

            if (rs.next()) {
                session = new Session(
                                        rs.getString("surfspots"),
                                        rs.getString("surfboards"),
                                        rs.getString("conditions"),
                                        rs.getString("opinions"),
                                        rs.getString("created_at"),
                                        rs.getString("updated_at")
                );
            }
                return session;
        } catch (SQLException e) {
            throw new SessionDaoException("SQL error in get by uuid with uuid: " + uuid);
        }
    }

    @Override
    public List<Session> getBySurfspot(String Surfspot) throws SessionDaoException {
        return List.of();
    }

}

