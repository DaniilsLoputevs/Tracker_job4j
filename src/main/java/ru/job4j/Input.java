package ru.job4j;

/**
 * Замена Scanner scanner т.к. нужно как меньше внешних зависимостей
 * Все методы этого interface выполняют одну функцию - спросить строку
 *
 * @author Daniils Loputevs
 * @version $Id$
 * @since 22.10.19
 **/

public interface Input {

    String askStr(String question);

    int askInt(String question);

    int askInt(String question, int max);
}