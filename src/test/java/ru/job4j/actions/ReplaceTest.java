package ru.job4j.actions;

import org.junit.Test;
import ru.job4j.Item;
import ru.job4j.StubInput;
import ru.job4j.trackers.TrackerLocal;

import java.util.function.Consumer;

import static org.junit.Assert.assertEquals;

public class ReplaceTest {
    private Consumer<String> output = System.out::println;

    @Test
    public void replaceActionClassTest() {
        // Подгатовка
        TrackerLocal trackerLocal = new TrackerLocal();
        Item example = new Item("example");
        Item test = new Item("test");
        // Основной блок
        trackerLocal.add(example);
        // Обновляем заявку в трекере.
        new Replace(1, "").execute(new StubInput(new String[]{example.getId(), test.getName()}), trackerLocal, output);
        // Проверяем, что заявка с таким id имеет новое имя "test"

        assertEquals("test", trackerLocal.findById(example.getId()).getName());
//        assertThat(trackerLocal.findById(example.getId()).getName(), Matchers.is("test"));
    }
}
