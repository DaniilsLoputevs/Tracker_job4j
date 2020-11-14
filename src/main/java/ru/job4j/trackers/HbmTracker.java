package ru.job4j.trackers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.Tracker;
import ru.job4j.acodehelp.ArrayHelp;
import ru.job4j.models.Item;

import java.util.List;
import java.util.function.Function;

public class HbmTracker implements Tracker, AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @Override
    public Item add(Item item) {
        transactionCore(session -> {
            session.save(item);
            return item;
        });
        return item;
    }

    @Override
    public List<Item> addAll(Item... items) {
        transactionCore(session -> {
            ArrayHelp.forEach(items, session::save);
            return null;
        });
        return List.of(items);
    }

    @Override
    public boolean replace(int id, Item item) {
        return (boolean) transactionCore(session -> {
            session.update(item);
            return true;
        });
    }

    @Override
    public boolean delete(int id) {
        Item temp = new Item(id, "");
        transactionCore(session -> {
            session.delete(temp);
            return true;
        });
        return true;
    }

    @Override
    public boolean deleteAll() {
        transactionCore(session ->
            session.createSQLQuery("truncate table items")
                    .executeUpdate()
        );
        return true;
    }

    @Override
    public List<Item> findAll() {
        return (List<Item>) transactionCore(session ->
                session.createQuery("from Item")
                        .list()
        );
    }

    @Override
    public List<Item> findByName(String name) {
        final var NewName = "'" + name + "'";
        return (List<Item>) transactionCore(session ->
                session.createQuery("from Item where name=" + NewName)
                        .list()
        );
    }

    @Override
    public Item findById(int id) {
        return (Item) transactionCore(session ->
                session.createQuery("from Item where id=" + id)
                        .getSingleResult()
        );
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
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }

    private Object transactionCore(Function<Session, Object> action) {
        Session session = sf.openSession();
        session.beginTransaction();

        Object rsl = action.apply(session);

        session.getTransaction().commit();
        session.close();

        return rsl;
    }
}
