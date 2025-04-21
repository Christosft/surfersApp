package gr.surfapp.cf.dto;

import java.sql.Timestamp;

public class SessionReadOnlyDTO  extends BaseSessionDTO {

    private String uuid;
    private Integer id;

    public SessionReadOnlyDTO() {

    }

    public SessionReadOnlyDTO(Integer id, String surfspots, String surfboards, String conditions, String opinions, String uuid) {
        super(surfspots, surfboards, conditions, opinions);
        this.id = id;
        this.uuid = uuid;
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

    @Override
    public String toString() {
        return "SessionReadOnlyDTO{" +
                ", id='" + id +
                //", uuid='" + uuid + '\'' +
                '}';
    }
}
