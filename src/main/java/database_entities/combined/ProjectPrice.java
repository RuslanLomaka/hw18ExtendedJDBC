package database_entities.combined;

import database_entities.DataBaseEntity;

public class ProjectPrice implements DataBaseEntity {
    private final String name; // Project name
    private final int price;   // Calculated project price

    public ProjectPrice(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Project Name: " + name + ", Price: " + price;
    }
}
