package ru.job4j.actions;

import org.junit.Test;
import ru.job4j.Item;
import ru.job4j.StubInput;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ReplaceTest extends AbstractTests {
    // 1) init
    private Item testItem = new Item("Запись от - actions[Replace.execute()]");
    private BaseAction action = new Replace(1, "");

    @Test
    public void modelTestReplaceSql() {
        // 2) prepare
        testItem = tracker.add(testItem);
        var newId = testItem.getId();

        var stubInput = new StubInput(new String[]{
                testItem.getId(),
                "Запись от - actions[Replace.execute()] - замена"
        });

        // 3) action
        modelTestActionsSql(action, stubInput);

        // ~4) expected
        var tempResult = List.of(new Item(newId, "Запись от - actions[Replace.execute()] - замена"));
        var expected = formatExpected(tempResult);

        // 5) compare
        assertEquals(expected, actualAnswer);

        // 6) close
        close(testItem);
    }

    @Test
    public void modelTestReplaceLocal() {
        // 2) prepare
        testItem = trackerLocal.add(testItem);
        var newId = testItem.getId();

        var stubInput = new StubInput(new String[]{
                testItem.getId(),
                "Запись от - actions[Replace.execute()] - замена"
        });

        // 3) action
        modelTestActionsLocal(action, stubInput);

        // ~4) expected
        var tempResult = List.of(new Item(newId, "Запись от - actions[Replace.execute()] - замена"));
        var expected = formatExpected(tempResult);

        // 5) compare
        assertEquals(expected, actualAnswer);

        // ~6) close
    }

}