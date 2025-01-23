package database_entities.simple;

import database_entities.DataBaseEntity;

public record Worker(Integer id, String name, String birthday, String level, Integer salary) implements DataBaseEntity {

    @Override
    public String toString() {
        return "Worker{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday='" + birthday + '\'' +
                ", level='" + level + '\'' +
                ", salary=" + salary +
                '}';
    }
}