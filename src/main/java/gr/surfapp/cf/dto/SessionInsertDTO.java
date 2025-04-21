package gr.surfapp.cf.dto;

public class SessionInsertDTO extends BaseSessionDTO {

    public SessionInsertDTO() {

    }

    public SessionInsertDTO(String surfspots, String surfboards, String conditions, String opinions) {
        super(surfspots, surfboards, conditions, opinions);
    }
}
