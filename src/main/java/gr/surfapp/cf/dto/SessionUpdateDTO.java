package gr.surfapp.cf.dto;

/**
 * Data Transfer Object (DTO) for updating a session.
 * Extends {@link BaseSessionDTO} and adds the session's ID and UUID for update operations.
 */

public class SessionUpdateDTO extends BaseSessionDTO {
    private Integer  id;
    private String uuid;

    public SessionUpdateDTO() {

    }

    public SessionUpdateDTO(Integer id, String surfspots, String surfboards, String conditions, String opinions, String uuid) {
        super(surfspots, surfboards, conditions, opinions);
        this.id = id;
        this.uuid = uuid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "SessionUpdateDTO{" +
                "id=" + id +
                "uuid=" + uuid +
                '}' + super.toString();
    }
}
