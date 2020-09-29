package ru.job4j.actions;

import org.junit.Test;
import ru.job4j.Item;
import ru.job4j.StubInput;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FindAllTest extends AbstractTests {
    // 1) init
    private final Item testItem = new Item("Запись от --- actions[FindAll.execute()]");
    private final BaseAction action = new FindAll(1, "");

    @Test
    public void modelTestFindAllSql() {
        // 2) prepare
        tracker.add(testItem);
        var stubInput = new StubInput(new String[]
                {}
        );

        // 3) action
        modelTestActionsSql(action, stubInput);

        // ~4) expected
        var tempResult = tracker.findAll();
        var expected = new ArrayList<>(List.of("table format: ID --- NAME"));
        expected.addAll(formatExpected(tempResult));

        // 5) compare
        assertEquals(expected, actualAnswer);

        // ~6) close
        close();
    }

    @Test
    public void modelTestFindAllLocal() {
        // 2) prepare
        trackerLocal.add(testItem);
        var stubInput = new StubInput(new String[]
                {}
        );

        // 3) action
        modelTestActionsLocal(action, stubInput);

        // ~4) expected
        var tempResult = trackerLocal.findAll();
        var expected = new ArrayList<>(List.of("table format: ID --- NAME"));
        expected.addAll(formatExpected(tempResult));

        // 5) compare
        assertEquals(expected, actualAnswer);

        // ~6) close
    }

}