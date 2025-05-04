package gr.surfapp.cf.dto;


import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) for a read-only session.
 * Extends {@link BaseSessionDTO} and adds session-specific fields like UUID and creation timestamp.
 */

public class SessionReadOnlyDTO  extends BaseSessionDTO {

    private String uuid;
    private Integer id;
    private LocalDateTime createdAt;

    public SessionReadOnlyDTO() {

    }

    public SessionReadOnlyDTO(Integer id, String surfspots, String surfboards, String conditions, String opinions, String uuid, LocalDateTime createdAt) {
        super(surfspots, surfboards, conditions, opinions);
        this.id = id;
        this.uuid = uuid;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void SetId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }


    @Override
    public String toString() {
        return "SessionReadOnlyDTO{" +
                ", id='" + id +
                //", uuid='" + uuid + '\'' +
                '}';
    }
}
