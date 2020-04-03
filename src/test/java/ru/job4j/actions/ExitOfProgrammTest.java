package ru.job4j.actions;

import org.junit.Test;
import ru.job4j.StubInput;
import ru.job4j.trackers.TrackerLocal;

import java.util.function.Consumer;

import static org.junit.Assert.assertFalse;

public class ExitOfProgrammTest {
    private Consumer<String> output = System.out::println;

    @Test
    public void exitOfProgrammTestClassTest() {
        boolean expect = new ExitOfProgramm(1, "")
                .execute(new StubInput(new String[]{" "}), new TrackerLocal(), output);
        assertFalse(expect);
    }
}