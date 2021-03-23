package ru.job4j.trackers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.ConfigLoader;
import ru.job4j.Tracker;
import ru.job4j.db.ConnectionRollback;
import ru.job4j.models.Item;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс менеджер заявок.
 *
 * @author Daniils Loputevs
 * @version $Id$
 * @since 01.10.20.
 * Created 25.03.20.
 */
public class TrackerSQL implements Tracker, AutoCloseable {
    private Connection connection;
    private static final Logger LOG = LoggerFactory.getLogger(TrackerSQL.class);
    
    public TrackerSQL(boolean rollBack) {
        try {
            if (rollBack) {
                this.connection = ConnectionRollback.create(initDefaultConnectionOrNull());
            } else {
                this.connection = initDefaultConnectionOrNull();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("TrackerSql: Exception in Constructor, see: ", e);
            System.err.println("TrackerSql: Exception in Constructor(), see:\r\n" + e);
        }
    }
    
    /**
     * Init Connection to DB.
     */
    private static Connection initDefaultConnectionOrNull() {
        Connection connection = null;
        var config = new ConfigLoader(ConfigLoader.getPsqlConfigPath());
        try {
            connection = DriverManager.getConnection(
                    config.value("url"),
                    config.value("username"),
                    config.value("password")
            );
            if (connection == null) {
                throw new RuntimeException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("TrackerSql: Exception in Constructor, see: ", e);
            System.err.println("TrackerSql: Exception in initConnection(), see:\r\n" + e);
        }
        return connection;
    }
    
    @Override
    public Item add(Item item) {
        try (var st = this.connection.prepareStatement(
                "insert into items(name) values(?)",
                Statement.RETURN_GENERATED_KEYS)) {
            st.setString(1, item.getName());
            st.execute();
            try (var id = st.getGeneratedKeys()) {
                if (id.next()) {
                    item.setId(id.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("TrackerSql: Exception in Constructor, see: ", e);
            System.err.println("TrackerSql: Exception in add(), see:\r\n" + e);
        }
        return item;
    }
    
    @Override
    public List<Item> addAll(Item... items) {
        List<Item> result = new ArrayList<>();
        for (var item : items) {
            if (item != null) {
                this.add(item);
                result.add(item);
            }
        }
        return result;
    }
    
    @Override
    public boolean replace(int id, Item item) {
        var result = false;
        try (var st = this.connection.prepareStatement("update items set name=(?) where id=(?);")) {
            st.setString(1, item.getName());
            st.setInt(2, id);
            st.executeUpdate();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("TrackerSql: Exception in Constructor, see: ", e);
            System.err.println("TrackerSql: Exception in replace(), see:\r\n" + e);
        }
        return result;
    }
    
    @Override
    public boolean delete(int id) {
        boolean result = false;
        try (var st = this.connection.prepareStatement("delete from items where id=?")) {
            st.setInt(1, id);
            st.executeUpdate();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("TrackerSql: Exception in Constructor, see: ", e);
            System.err.println("TrackerSql: Exception in delete(), see:\r\n" + e);
        }
        return result;
    }
    
    @Override
    public void deleteAll() {
        try (var st = this.connection.prepareStatement("truncate table items ")) {
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("TrackerSql: Exception in Constructor, see: ", e);
            System.err.println("TrackerSql: Exception in deleteAll(), see:\r\n" + e);
        }
    }
    
    @Override
    public boolean deleteAll() {
        boolean result = false;
        try (var st = this.connection.prepareStatement("truncate table items ")) {
            st.execute();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("Exception in - TrackerSQL.delete()", e);
        }
        return result;
    }

    @Override
    public List<Item> findAll() {
        List<Item> result = new ArrayList<>();
        try (var st = this.connection.prepareStatement("select * from items;")) {
            try (var rs = st.executeQuery()) {
                while (rs.next()) {
                    result.add(new Item(
                            rs.getInt("id"),
                            rs.getString("name")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("TrackerSql: Exception in Constructor, see: ", e);
            System.err.println("TrackerSql: Exception in findAll(), see:\r\n" + e);
        }
        return result;
    }
    
    @Override
    public List<Item> findByName(String key) {
        List<Item> result = new ArrayList<>();
        try (var st = this.connection.prepareStatement("select * from items where name=?")) {
            st.setString(1, key);
            try (var rs = st.executeQuery()) {
                while (rs.next()) {
                    result.add(new Item(
                            rs.getInt("id"),
                            rs.getString("name")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("TrackerSql: Exception in Constructor, see: ", e);
            System.err.println("TrackerSql: Exception in findByName(), see:\r\n" + e);
        }
        return result;
    }
    
    @Override
    public Item findById(int id) {
        Item result = null;
        try (var st = this.connection.prepareStatement("select * from items where id=?")) {
            st.setInt(1, id);
            try (var rs = st.executeQuery()) {
                if (rs.next()) {
                    result = new Item(rs.getInt("id"), rs.getString("name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("TrackerSql: Exception in Constructor, see: ", e);
            System.err.println("TrackerSql: Exception in findById(), see:\r\n" + e);
        }
        return result;
    }
    
    @Override
    public boolean containsId(int id) {
        var temp = findById(id);
        return temp != null && temp.getId() != -1;
    }
    
    @Override
    public boolean containsName(String name) {
        return !findByName(name).isEmpty();
    }
    
    @Override
    public void close() {
        if (this.connection != null) {
            try {
                this.connection.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }
}