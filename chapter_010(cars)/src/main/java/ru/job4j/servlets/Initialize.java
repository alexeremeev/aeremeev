package ru.job4j.servlets;

import ru.job4j.service.HibernateUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Initialize - init and destroy HibernateUtil singleton.
 * @author aeremeev.
 * @version 1
 * @since 29.01.2018
 */
public class Initialize implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        HibernateUtil.getSessionFactory();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        HibernateUtil.shutdown();
    }
}
