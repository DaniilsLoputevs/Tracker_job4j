package ru.job4j.trackers;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.models.Item;

import java.util.List;

import static org.junit.Assert.*;

public class TrackerLocalTest {
    private TrackerLocal tracker;
    private Item one = new Item("example");
    private Item two = new Item("test");
    private Item three = new Item("example");

    @Before
    public void setUp() {
        tracker = new TrackerLocal();
    }

    @Test
    public void add() {
        tracker.add(one);
        assertTrue(tracker.containsId(one.getId()));
    }

    @Test
    public void replace() {
        tracker.add(one);
        tracker.replace(one.getId(), two);
        var result = tracker.findById(one.getId()).getName();
        assertEquals("test", result);
    }

    @Test
    public void delete() {
        tracker.addAll(one, two);
        tracker.delete(two.getId());
        List<Item> expected = List.of(one);
        assertEquals(expected, tracker.findAll());
    }

    @Test
    public void findAll() {
        tracker.addAll(one, two);
        List<Item> expected = List.of(one, two);
        assertEquals(expected, tracker.findAll());
    }

    @Test
    public void findByName() {
        tracker.addAll(one, two, three);
        List<Item> expected = List.of(one, three);
        assertEquals(expected, tracker.findByName("example"));
    }

    @Test
    public void findById() {
        tracker.addAll(one, two);
        assertEquals(one, tracker.findById(one.getId()));
    }

    @Test
    public void indexOfId() {
        tracker.addAll(one, two);
        assertEquals(1, tracker.indexOfId(two.getId()));
    }

    @Test
    public void containsId() {
        tracker.add(one);
        assertTrue(tracker.containsId(one.getId()));
    }

    @Test
    public void toStringTest() {
        tracker.addAll(one, two, three);
        assertEquals("TrackerLocal{items = example, test, example, }",
                tracker.toString());
    }

    @Test
    public void generateId() {
        var example = new TrackerLocal().generateId();
        var test = new TrackerLocal().generateId();
        assertNotEquals(example, test);
    }
}