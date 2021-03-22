package ru.job4j.actions;

import ru.job4j.Input;
import ru.job4j.Tracker;
import ru.job4j.models.Item;

import java.util.function.Consumer;

/**
 * Вернуть заявку по id из tracker.
 *
 * @author Daniils Loputevs
 * @version $Id$
 * @since 23.12.19.
 **/

public class FindById extends BaseAction {

    public FindById(int key, String name) {
        super(key, name);
    }

    @Override
    public boolean execute(Input input, Tracker tracker, Consumer<String> output) {
        String temp = input.askStr("Enter id: ");
        int id = Integer.parseInt(temp);

        if (ValidateEnterData.checkId(id, tracker)) {
            Item local = tracker.findById(id);
            output.accept("table format: ID --- NAME");
            output.accept(local.getId() + " --- " + local.getName());
        }
        return true;
    }
}
