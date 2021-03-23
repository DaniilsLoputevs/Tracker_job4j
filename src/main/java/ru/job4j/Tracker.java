package ru.job4j;

import ru.job4j.models.Item;

import java.util.List;

/**
 * Интерфейс для менеджера заявок.
 * 2 имплементации: {@code TrackerSQL} и {@code TrackerLocal}
 * 1) - работает с базой данных.
 * 2) - работает с коллекций.
 *
 * @author Daniils Loputevs
 * @version $Id$
 * @since 15.04.20.
 */
public interface Tracker {

    /**
     * Добавить заявку в tracker.
     * * Присваивает завке новый {@param id}.
     *
     * @param item - новая заявка.
     */
    Item add(Item item);

    /**
     * Добавляет ряд заявок в tracker.
     *
     * @param items - набор заявок.
     * @return List<Item> - лист всех новых заявок.
     */
    List<Item> addAll(Item... items);

    /**
     * Замена старой заявки по её {@param id}, новой заявкой {@param item}.
     * * После замены, ячейка имее {@param id} от старый заявки.
     *
     * @param id   - id старой заявки (для её поиска).
     * @param item - новая заявка.
     * @return boolean - успех/провал.
     */
    boolean replace(int id, Item item);

    /**
     * Удалить завку по {@param id}.
     *
     * @param id - id заявки для удаление.
     * @return boolean - успех/провал.
     */
    boolean delete(int id);

    /**
     * Debug function BE CAREFUL.
     * Delete all date in store.
     *
     */
    void deleteAll();

    /**
     * Найти все заявки.
     *
     * @return List<Item> - лист всех заявок.
     */
    List<Item> findAll();

    /**
     * Поиск заявки по {@param name}.
     *
     * @param name - name заявки для поиска.
     * @return List<Item> - лист всех заявок с одинаковыми именами.
     */
    List<Item> findByName(String name);

    /**
     * Поиск заявки по {@param id}.
     *
     * @param id - id заявки для поиска.
     * @return Item - нужная заявка.
     */
    Item findById(int id);

    /**
     * Проверка: Есть ли заявка с таким {@param id} в {@code tracker}.
     *
     * @param id - id для поиска.
     * @return boolean - успех/провал.
     */
    boolean containsId(int id);

    /**
     * Проверка: Есть ли заявка с таким name в tracker.
     *
     * @param name - name для поиска.
     * @return boolean - успех/провал.
     */
    boolean containsName(String name);
}