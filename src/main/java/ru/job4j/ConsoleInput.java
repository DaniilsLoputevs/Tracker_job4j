package ru.job4j;

import java.util.Scanner;

/**
 * Реализация interface Input - описывает ввод данных от консоли.
 * Все методы выполяют одну функцию - спровить строку
 *
 * @author Daniils Loputevs
 * @version $Id$
 * @since 03.04.20.
 **/

public class ConsoleInput implements Input {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public String askStr(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }

    @Override
    public int askInt(String question) {
        return Integer.valueOf(askStr(question));
    }

    @Override
    public int askInt(String question, int max) {
        int select = askInt(question);
        if (select < 0 || select >= max) {
            throw new IllegalStateException(String.format("Out of about %s > [0, %s]", select, max));
        }
        return select;
    }
}