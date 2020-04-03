package ru.job4j;

/**
 * Реализует interface Input - Валидация данных для ConsoleInput
 * Все методы выполяют одну функцию - спровить строку
 *
 * @author Daniils Loputevs
 * @version $Id$
 * @since 22.10.19
 **/

public class ValidateInput implements Input {
    private final Input input;

    /**
     * Конструктор
     *
     * @param input - ConsoleInput input - объявляеться в main()
     */
    ValidateInput(Input input) {
        this.input = input;
    }


    @Override
    public String askStr(String question) {
        return input.askStr(question);
    }

    @Override
    public int askInt(String question) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = input.askInt(question);
                invalid = false;
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter validate data again ");
            }
        } while (invalid);
        return value;
    }

    @Override
    public int askInt(String question, int max) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = input.askInt(question, max);
                invalid = false;
            } catch (IllegalStateException moe) {
                System.out.println("Please select key from menu.");
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter validate data again.");
            }
        } while (invalid);
        return value;
    }
}