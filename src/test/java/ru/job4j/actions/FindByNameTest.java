package ru.job4j.actions;

import org.junit.Test;
import ru.job4j.StubInput;
import ru.job4j.models.Item;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FindByNameTest extends AbstractTests {
    // 1) init
    private final Item testItem = new Item("Запись от --- actions[FindByName.execute()]");

    private final BaseAction action = new FindByName(1, "");

    @Test
    public void modelTestFindByNameHbm() {
        // 2) prepare
        trackerHbm.add(testItem);
        var stubInput = new StubInput(new String[]{
                testItem.getName()
        });

        // 3) action
        modelTestActionsHbm(action, stubInput);

        // ~4) expected
        var tempResult = List.of(testItem);
        var expected = new ArrayList<>(List.of("table format: ID --- NAME"));
        expected.addAll(formatExpected(tempResult));

        // 5) compare
        assertEquals(expected, actualAnswer);

        // 6) close
        closeSql();
    }
    @Test
    public void modelTestFindByNameSql() {
        // 2) prepare
        trackerSql.add(testItem);
        var stubInput = new StubInput(new String[]{
                testItem.getName()
        });

        // 3) action
        modelTestActionsSql(action, stubInput);

        // ~4) expected
        var tempResult = List.of(testItem);
        var expected = new ArrayList<>(List.of("table format: ID --- NAME"));
        expected.addAll(formatExpected(tempResult));

        // 5) compare
        assertEquals(expected, actualAnswer);

        // 6) close
        closeSql();
    }

    @Test
    public void modelTestFindByNameLocal() {
        // 2) prepare
        trackerLocal.add(testItem);
        var stubInput = new StubInput(new String[]{
                testItem.getName()
        });

        // 3) action
        modelTestActionsLocal(action, stubInput);

        // ~4) expected
        var tempResult = List.of(testItem);
        var expected = new ArrayList<>(List.of("table format: ID --- NAME"));
        expected.addAll(formatExpected(tempResult));

        // 5) compare
        assertEquals(expected, actualAnswer);

        // ~6) close
    }

}