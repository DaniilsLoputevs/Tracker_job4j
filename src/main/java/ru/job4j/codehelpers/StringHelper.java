package ru.job4j.codehelpers;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Класс содержащий универсальные и вспомогательные методы для работы со String.
 *
 * @author Daniils Loputevs
 * @version $Id$
 * @since 03.03.20.
 * Last upd:  06.03.20.
 * Last JavaDoc upd:  06.03.20.
 */
public class StringHelper {

    /**
     * Добавляет в конец каждой строки "Строчный разделитель".
     * ** Метод создан на основе List.of(...).
     *
     * @param input строки.
     * @return массив из этих строк с разделителями.
     */
    public static String[] separateLines(String... input) {
        String[] tmp = new String[input.length];
        for (int i = 0; i < input.length; i++) {
            tmp[i] = Objects.requireNonNull(input[i]) + System.lineSeparator();
        }
        return tmp;
    }

    /**
     * Преобразует строки в List<String>
     * Через ',' добавляем строки. Аналог List.of()
     *
     * @param lines - строки. (через ',' добавляем строки)
     * @return List<String>
     */
    public static List<String> linesToList(String... lines) {
        return Arrays.stream(lines).collect(Collectors.toList());
    }

    /**
     * Добавляет в конец каждой строки "Строчный разделитель".
     *
     * @param list List<String> для разделения строк.
     * @return тот же, List<String>, с разделёнными строчками.
     */
    public static List<String> separateLines(List<String> list) {
        return list.stream().map(string -> string + System.lineSeparator()).collect(Collectors.toList());
    }

    public static List<String> reformatListString(String newFormat, List<String> list) {
        return list.stream().map(string -> String.format(newFormat, string)).collect(Collectors.toList());
    }

}