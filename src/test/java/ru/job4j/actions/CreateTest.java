package ru.job4j.actions;

import org.junit.Test;
import ru.job4j.StubInput;
import ru.job4j.Tracker;
import ru.job4j.trackers.TrackerLocal;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.function.Consumer;

import static org.junit.Assert.assertEquals;

public class CreateTest {
    private Consumer<String> output = System.out::println;

    @Test
    public void createActionClassTest() {
        // Подгатовка
        ByteArrayOutputStream newOutput = new ByteArrayOutputStream();
        PrintStream defaultOutput = System.out;
        System.setOut(new PrintStream(newOutput));
        // Основной блок
        Tracker trackerLocal = new TrackerLocal();
        String nameCreateItem = "test";
        // Действие
        new Create(1, "").execute(new StubInput(new String[]{nameCreateItem}), trackerLocal, output);
        assertEquals(trackerLocal.findByName("test"), trackerLocal.findAll());
//        assertThat(trackerLocal.findAll(), is(trackerLocal.findByName("test")));
        // Возвращаем стандартный вывод
        System.setOut(defaultOutput);
    }
}