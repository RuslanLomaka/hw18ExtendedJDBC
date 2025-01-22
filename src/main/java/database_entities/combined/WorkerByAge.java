package database_entities.combined;

import database_entities.DataBaseEntity;

public class WorkerByAge implements DataBaseEntity {
    private final String type; // "YOUNGEST" or "ELDEST"
    private final String name;
    private final String birthday;

    public WorkerByAge(String type, String name, String birthday) {
        this.type = type;
        this.name = name;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Type: " + type + ", Name: " + name + ", Birthday: " + birthday;
    }
}