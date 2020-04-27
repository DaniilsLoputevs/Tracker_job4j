package ru.job4j.actions;

import ru.job4j.Input;
import ru.job4j.Tracker;

import java.util.function.Consumer;

/**
 * Удаление заявки из tracker.
 *
 * @author Daniils Loputevs
 * @version $Id$
 * @since 23.12.19.
 **/

public class Delete extends BaseAction {

    public Delete(int key, String name) {
        super(key, name);
    }

    @Override
    public boolean execute(Input input, Tracker tracker, Consumer<String> output) {
//        output.accept("Enter id: "); // Если нужно вести текст вручную
        String id = input.askStr("");
        if (ValidateEnterData.checkId(id, tracker)) {
            tracker.delete(id);
            System.out.println();
        }
        return true;
    }
}
