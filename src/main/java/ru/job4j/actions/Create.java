package ru.job4j.actions;

import ru.job4j.Input;
import ru.job4j.Tracker;
import ru.job4j.models.Item;

import java.util.function.Consumer;

/**
 * Создание заявки и добавление в tracker.items
 *
 * @author Daniils Loputevs
 * @version $Id$
 * @since 23.12.19.
 **/

public class Create extends BaseAction {

    public Create(int key, String name) {
        super(key, name);
    }

    @Override
    public boolean execute(Input input, Tracker tracker, Consumer<String> output) {
        var local = tracker.add(new Item(input.askStr("Enter name: ")));
        output.accept("table format: ID --- NAME");
        output.accept(local.getId() + " --- " + local.getName());
        return true;
    }
}