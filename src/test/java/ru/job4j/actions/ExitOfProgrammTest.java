package ru.job4j.actions;

import org.junit.Test;
import ru.job4j.StubInput;

import static org.junit.Assert.assertFalse;

public class ExitOfProgrammTest extends AbstractTests {
    @Test
    public void actionExitOfProgrammTest() {
        boolean expect = new ExitOfProgramm(1, "").execute(
                new StubInput(new String[]{" "}), trackerSql, output);
        assertFalse(expect);
    }
}