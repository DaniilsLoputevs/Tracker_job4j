package ru.job4j;

/**
 * Модель данных: Заявка.
 * Используется Имплементация {@code Tracker}.
 *
 * @author Daniils Loputevs
 * @version $Id$
 * @since 03.04.20.
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