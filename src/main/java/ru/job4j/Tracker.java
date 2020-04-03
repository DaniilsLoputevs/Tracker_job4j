package ru.job4j;

import java.util.List;

/** Интерфейс для менеджера заявок.
 * 2 имплементации: {@code TrackerSQL} и {@code TrackerLocal}
 * 1) - работает с базой
 * 2) - работает с коллекций.
 * {@code TrackerSQL} - нахотидся в {@code chapter_007}
 *
 * @author Daniils Loputevs
 * @version $Id$
 * @since 03.04.20.
 */
public interface Tracker {
    Item add(Item item);
    List<Item> addAll(Item... items);
    boolean replace(String id, Item item);
    boolean delete(String id);
    List<Item> findAll();
    List<Item> findByName(String key);
    Item findById(String id);

    boolean containsId(String id);
    boolean containsName(String name);
}