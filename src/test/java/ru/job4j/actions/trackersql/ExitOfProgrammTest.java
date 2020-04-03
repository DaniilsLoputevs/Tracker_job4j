package ru.job4j.actions.trackersql;

import org.junit.Test;
import ru.job4j.StubInput;
import ru.job4j.Tracker;
import ru.job4j.actions.ExitOfProgramm;
import ru.job4j.trackers.TrackerSQL;

import java.util.function.Consumer;

import static org.junit.Assert.assertFalse;

public class ExitOfProgrammTest {
    private String configPath = "./src/main/resources/connection_config.properties";
    private Tracker tracker = new TrackerSQL(configPath);
    private Consumer<String> output = System.out::println;

    @Test
    public void actionExitOfProgrammTest() {
        boolean expect = new ExitOfProgramm(1, "").execute(
                new StubInput(new String[]{" "}), tracker, output);
        assertFalse(expect);
//        assertThat(expect, is(false));
    }
}