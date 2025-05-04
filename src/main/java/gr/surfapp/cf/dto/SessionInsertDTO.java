package gr.surfapp.cf.dto;

/**
 * Data Transfer Object (DTO) for inserting a session.
 * Extends {@link BaseSessionDTO} and provides the necessary fields for creating a new session.
 */

public class SessionInsertDTO extends BaseSessionDTO {

    public SessionInsertDTO() {

    }

    public SessionInsertDTO(String surfspots, String surfboards, String conditions, String opinions) {
        super(surfspots, surfboards, conditions, opinions);
    }
}
