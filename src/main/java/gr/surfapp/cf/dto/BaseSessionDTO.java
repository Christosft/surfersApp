package gr.surfapp.cf.dto;



public abstract class BaseSessionDTO {

    private String surfspots;
    private String surfboards;
    private String conditions;
    private String opinions;

    public BaseSessionDTO() {

    }

    public BaseSessionDTO(String surfspots, String surfboards, String conditions, String opinions) {
        this.surfspots = surfspots;
        this.surfboards = surfboards;
        this.conditions = conditions;
        this.opinions = opinions;
    }

    public String getSurfspots() {
        return surfspots;
    }

    public void setSurfspots(String surfspots) {
        this.surfspots = surfspots;
    }

    public String getSurfboards() {
        return surfboards;
    }

    public void setSurfboards(String surfboards) {
        this.surfboards = surfboards;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public String getOpinions() {
        return opinions;
    }

    public void setOpinions(String opinions) {
        this.opinions = opinions;
    }


    @Override
    public String toString() {
        return "BaseSessionDTO{" +
                "surfspots='" + surfspots + '\'' +
                ", surfboards='" + surfboards + '\'' +
                ", conditions='" + conditions + '\'' +
                ", opinions='" + opinions + '\'' +
                '}';
    }
}
