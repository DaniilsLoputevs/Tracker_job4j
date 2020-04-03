package ru.job4j.actions;

import org.junit.Test;
import ru.job4j.StubInput;

import static org.junit.Assert.assertEquals;

public class CreateTest extends AbstractTests {
    // 1) init
    private BaseAction action = new Create(1, "");

    @Test
    public void modelTestCreateSql() {
        // 2) prepare
        var stubInput = new StubInput(new String[]{
                "Запись от - actions[Create.execute()]"
        });

        // 3) action
        modelTestActionsSql(action, stubInput);

        // ~4) expected
        var tempResult = tracker.findByName("Запись от - actions[Create.execute()]");
        var expected = formatExpected(tempResult);

        // 5) compare
        assertEquals(expected, actualAnswer);

        // 6) close
        close(tempResult.get(0));
    }

    @Test
    public void modelTestCreateLocal() {
        // 2) prepare
        var stubInput = new StubInput(new String[]{
                "Запись от - actions[Create.execute()]"
        });

        // 3) action
        modelTestActionsLocal(action, stubInput);

        // ~4) expected
        var tempResult = trackerLocal.findByName("Запись от - actions[Create.execute()]");
        var expected = formatExpected(tempResult);

        // 5) compare
        assertEquals(expected, actualAnswer);

        // ~6) close
    }

}