package ru.job4j.actions;

import org.junit.Test;
import ru.job4j.Item;
import ru.job4j.StubInput;
import ru.job4j.trackers.TrackerLocal;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;
import java.util.function.Consumer;

import static org.junit.Assert.assertEquals;

/**
 * Вот такая должа быть строка у метода
 * System.out.println(String.format("%s %s", item.getId(), item.getName()));
 */

public class FindByNameTest {

    @Test
    public void findByNameClassTest() {
        // Подгатовка
        ByteArrayOutputStream newOutput = new ByteArrayOutputStream();
        PrintStream defaultOutput = System.out;
        System.setOut(new PrintStream(newOutput));
        Consumer<String> output = System.out::println;
        // Основной блок
        TrackerLocal trackerLocal = new TrackerLocal();
        Item example = new Item("example");
        Item test1 = new Item("test");
        Item test2 = new Item("test");
        trackerLocal.add(example);
        trackerLocal.add(test1);
        trackerLocal.add(test2);
        // Действие
        new FindByName(1, "").execute(new StubInput(new String[]{"test"}), trackerLocal, output);
        // Сравнение
        // Массив ожидаемых строк
        String[] expectArr = new String[]{
                new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                        .add(test1.getId() + " " + test1.getName())
                        .toString(),
                new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                        .add(test2.getId() + " " + test2.getName())
                        .toString()
        };
        // Переводим массив в строку + Пустой sout()
        String expect = expectArr[0] + expectArr[1] + System.lineSeparator();
        assertEquals(expect, new String(newOutput.toByteArray()));
//        assertThat(new String(newOutput.toByteArray()), is(expect));
        // Возвращаем стандартный вывод
        System.setOut(defaultOutput);
    }
}