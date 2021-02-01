package ru.job4j.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.acodehelp.ConslLog;
import ru.job4j.models.Item;
import ru.job4j.trackers.HbmTracker;

import java.util.List;

public class HibernateRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        try {


//            System.out.println("START");
//            Item item = create(new Item("Learn Hibernate"), sf);
//            ConslLog.log("item v1:", item);
//            item.setName("Learn Hibernate 5.");
//            update(item, sf);
//            ConslLog.log("item v2:", item);
//            Item rsl = findById(item.getId(), sf);
//            ConslLog.log("item v3:", rsl);
//            delete(rsl.getId(), sf);
//            List<Item> list = findAll(sf);
//            for (Item it : list) {
//                ConslLog.log("delete item", it);
//            }


//            create(new Item(1, "test"), sf);
//            create(new Item(2, "two"), sf);
//            create(new Item(3, "three"), sf);
//                delete(5, sf);
//                delete(6, sf);
//                delete(7, sf);
            var tracker = new HbmTracker();
            var temp = tracker.findByName("test");
            ConslLog.log("item", temp);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public static Item create(Item item, SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        session.close();
        return item;
    }

    public static void update(Item item, SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.update(item);
        session.getTransaction().commit();
        session.close();
    }

    public static void delete(Integer id, SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();

        Item item = new Item(null);
        item.setId(id);
        session.delete(item);

        session.getTransaction().commit();
        session.close();
    }

    public static List<Item> findAll(SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        List result = session.createQuery("from ru.job4j.models.Item").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public static Item findById(Integer id, SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        Item result = session.get(Item.class, id);
        session.getTransaction().commit();
        session.close();
        return result;
    }
}
