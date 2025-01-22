package database_entities.simple;

import database_entities.DataBaseEntity;

public class Project implements DataBaseEntity {
    private final Integer id;
    private final Integer clientId;
    private final String name;
    private final String startDate;
    private final String finishDate;

    public Project(Integer id, Integer clientId, String name, String startDate, String finishDate) {
        this.id = id;
        this.clientId = clientId;
        this.name = name;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

    public Integer getId() {
        return id;
    }

    public Integer getClientId() {
        return clientId;
    }

    public String getName() {
        return name;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getFinishDate() {
        return finishDate;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", name='" + name + '\'' +
                ", startDate='" + startDate + '\'' +
                ", finishDate='" + finishDate + '\'' +
                '}';
    }
}