package database_entities.simple;

import database_entities.DataBaseEntity;

public record Project(Integer id, Integer clientId, String name, String startDate,
                      String finishDate) implements DataBaseEntity {

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