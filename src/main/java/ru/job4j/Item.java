package ru.job4j;

/**
 * Класс Класс шаблон: item - (заявки)
 * Все заявк хранятся в ArrayList<Item> items в классе TrackerLocal
 *
 * @author Daniils Loputevs
 * @version 1.1
 * @since 1.1
 **/

public class Item {
    private String id;
    private String name;

    // Constructors
    public Item(String name) {
        this.name = name;
    }

    public Item(String id, String name) {
        this.id = id;
        this.name = name;
    }


    // Getters & Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}