package ru.job4j.actions;

import ru.job4j.Input;
import ru.job4j.Item;
import ru.job4j.Tracker;

import java.util.function.Consumer;

/**
 * Вернуть все заявки из tracker.
 *
 * @author Daniils Loputevs
 * @version $Id$
 * @since 23.12.19.
 **/

public class FindAll extends BaseAction {

    public FindAll(int key, String name) {
        super(key, name);
    }

    @Override
    public boolean execute(Input input, Tracker tracker, Consumer<String> output) {
        output.accept("table format: ID --- NAME");
        for (Item item : tracker.findAll()) {
            output.accept(item.getId() + " --- " + item.getName());
        }
        return true;
    }
}
