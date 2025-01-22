package database_entities.combined;

import database_entities.DataBaseEntity;

public class LongestProject implements DataBaseEntity {
    private final String name;
    private final int projectLength;
    private final int id;

    public LongestProject(String name, int projectLength, int id) {
        this.name = name;
        this.projectLength = projectLength;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Project Name: " + name + ", Project Length: " + projectLength + " months, Project ID: " + id;
    }
}