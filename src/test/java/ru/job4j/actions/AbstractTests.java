package ru.job4j.actions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.StubInput;
import ru.job4j.Tracker;
import ru.job4j.models.Item;
import ru.job4j.trackers.HbmTracker;
import ru.job4j.trackers.TrackerLocal;
import ru.job4j.trackers.TrackerSQL;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public abstract class AbstractTests {

    protected Tracker trackerSql = new TrackerSQL(true);
    protected Tracker trackerLocal = new TrackerLocal();
    protected Tracker trackerHbm = new HbmTracker();
    // variables for test
    protected List<String> actualAnswer = new ArrayList<>();
    protected Consumer<String> output = actualAnswer::add;
    private static final Logger LOG = LoggerFactory.getLogger(AbstractTests.class);

    protected void modelTestActionsLocal(BaseAction action, StubInput stubInput) {
        action.execute(
                stubInput,
                trackerLocal,
                output
        );
    }

    protected void modelTestActionsSql(BaseAction action, StubInput stubInput) {
        action.execute(
                stubInput,
                trackerSql,
                output
        );
    }

    protected void modelTestActionsHbm(BaseAction action, StubInput stubInput) {
        action.execute(
                stubInput,
                trackerHbm,
                output
        );
    }

    protected List<String> formatExpected(List<Item> tempExpected) {
        return tempExpected.stream()
                .map(item -> item.getId() + " --- " + item.getName())
                .collect(Collectors.toList());
    }

    protected void closeSql() {
        var tmp = (TrackerSQL) trackerSql;
        tmp.close();
    }

    protected void closeHbm() {
        try {
            var tmp = (HbmTracker) trackerHbm;
            tmp.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
