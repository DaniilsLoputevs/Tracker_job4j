package ru.job4j.actions;

import org.junit.Test;
import ru.job4j.Item;
import ru.job4j.StubInput;
import ru.job4j.trackers.TrackerLocal;

import java.util.ArrayList;
import java.util.function.Consumer;

import static org.junit.Assert.assertEquals;

public class DeleteTest {
    private Consumer<String> output = System.out::println;

    @Test
    public void deleteItemActionClassTest() {
        // Подгатовка
        TrackerLocal example = new TrackerLocal();
        Item first = new Item("one");
        Item second = new Item("two");
        Item third = new Item("three");
        // Основной блок
        example.add(first);
        example.add(second);
        example.add(third);

        ArrayList<Item> test = new ArrayList<>();
        test.add(first);
        test.add(third);

        // Действие
        new Delete(1, "").execute(new StubInput(new String[]{second.getId()}), example, output);

        assertEquals(test, example.findAll());
    }
}
