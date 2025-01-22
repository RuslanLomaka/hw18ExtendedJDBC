package database_entities.combined;

import database_entities.DataBaseEntity;

public class MaxProjectCountClient implements DataBaseEntity {
    private final String name;
    private  final int  projectCount;

    public MaxProjectCountClient(String name, int projectCount) {
        this.name = name;
        this.projectCount = projectCount;
    }

    @Override
    public String toString() {
        return ("Name " + name + ", Project count:" + projectCount);
    }
}