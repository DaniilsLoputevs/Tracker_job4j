package ru.job4j.actions;

import org.junit.Test;
import ru.job4j.Item;
import ru.job4j.StubInput;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class FindByIdTest extends AbstractTests {
    // 1) init
    private Item testItem = new Item("Запись от - actions[FindById.execute()]");
    private BaseAction action = new FindById(1, "");

    @Test
    public void modelTestFindByIdSql() {
        // 2) prepare
        tracker.add(testItem);
        var stubInput = new StubInput(new String[]{
                testItem.getId()
        });

        // 3) action
        modelTestActionsSql(action, stubInput);

        // ~4) expected
        var tempResult = List.of(testItem);
        var expected = formatExpected(tempResult);

        // 5) compare
        assertEquals(expected, actualAnswer);

        // 6) close
//        close(testItem);
        close();
    }

    @Test
    public void modelTestFindByIdLocal() {
        // 2) prepare
        trackerLocal.add(testItem);
        var stubInput = new StubInput(new String[]{
                testItem.getId()
        });

        // 3) action
        modelTestActionsLocal(action, stubInput);

        // ~4) expected
        var tempResult = List.of(testItem);
        var expected = formatExpected(tempResult);

        // 5) compare
        assertEquals(expected, actualAnswer);

        // ~6) close
    }

}