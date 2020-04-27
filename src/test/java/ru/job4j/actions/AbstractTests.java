package ru.job4j.actions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.ConfigLoader;
import ru.job4j.Item;
import ru.job4j.StubInput;
import ru.job4j.Tracker;
import ru.job4j.db.ConnectionRollback;
import ru.job4j.trackers.TrackerLocal;
import ru.job4j.trackers.TrackerSQL;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public abstract class AbstractTests {
    // TrackerSQLTest - variables
    protected String configPath = "./src/main/resources/connection_config.properties";
    protected Tracker tracker = trackerSqlInit();
    protected List<String> actualAnswer = new ArrayList<>();
    protected Consumer<String> output = actualAnswer::add;

    // TrackerLocal
    protected Tracker trackerLocal = new TrackerLocal();
    private static final Logger LOG = LoggerFactory.getLogger(AbstractTests.class);

    private Tracker trackerSqlInit() {
        Tracker result = null;
        try {
            var config = new ConfigLoader(configPath);
            var connection = DriverManager.getConnection(
                    config.value("url"),
                    config.value("username"),
                    config.value("password")
            );
            connection = ConnectionRollback.create(connection);

            result = new TrackerSQL(connection);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    protected void modelTestActionsSql(BaseAction action, StubInput stubInput) {
        action.execute(
                stubInput,
                tracker,
                output
        );
    }

    protected List<String> formatExpected(List<Item> tempExpected) {
        return tempExpected.stream()
                .map(item -> String.format("%s %s", item.getId(), item.getName()))
                .collect(Collectors.toList());
    }

    protected void close() {
        var tmp = (TrackerSQL) tracker;
        tmp.close();
    }

    protected void modelTestActionsLocal(BaseAction action, StubInput stubInput) {
        action.execute(
                stubInput,
                trackerLocal,
                output
        );
    }

}
