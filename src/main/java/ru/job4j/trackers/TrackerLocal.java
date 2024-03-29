package ru.job4j.trackers;

import ru.job4j.Tracker;
import ru.job4j.models.Item;
import ru.job4j.utils.Observe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Класс менеджер заявок.
 *
 * @author Daniils Loputevs
 * @version $Id$
 * @since 15.04.20.
 * Created 15.10.19
 */

public class TrackerLocal implements Tracker {
    /**
     * Массив для хранение заявок.
     */
    private final ArrayList<Item> items = new ArrayList<>();

    /**
     * Генерирует уникальный ключ для заявки.
     * Т.к. у заявки нет уникального поля. Для идентификации нам нужен уникальный ключ.
     *
     * @return Уникальный ключ. (далее присваивается заявке(Item item), как id).
     */
    int generateId() {
        var rm = new Random();
        return Integer.parseInt("" + (rm.nextInt() + System.currentTimeMillis() / rm.nextInt()));
    }

    /* ============= Методы Tracker ============= */

    @Override
    public Item add(Item item) {
        item.setId(this.generateId());
        items.add(item);
        return item;
    }

    @Override
    public List<Item> addAll(Item... items) {
        List<Item> result = new ArrayList<>();
        for (var item : items) {
            if (item != null) {
                this.add(item);
                result.add(item);
            }
        }
        return result;
    }

    @Override
    public boolean replace(int id, Item item) {
        int index = indexOfId(id);
        if (index != -1) {
            item.setId(id);
            items.set(index, item);
        }
        return true;
    }

    @Override
    public boolean delete(int id) {
        int index = indexOfId(id);
        if (index != -1) {
            items.remove(index);
        }
        return true;
    }

    @Override
    public void deleteAll() {
        this.items.clear();
    }
    /* ============= Методы findBy... ============= */

    @Override
    public List<Item> findAll() {
        return new ArrayList<>(items);
    }
    
    public void findAllReact(Observe<Item> observe) throws InterruptedException {
        for (Item datum : items) {
            observe.receive(datum);
        }
    }

    @Override
    public List<Item> findByName(String key) {
        ArrayList<Item> result = new ArrayList<>();
        for (Item item : items) {
            if (item.getName().equals(key)) {
                result.add(item);
            }
        }
        return result;
    }

    @Override
    public Item findById(int id) {
        int index = indexOfId(id);
        return (index != -1) ? items.get(index) : null;
    }

    @Override
    public boolean containsId(int id) {
        return indexOfId(id) != -1;
    }

    @Override
    public boolean containsName(String name) {
        return !findByName(name).isEmpty();
    }

    /**
     * Поиск индекса заявки, по {@param id}.
     *
     * @param id - id заявки.
     * @return - индекс заявки.
     */
    public int indexOfId(int id) {
        int index = 0;
        boolean find = false;
        for (Item item : items) {
            if (item.getId() == id) {
                find = true;
                break;
            }
            index++;
        }
        index = (find) ? index : -1;
        return index;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Item item : items) {
            result.append(item.getName()).append(", ");
        }
        return "TrackerLocal{" + "items = " + result + "}";
    }
}