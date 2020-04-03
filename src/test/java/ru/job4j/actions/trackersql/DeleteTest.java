package ru.job4j.actions.trackersql;

import org.junit.Test;
import ru.job4j.Item;
import ru.job4j.StubInput;
import ru.job4j.Tracker;
import ru.job4j.actions.BaseAction;
import ru.job4j.actions.Delete;
import ru.job4j.trackers.TrackerSQL;
import ru.job4j.trackers.TrackerSQLTest;

import java.util.function.Consumer;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DeleteTest {
    private String configPath = "./src/main/resources/connection_config.properties";
    private Tracker tracker = new TrackerSQL(configPath);
    private Consumer<String> output = System.out::println;

    private BaseAction action = new Delete(1, "");

    @Test
    public void actionDeleteTest() {
        // Подгатовка
        var first = new Item("Запись от - actions[Delete.execute()] - 1");
        var second = new Item("Запись от - actions[Delete.execute()] - 2");
        var third = new Item("Запись от - actions[Delete.execute()] - 3");
        // Основной блок
        tracker.add(first);
        tracker.add(second);
        tracker.add(third);

        // Действие
        action.execute(
                new StubInput(new String[] {
                        second.getId()
                }),
                tracker,
                output);

        assertFalse(tracker.containsName(second.getName()));
        assertTrue(tracker.containsName(third.getName()));

        // Чистим базу
        new TrackerSQLTest().cleanBaseTracker(first, third);
    }
}
