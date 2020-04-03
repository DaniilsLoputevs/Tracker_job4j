package ru.job4j.actions;

import ru.job4j.Input;
import ru.job4j.Item;
import ru.job4j.Tracker;

import java.util.function.Consumer;

/**
 * Заменить заявку по id на новую заявку в tracker.items
 *
 * @author Daniils Loputevs
 * @version $Id$
 * @since 23.12.19
 **/

public class Replace extends BaseAction {

    public Replace(int key, String name) {
        super(key, name);
    }

    @Override
    public boolean execute(Input input, Tracker tracker, Consumer<String> output) {
//        output.accept(("Enter id in tracker for replace: "));  // Если нужно вести текст вручную
        String id = input.askStr("");
        if (ValidateEnterData.checkId(id, tracker)) {
//          output.accept(("Enter name for new item: "));    // Если нужно вести текст вручную
            String name = input.askStr("");
            if (!ValidateEnterData.checkName(name, tracker)) {
                var local = new Item(id, name);
                tracker.replace(id, local);
                output.accept((String.format("%s %s", local.getId(), local.getName())));
                System.out.println();
            }
        }
        return true;
    }
}
