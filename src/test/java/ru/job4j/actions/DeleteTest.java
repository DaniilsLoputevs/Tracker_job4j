package ru.job4j.actions;

import org.junit.Test;
import ru.job4j.StubInput;
import ru.job4j.models.Item;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DeleteTest extends AbstractTests {
    // 1) init
    private final Item one = new Item("Запись от - actions[Delete.execute()] - 1");
    private final Item two = new Item("Запись от - actions[Delete.execute()] - 2");
    private final Item three = new Item("Запись от - actions[Delete.execute()] - 3");
    private final BaseAction action = new Delete(1, "");

    @Test
    public void modelTestDeleteHibernate() {
        // 2) prepare
        trackerHbm.addAll(one, two, three);
        var stubInput = new StubInput(new String[]{
                "" + two.getId()
        });

        // 3) action
        modelTestActionsHbm(action, stubInput);

        // ~4) expected

        // 5) compare
        assertFalse(trackerHbm.containsName(two.getName()));
        assertTrue(trackerHbm.containsName(three.getName()));

        // 6) close
        closeHbm();
    }

    @Test
    public void modelTestDeleteSql() {
        // 2) prepare
        trackerSql.addAll(one, two, three);
        var stubInput = new StubInput(new String[]{
                "" + two.getId()
        });

        // 3) action
        modelTestActionsSql(action, stubInput);

        // ~4) expected

        // 5) compare
        assertFalse(trackerSql.containsName(two.getName()));
        assertTrue(trackerSql.containsName(three.getName()));

        // 6) close
        closeSql();
    }

    @Test
    public void modelTestDeleteLocal() {
        // 2) prepare
        trackerLocal.addAll(one, two, three);
        var stubInput = new StubInput(new String[]{
                "" + two.getId()
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