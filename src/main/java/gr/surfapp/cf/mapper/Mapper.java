package gr.surfapp.cf.mapper;

import gr.surfapp.cf.dto.SessionInsertDTO;
import gr.surfapp.cf.dto.SessionReadOnlyDTO;
import gr.surfapp.cf.dto.SessionUpdateDTO;
import gr.surfapp.cf.model.Session;

import java.util.Optional;
import java.util.UUID;

public class Mapper {

    private Mapper() {

    }

    public static Session mapSessionInsertToModel(SessionInsertDTO dto) {

        return new Session(null, dto.getSurfspots(), dto.getSurfboards(), dto.getConditions(), dto.getOpinions(), UUID.randomUUID().toString(), null, null);
    }

    public static Session mapSessionUpdateToModel(SessionUpdateDTO dto) {
        return new Session(null, dto.getSurfspots(), dto.getSurfboards(), dto.getConditions(), dto.getOpinions(), null, null, null);
    }

    public static Optional<SessionReadOnlyDTO> mapSessionToReadOnlyDTO(Session session) {
        if (session == null) return Optional.empty();
        return Optional.of(new SessionReadOnlyDTO(session.getId(), session.getSurfspots(), session.getSurfboards(), session.getConditions(), session.getOpinions(), session.getUuid(), session.getCreatedAt()));
    }
}
