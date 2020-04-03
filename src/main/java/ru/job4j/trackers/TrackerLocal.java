package ru.job4j.trackers;

import ru.job4j.Item;
import ru.job4j.Tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Класс менеджер заявок.
 *
 * @author Daniils Loputevs
 * @version $Id$
 * @since 03.04.20.
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
    String generateId() {
        var rm = new Random();
        return String.valueOf(rm.nextLong() + System.currentTimeMillis());
    }


    // Методы на которых основаны все клаасы в пакете actions

    /**
     * Добавить заявку в tracker
     * + Присваивает id для новой заявки.
     *
     * @param item новая заявка.
     */
    @Override
    public Item add(Item item) {
        item.setId(this.generateId());
        items.add(item);
        return item;
    }

    /**
     * Добавляет ряд заявок в tracker.
     *
     * @param items - набор заявок.
     * @return List<Item> - лист всех заявок.
     */
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

    /**
     * Замена старой заявки по её id, новый завкой(item).
     * После замены, ячейка имее старый id.
     *
     * @param id   id старой заявки (для удаление).
     * @param item новая заявка.
     */
    @Override
    public boolean replace(String id, Item item) {
        int index = indexOfId(id);
        if (index != -1) {
            item.setId(id);
            items.set(index, item);
        }
        return true;
    }

    /**
     * Удалить завку по id.
     * после сдвигает все след. ячейки ближе к началу коллекции.
     *
     * @param id - id заявки для удаление.
     */
    @Override
    public boolean delete(String id) {
        int index = indexOfId(id);
        if (index != -1) {
            items.remove(index);
        }
        return true;
    }

    // Методы findBy...

    /**
     * Найти все заявки.
     *
     * @return Item[] - массив всех заявок.
     */
    @Override
    public List<Item> findAll() {
        return new ArrayList<>(items);
    }

    /**
     * Поиск заявки по имени.
     * Т.к. заявок может быть много, метод возвращает ArrayList.
     *
     * @return Item[] - массив всех заявок с одинаковыми именами.
     */
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

    /**
     * Поиск заявки по id.
     *
     * @param id id заявки для возврата.
     * @return Item - нужная заявка.
     */
    @Override
    public Item findById(String id) {
        int index = indexOfId(id);
        return (index != -1) ? items.get(index) : null;
    }

    /**
     * Поиск индекса заявки, по {@param id}.
     *
     * @param id - id заявки.
     * @return - индекс заявки.
     */
    public int indexOfId(String id) {
        int index = 0;
        boolean find = false;
        for (Item item : items) {
            if (item.getId().equals(id)) {
                find = true;
                break;
            }
            index++;
        }
        index = (find) ? index : -1;
        return index;
    }

    /**
     * Проверка: Есть ли заявка с таким id в tracker.
     *
     * @param id - id для поиска.
     * @return - true/false
     */
    @Override
    public boolean containsId(String id) {
        return indexOfId(id) != -1;
    }

    /**
     * Проверка: Есть ли заявка с таким name в tracker.
     *
     * @param name - name для поиска.
     * @return - true/false
     */
    @Override
    public boolean containsName(String name) {
        return !findByName(name).isEmpty();
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