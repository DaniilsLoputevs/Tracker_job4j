package ru.job4j.actions;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.Item;
import ru.job4j.Tracker;
import ru.job4j.trackers.TrackerLocal;

import static org.junit.Assert.assertTrue;

public class ValidateEnterDataTest {
    private Tracker tracker;
    private Item example;

    @Before
    public void setUp() {
        tracker = new TrackerLocal();
        example = new Item("example");
    }

    @Test
    public void checkId() {
        tracker.add(example);
        assertTrue(ValidateEnterData.checkId(example.getId(), tracker));
    }

    @Test
    public void checkName() {
        tracker.add(example);
        assertTrue(ValidateEnterData.checkName(example.getName(), tracker));
    }
}