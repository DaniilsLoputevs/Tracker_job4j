package ru.job4j.actions;

import ru.job4j.UserAction;

/**
 * abstract class - implements UserAction
 * Реализует общие методы Классов в папке actions.
 *
 * @author Daniils Loputevs
 * @version $Id$
 * @since 17.11.19
 **/

public abstract class BaseAction implements UserAction {
    private final int key;
    private final String name;

    protected BaseAction(final int key, final String name) {
        this.key = key;
        this.name = name;
    }

    @Override
    public int key() {
        return this.key;
    }

    @Override
    public String name() {
        return String.format("%s : %s", this.key, this.name);
    }
}