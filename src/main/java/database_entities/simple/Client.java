package database_entities.simple;

import database_entities.DataBaseEntity;

public record Client(int id, String name) implements DataBaseEntity {

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}