package ru.job4j.actions;

import ru.job4j.Input;
import ru.job4j.Item;
import ru.job4j.Tracker;

import java.util.function.Consumer;

/**
 * Создание заявки и добавление в tracker.items
 *
 * @author Daniils Loputevs
 * @version $Id$
 * @since 23.12.19
 **/

public class Create extends BaseAction {

    public Create(int key, String name) {
        super(key, name);
    }

    @Override
    public boolean execute(Input input, Tracker tracker, Consumer<String> output) {
//        output.accept("Enter name: "); // Если нужно вести текст вручную - v2
        var local = tracker.add(new Item(input.askStr("")));
        output.accept((String.format("%s %s", local.getId(), local.getName())));
        System.out.println();
        return true;
    }
}