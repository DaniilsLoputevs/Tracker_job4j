package ru.job4j.trackers;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.Tracker;
import ru.job4j.models.Item;

import static org.junit.Assert.*;

public class TrackerSQLTest {
    private final Tracker tracker = new TrackerSQL(true);
    private static final Logger LOG = LoggerFactory.getLogger(TrackerSQLTest.class);

    @Test
    public void add() {
        var clean = tracker.add(new Item("Запись от - add()"));

        assertNotNull(clean);
    }

    @Test
    public void replace() {
        var replaceItem = tracker.add(new Item("Запись от - replace()"));
        var result = tracker.replace(replaceItem.getId(), new Item("Запись от - replace() - замена"));

        assertTrue(result);
    }

    @Test
    public void delete() {
        var deleteItem = tracker.add(new Item("Запись от - delete()"));
        this.tracker.delete(deleteItem.getId());

        assertEquals(0, tracker.findByName("Запись от - delete()").size());
    }

    @Test
    public void findAll() {
        this.tracker.add(new Item("Запись от - findAll()"));
        this.tracker.add(new Item("Запись от - findAll()"));
        var list = tracker.findAll();

        assertTrue(list.size() >= 2);
    }

    @Test
    public void findByName() {
        this.tracker.add(new Item("Запись от - findByName()"));
        this.tracker.add(new Item("Запись от - findByName()"));
        var list = tracker.findByName("Запись от - findByName()");

        assertEquals("Запись от - findByName()", list.get(0).getName());
        assertTrue(list.size() >= 2);
    }

    @Test
    public void findById() {
        var itemForFind = tracker.add(new Item("поиск запрос ИД"));
        var result = tracker.findById(itemForFind.getId());

        assertEquals("поиск запрос ИД", result.getName());
    }

}

