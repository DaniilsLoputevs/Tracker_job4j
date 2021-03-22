package ru.job4j.actions;

import org.junit.Test;
import ru.job4j.StubInput;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CreateTest extends AbstractTests {
    // 1) init
    private final BaseAction action = new Create(1, "");

    @Test
    public void modelTestCreateHibernate() {
        // 2) prepare
        var stubInput = new StubInput(new String[]{
                "Запись от --- actions[Create.execute()]"
        });

        // 3) action
        modelTestActionsHbm(action, stubInput);

        // ~4) expected
        var tempResult = trackerHbm.findByName("Запись от --- actions[Create.execute()]");
        var expected = new ArrayList<>(List.of("table format: ID --- NAME"));
        expected.addAll(formatExpected(tempResult));

        // 5) compare
        assertEquals(expected, actualAnswer);

        // 6) close
        closeHbm();
    }

    @Test
    public void modelTestCreateSql() {
        // 2) prepare
        var stubInput = new StubInput(new String[]{
                "Запись от --- actions[Create.execute()]"
        });

        // 3) action
        modelTestActionsSql(action, stubInput);

        // ~4) expected
        var tempResult = trackerSql.findByName("Запись от --- actions[Create.execute()]");
      
        var expected = new ArrayList<>(List.of("table format: ID --- NAME"));
        expected.addAll(formatExpected(tempResult));

        // 5) compare
        assertEquals(expected, actualAnswer);

        // 6) close
        closeSql();
    }

    @Test
    public void modelTestCreateLocal() {
        // 2) prepare
        var stubInput = new StubInput(new String[]{
                "Запись от --- actions[Create.execute()]"
        });

        // 3) action
        modelTestActionsLocal(action, stubInput);

        // ~4) expected
        var tempResult = trackerLocal.findByName("Запись от --- actions[Create.execute()]");
        var expected = new ArrayList<>(List.of("table format: ID --- NAME"));
        expected.addAll(formatExpected(tempResult));

        // 5) compare
        assertEquals(expected, actualAnswer);

        // ~6) close
        trackerLocal.deleteAll();
    }

}