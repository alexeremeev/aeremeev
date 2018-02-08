package ru.job4j.todo.database;

import org.hibernate.Session;
import ru.job4j.todo.models.Item;
import ru.job4j.todo.service.HibernateUtil;

import java.util.List;

/**
 * Database.
 * @author aeremeev.
 * @version 1.1
 * @since 28.01.2018
 */
public class Database {

    private TransactionManager manager = new TransactionManager(HibernateUtil.getSessionFactory());

    /**
     * Creates new or updates existing item in db.
     * @param item item.
     * @return id of the item.
     */
    public int createOrUpdate(Item item) {
        return manager.doInTransaction(new TransactionCallable<Integer>() {
            @Override
            public Integer execute(Session session) {
                session.saveOrUpdate(item);
                return item.getId();
            }
        });
    }
    /**
     * Deletes item from db.
     * @param item item.
     */
    public void delete(Item item) {
        manager.doInTransaction(new TransactionCallable<Void>() {
            @Override
            public Void execute(Session session) {
                session.delete(item);
                return null;
            }
        });
    }

    /**
     * Finds item in db by its ID.
     * @param id id.
     * @return item.
     */
    public Item findById(int id) {
        return manager.doInTransaction(new TransactionCallable<Item>() {
            @Override
            public Item execute(Session session) {
                return session.load(Item.class, id);
            }
        });
    }

    /**
     * Get list of items from db.
     * @param all if true, then returns list of all items, else - only unfinished (done == false).
     * @return list of items.
     */
    public List<Item> getItems(boolean all) {
        return manager.doInTransaction(new TransactionCallable<List<Item>>() {
            @Override
            public List<Item> execute(Session session) {
                String query;
                if (all) {
                    query = "from Item order by id";
                } else {
                    query = "from Item as i where i.done = false order by id";
                }
                return session.createQuery(query).list();
            }
        });
    }

    /**
     * Clears table in db.
     */
    public void clearTable() {
        manager.doInTransaction(new TransactionCallable<Void>() {
            @Override
            public Void execute(Session session) {
                session.createNativeQuery("TRUNCATE TABLE item RESTART IDENTITY ").executeUpdate();
                return null;
            }
        });
    }
}
