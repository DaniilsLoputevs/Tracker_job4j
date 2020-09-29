package ru.job4j;

import org.junit.Test;
import ru.job4j.actions.*;
import ru.job4j.trackers.TrackerLocal;

import java.util.ArrayList;
import java.util.function.Consumer;

import static org.junit.Assert.assertTrue;

public class StartUITest {
    private Consumer<String> output = System.out::println;

    @Test
    public void init() {
        // создаём искусственный ввод информации
        StubInput input = new StubInput(
                new String[]{"0", "0"}
        );
        // создаём искусственное действие
        StubAction action = new StubAction(1, "", false);
        ArrayList<UserAction> arrayList = new ArrayList<>();
        arrayList.add(action);
        // проверяем метод (действие должно вернуть true, т.к. оно было вызвано)
        new StartUI().init(input, new TrackerLocal(), arrayList, output);
        assertTrue(action.isCall());
    }

    @Test
    public void showMenu() {
        // Проверяет, что в списке ВСЕ действия что должы там быть
        ArrayList<UserAction> actions = new ArrayList<>();
        actions.add(new ExitOfProgramm(0, "=== Exit ===="));
        actions.add(new Create(1, "=== Create a new Item ===="));
        actions.add(new Replace(2, "=== Replace Item ===="));
        actions.add(new Delete(3, "=== Delete Item ===="));
        actions.add(new FindAll(4, "=== Show all Items ===="));
        actions.add(new FindByName(5, "=== Find Item by Name ===="));
        actions.add(new FindById(6, "=== Find Item by Id ===="));
        new StartUI().showMenu(actions);
    }
}