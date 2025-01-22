package database_entities.simple;

import database_entities.DataBaseEntity;

public class Worker implements DataBaseEntity {
    private final Integer id;
    private final String name;
    private final String birthday;
    private final String level;
    private final Integer salary;

    public Worker(Integer id, String name, String birthday, String level, Integer salary) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.level = level;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getLevel() {
        return level;
    }

    public int getSalary() {
        return salary;
    }

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