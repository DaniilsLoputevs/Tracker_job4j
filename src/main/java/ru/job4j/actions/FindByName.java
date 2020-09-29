package ru.job4j.actions;

import ru.job4j.Input;
import ru.job4j.Tracker;
import ru.job4j.models.Item;

import java.util.function.Consumer;

/**
 * Вернуть заявку по name из tracker.
 *
 * @author Daniils Loputevs
 * @version $Id$
 * @since 23.12.19.
 **/

public class FindByName extends BaseAction {

    public FindByName(int key, String name) {
        super(key, name);
    }

    @Override
    public boolean execute(Input input, Tracker tracker, Consumer<String> output) {
        String name = input.askStr("Enter name: ");
        output.accept("table format: ID --- NAME");
        for (Item item : tracker.findByName(name)) {
            output.accept(item.getId() + " --- " + item.getName());
        }
        return true;
    }
}
