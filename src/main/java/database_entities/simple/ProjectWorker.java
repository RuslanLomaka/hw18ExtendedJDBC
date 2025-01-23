package database_entities.simple;

import database_entities.DataBaseEntity;

public record ProjectWorker(Integer projectId, Integer workerId) implements DataBaseEntity {

    @Override
    public String toString() {
        return "ProjectWorker{" +
                "projectId=" + projectId +
                ", workerId=" + workerId +
                '}';
    }
}