package ru.job4j.todo.database;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.job4j.todo.models.Item;
import ru.job4j.todo.service.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Database.
 * @author aeremeev.
 * @version 1
 * @since 28.01.2018
 */
public class Database {
    /**
     * Creates new or updates existing item in db.
     * @param item item.
     * @return id of the item.
     */
    public int createOrUpdate(Item item) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(item);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return item.getId();
    }

    /**
     * Deletes item from db.
     * @param item item.
     */
    public void delete(Item item) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.remove(item);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * Finds item in db by its ID.
     * @param id id.
     * @return item.
     */
    public Item findById(int id) {
        Session session = null;
        Transaction transaction = null;
        Item item = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            item = (Item) session.load(Item.class, id);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return item;
    }

    /**
     * Get list of items from db.
     * @param all if true, then returns list of all items, else - only unfinished (done == false).
     * @return list of items.
     */
    public List<Item> getItems(boolean all) {
        Session session = null;
        Transaction transaction = null;
        List<Item> result = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            String query;
            if (all) {
                query = "from Item order by id";
            } else {
                query = "from Item as i where i.done = false order by id";
            }
            result = session.createQuery(query).list();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }

    /**
     * Clears table in db.
     */
    public void clearTable() {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.createNativeQuery("TRUNCATE TABLE item RESTART IDENTITY ").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
