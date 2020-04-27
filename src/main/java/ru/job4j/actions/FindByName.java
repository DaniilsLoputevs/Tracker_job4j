package ru.job4j.actions;

import ru.job4j.Input;
import ru.job4j.Item;
import ru.job4j.Tracker;

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
//        System.out.print("Enter id: "); // Если нужно вести текст вручную
        String name = input.askStr("");
        for (Item item : tracker.findByName(name)) {
            output.accept((String.format("%s %s", item.getId(), item.getName())));
        }
        System.out.println();
        return true;
    }
}
