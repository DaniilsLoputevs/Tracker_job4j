package ru.job4j.actions;

import org.junit.Test;
import ru.job4j.StubInput;
import ru.job4j.models.Item;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ReplaceTest extends AbstractTests {
    // 1) init
    private Item testItem = new Item("Запись от --- actions[Replace.execute()]");
    private final BaseAction action = new Replace(1, "");

    @Test
    public void modelTestReplaceHbm() {
        // 2) prepare
        testItem = trackerHbm.add(testItem);
        var newId = testItem.getId();

        var stubInput = new StubInput(new String[]{
                "" + testItem.getId(),
                "Запись от - actions[Replace.execute()] - замена"
        });

        // 3) action
        modelTestActionsHbm(action, stubInput);

        // ~4) expected
        var tempResult = List.of(new Item(newId, "Запись от - actions[Replace.execute()] - замена"));
        var expected = new ArrayList<>(List.of("table format: ID --- NAME"));
        expected.addAll(formatExpected(tempResult));

        // 5) compare
        assertEquals(expected, actualAnswer);

        // 6) close
        trackerHbm.deleteAll();
        closeSql();
    }

    @Test
    public void modelTestReplaceSql() {
        // 2) prepare
        testItem = trackerSql.add(testItem);
        var newId = testItem.getId();

        var stubInput = new StubInput(new String[]{
                "" + testItem.getId(),
                "Запись от - actions[Replace.execute()] - замена"
        });

        // 3) action
        modelTestActionsSql(action, stubInput);

        // ~4) expected
        var tempResult = List.of(new Item(newId, "Запись от - actions[Replace.execute()] - замена"));
        var expected = new ArrayList<>(List.of("table format: ID --- NAME"));
        expected.addAll(formatExpected(tempResult));

        // 5) compare
        assertEquals(expected, actualAnswer);

        // 6) close
        closeSql();
    }

    @Test
    public void modelTestReplaceLocal() {
        // 2) prepare
        testItem = trackerLocal.add(testItem);
        var newId = testItem.getId();

        var stubInput = new StubInput(new String[]{
                "" + testItem.getId(),
                "Запись от - actions[Replace.execute()] - замена"
        });

        // 3) action
        modelTestActionsLocal(action, stubInput);

        // ~4) expected
        var tempResult = List.of(new Item(newId, "Запись от - actions[Replace.execute()] - замена"));
        var expected = new ArrayList<>(List.of("table format: ID --- NAME"));
        expected.addAll(formatExpected(tempResult));

        // 5) compare
        assertEquals(expected, actualAnswer);

        // ~6) close
    }

}