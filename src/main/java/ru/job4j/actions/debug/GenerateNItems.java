package ru.job4j.actions.debug;

import ru.job4j.Input;
import ru.job4j.Tracker;
import ru.job4j.actions.BaseAction;
import ru.job4j.models.Item;

import java.util.Random;
import java.util.function.Consumer;

public class GenerateNItems extends BaseAction {
    public GenerateNItems(int key, String name) {
        super(key, name);
    }

    @Override
    public boolean execute(Input input, Tracker tracker, Consumer<String> output) {
        int count = input.askInt("Enter how many items you want: ");
        var random = new Random();
        output.accept("table format: ID --- NAME");
        for (int i = 0; i < count; i++) {
            var temp = new Item(Integer.parseInt("" + random.nextInt()), "Debug_Items: " + i);
            var local = tracker.add(temp);
            output.accept(local.getId() + " --- " + local.getName());
        }
        return true;
    }
}
