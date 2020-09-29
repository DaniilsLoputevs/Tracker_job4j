package ru.job4j.acodehelp;

import java.util.function.Consumer;

public class ArrayHelp {
    public static <T> void forEach(T[] array, Consumer<T> action) {
        for (var temp : array) {
            action.accept(temp);
        }
    }

}
