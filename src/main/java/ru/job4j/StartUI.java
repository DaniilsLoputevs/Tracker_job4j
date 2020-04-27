package ru.job4j;

import ru.job4j.actions.*;
import ru.job4j.trackers.TrackerLocal;

import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * Главый класс с main методом.
 *
 * @author Daniils Loputevs
 * @version $Id$
 * @since 03.04.20.
 **/

public class StartUI {

    /**
     * Метод инициилизации программы.
     *
     * @param input   - ValidateInput(input).
     * @param tracker tracker - Менеджер заявок.
     * @param actions - ArrayList<UserAction> - массив действий с заявками.
     */
    void init(Input input, Tracker tracker, ArrayList<UserAction> actions, Consumer<String> output) {
        boolean run = true;
        while (run) {
            this.showMenu(actions);
            int select = input.askInt("Select: ", actions.size());
            UserAction action = actions.get(select);
            run = action.execute(input, tracker, output);
        }
    }

    /**
     * Показывает имина всех Действий добавленых в actions.
     *
     * @param actions - ArrayList<UserAction> - список действий.
     */
    void showMenu(ArrayList<UserAction> actions) {
        System.out.println("Menu.");
        for (UserAction action : actions) {
            System.out.println(action.name());
        }
    }

    /**
     * Заполняет список с возможными действиями.
     *
     * @return actions - задаёт содержимое массива.
     */
    private static ArrayList<UserAction> setActions() {
        ArrayList<UserAction> actions = new ArrayList<>();
        actions.add(new ExitOfProgramm(0, "=== Exit ===="));
        actions.add(new Create(1, "=== Create a new Item ===="));
        actions.add(new Replace(2, "=== Replace Item ===="));
        actions.add(new Delete(3, "=== Delete Item ===="));
        actions.add(new FindAll(4, "=== Show all Items ===="));
        actions.add(new FindByName(5, "=== Find Item by Name ===="));
        actions.add(new FindById(6, "=== Find Item by Id ===="));
        return actions;
    }


    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Consumer<String> output = System.out::println;
        Input validate = new ValidateInput(input);
        Tracker tracker = new TrackerLocal();
        ArrayList<UserAction> actions = setActions();
        new StartUI().init(validate, tracker, actions, output);
    }
}