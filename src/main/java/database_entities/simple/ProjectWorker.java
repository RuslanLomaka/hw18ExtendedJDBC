package database_entities.simple;

import database_entities.DataBaseEntity;

public class ProjectWorker implements DataBaseEntity {
    private final Integer projectId;
    private final Integer workerId;

    public ProjectWorker(Integer projectId, Integer workerId) {
        this.projectId = projectId;
        this.workerId = workerId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public Integer getWorkerId() {
        return workerId;
    }

    @Override
    public String toString() {
        return "ProjectWorker{" +
                "projectId=" + projectId +
                ", workerId=" + workerId +
                '}';
    }
}