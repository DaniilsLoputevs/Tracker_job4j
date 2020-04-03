package ru.job4j.actions;

import org.junit.Test;
import ru.job4j.Item;
import ru.job4j.StubInput;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DeleteTest extends AbstractTests {
    // 1) init
    private Item one = new Item("Запись от - actions[Delete.execute()] - 1");
    private Item two = new Item("Запись от - actions[Delete.execute()] - 2");
    private Item three = new Item("Запись от - actions[Delete.execute()] - 3");
    private BaseAction action = new Delete(1, "");

    @Test
    public void modelTestDeleteSql() {
        // 2) prepare
        tracker.addAll(one, two, three);
        var stubInput = new StubInput(new String[]{
                two.getId()
        });

        // 3) action
        modelTestActionsSql(action, stubInput);

        // ~4) expected

        // 5) compare
        assertFalse(tracker.containsName(two.getName()));
        assertTrue(tracker.containsName(three.getName()));

        // 6) close
        close(one, three);
    }

    @Test
    public void modelTestDeleteLocal() {
        // 2) prepare
        trackerLocal.addAll(one, two, three);
        var stubInput = new StubInput(new String[]{
                two.getId()
        });

        // 3) action
        modelTestActionsLocal(action, stubInput);

        // ~4) expected

        // 5) compare
        assertFalse(trackerLocal.containsName(two.getName()));
        assertTrue(trackerLocal.containsName(three.getName()));

        // ~6) close
    }

}