package gr.surfapp.cf.model;

import java.time.LocalDateTime;

public class Session {

    private Integer id;
    private String surfspots;
    private String surfboards;
    private String conditions;
    private String opinions;
    private String uuid;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Session() {

    }

    public Session(Integer id, String surfspots, String surfboards, String conditions, String opinions, String uuid, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.surfspots = surfspots;
        this.surfboards = surfboards;
        this.conditions = conditions;
        this.opinions = opinions;
        this.uuid = uuid;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", surfspots='" + surfspots + '\'' +
                ", surfboards='" + surfboards + '\'' +
                ", conditions='" + conditions + '\'' +
                ", opinions='" + opinions + '\'' +
                ", uuid='" + uuid + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
