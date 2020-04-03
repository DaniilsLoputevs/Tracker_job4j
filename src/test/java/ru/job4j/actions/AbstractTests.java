package ru.job4j.actions;

import ru.job4j.Item;
import ru.job4j.StubInput;
import ru.job4j.Tracker;
import ru.job4j.trackers.TrackerLocal;
import ru.job4j.trackers.TrackerSQL;
import ru.job4j.trackers.TrackerSQLTest;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public abstract class AbstractTests {
    // TrackerSQLTest - variables
    protected String configPath = "./src/main/resources/connection_config.properties";
    protected Tracker tracker = new TrackerSQL(configPath);
    protected List<String> actualAnswer = new ArrayList<>();
    protected Consumer<String> output = actualAnswer::add;

    // TrackerLocal
    protected Tracker trackerLocal = new TrackerLocal();

    protected void modelTestActionsSql(BaseAction action, StubInput stubInput) {
        action.execute(
                stubInput,
                tracker,
                output
        );
    }

    protected List<String> formatExpected(List<Item> tempExpected) {
        return tempExpected.stream()
                .map(item -> String.format("%s %s", item.getId(), item.getName()))
                .collect(Collectors.toList());
    }

    protected void close(Item... items) {
        new TrackerSQLTest().cleanBaseTracker(items);
    }

    protected void modelTestActionsLocal(BaseAction action, StubInput stubInput) {
        action.execute(
                stubInput,
                trackerLocal,
                output
        );
    }

}
