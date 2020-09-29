package ru.job4j.actions.debug;

import ru.job4j.Input;
import ru.job4j.Tracker;
import ru.job4j.actions.BaseAction;

import java.util.function.Consumer;

public class DeleteAll extends BaseAction {
    public DeleteAll(int key, String name) {
        super(key, name);
    }

    @Override
    public boolean execute(Input input, Tracker tracker, Consumer<String> output) {
        output.accept("Please wait... deleting in progress.");
        var time = System.currentTimeMillis();
        tracker.deleteAll();
        output.accept("Spend time: " + (System.currentTimeMillis() - time) / 100 + " ms");
        return true;
    }

//    /**
//     * forEach version.
//     * @param input -
//     * @param tracker -
//     * @param output -
//     * @return -
//     */
//    @Override
//    public boolean execute(Input input, Tracker tracker, Consumer<String> output) {
//
//        output.accept("Please wait... deleting in progress.");
//        var count = 0;
//        var time = System.currentTimeMillis();
//        for (var item : tracker.findAll()) {
//            tracker.delete(item.getId());
//            System.out.println(count++);
//        }
//       output.accept("Spend time: " + (System.currentTimeMillis() - time) / 100 + " ms");
//        return true;
//    }
}
