package ru.job4j.actions.trackersql;

//import ru.job4j.tracker.*;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.function.Consumer;
//import java.util.stream.Collectors;
//
//import static org.junit.Assert.assertEquals;
//
//public class BaseTest {
//    private Tracker tracker = new TrackerSQL();
//    private List<String> actualAnswer = new ArrayList<>();
//    private Consumer<String> output = actualAnswer::add;
//
//    protected void formOne(BaseAction action, String itemName, String[] stubInput, String methodName) {
//
//        Item testItem = new Item(itemName);
//        tracker.add(testItem);
//        if ("findById".equals(methodName)) {
//            var temp = tracker.findByName(itemName).load(0);
//            testItem.setId(temp.getId());
//           stubInput = new String[] { temp.getId() };
//            new TrackerSQLTest().cleanBaseTracker(testItem);
//        }
//
//        // Действие
//        action.execute(
//                new StubInput(stubInput),
//                tracker,
//                output
//        );
//        List<Item> result;
//        if ("findAll".equals(methodName)) {
//            result = tracker.findAll();
//        } else if ("findById".equals(methodName)){
//            result = List.of(tracker.findByName(itemName).load(0));
//            var temp = result.load(0);
//            result = List.of(temp);
//        } else {
//            result = tracker.findByName(itemName);
//        }
//
//
//        List<String> expected = result.stream()
//                .map(item -> String.format("%s %s", item.getId(), item.getName()))
//                .collect(Collectors.toList());
//
//        assertEquals(expected, actualAnswer);
//
//        // clean base after test
//
//        if ("create".equals(methodName)) {
//            List<Item> cleanList = tracker.findByName(itemName);
//            int i = 0;
//            while (!cleanList.isEmpty()) {
//                new TrackerSQLTest().cleanBaseTracker(cleanList.remove(i));
//                i++;
//            }
//        } else {
//            new TrackerSQLTest().cleanBaseTracker(testItem);
//        }
//
//
//    }
//}