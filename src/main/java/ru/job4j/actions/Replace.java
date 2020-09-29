package ru.job4j.actions;

import ru.job4j.Input;
import ru.job4j.Tracker;
import ru.job4j.models.Item;

import java.util.function.Consumer;

/**
 * Заменить заявку по id на новую заявку в tracker.
 *
 * @author Daniils Loputevs
 * @version $Id$
 * @since 23.12.19.
 **/

public class Replace extends BaseAction {

    public Replace(int key, String name) {
        super(key, name);
    }

    @Override
    public boolean execute(Input input, Tracker tracker, Consumer<String> output) {
        String temp = input.askStr("Enter id in tracker for replace: ");
        int id = Integer.parseInt(temp);
        if (ValidateEnterData.checkId(id, tracker)) {
            String name = input.askStr("Enter name for new item: ");
            if (!ValidateEnterData.checkName(name, tracker)) {
                var local = new Item(id, name);
                tracker.replace(id, local);
                output.accept("table format: ID --- NAME");
                output.accept(local.getId() + " --- " + local.getName());
            }
        }
        return true;
    }
}
