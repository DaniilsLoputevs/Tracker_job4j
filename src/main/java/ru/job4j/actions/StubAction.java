package ru.job4j.actions;

import ru.job4j.Input;
import ru.job4j.Tracker;

import java.util.function.Consumer;

/**
 * Заглушка - Имитация действий для tracker.items
 * Нужна для тестов
 *
 * @author Daniils Loputevs
 * @version $Id$
 * @since 23.12.19
 **/

public class StubAction extends BaseAction {
    /**
     * переменная отвечает за вызов метода, если он был вызван, то call >> = true
     */
    private boolean call; // по умолчанию должна быть false;

    public StubAction(int key, String name, boolean call) {
        super(key, name);
        this.call = call;
    }


    @Override
    public String name() {
        return "Stub action";
    }

    @Override
    public boolean execute(Input input, Tracker tracker, Consumer<String> output) {
        call = true;
        return false;
    }

    public boolean isCall() {
        return call;
    }
}