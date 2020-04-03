package ru.job4j.actions.trackersql;

import org.junit.Test;
import ru.job4j.Item;
import ru.job4j.StubInput;
import ru.job4j.Tracker;
import ru.job4j.actions.BaseAction;
import ru.job4j.actions.FindAll;
import ru.job4j.trackers.TrackerSQL;
import ru.job4j.trackers.TrackerSQLTest;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

//public class FindAllTest extends BaseTest {
public class FindAllTest {
    private String configPath = "./src/main/resources/connection_config.properties";
    private Tracker tracker = new TrackerSQL(configPath);
    private List<String> actualAnswer = new ArrayList<>();
    private Consumer<String> output = actualAnswer::add;

    private BaseAction action = new FindAll(1, "");
    private Item testItem = new Item("Запись от - actions[FindAll.execute()]");

    @Test
    public void actionFindAllTest() {
        tracker.add(testItem);

        // Действие
        action.execute(
                new StubInput(new String[]
                        {}
                ),
                tracker,
                output);

        List<Item> temp = tracker.findAll();

        List<String> expected = temp.stream()
                .map(item -> String.format("%s %s", item.getId(), item.getName()))
                .collect(Collectors.toList());

        assertEquals(expected, actualAnswer);

        new TrackerSQLTest().cleanBaseTracker(testItem);
    }

//    @Test
//    public void baseTestFindAll() {
//        this.formOne(action,
//                "Запись от - actions[FindAll.execute()]",
//                new String[] {
//                        "Запись от - actions[FindAll.execute()]"
//                },
//                "findAll"
//        );
//    }

}