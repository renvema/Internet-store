package dao.impl;

import dao.OrderDao;
import model.Order;
import model.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.util.Optional;

public class OrderHibernateDaoImpl implements OrderDao {

    private static final Logger logger = Logger.getLogger(OrderHibernateDaoImpl.class);

    @Override
    public Optional<Order> getOrder(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM Order WHERE user = :user ORDER BY id DESC");
            query.setParameter("user", user);
            query.setMaxResults(1);
            Order order = (Order) query.uniqueResult();
            return Optional.of(order);
        } catch (Exception e) {
            logger.error("Can't get order", e);
        }
        return Optional.empty();
    }

    @Override
    public void add(Order order) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
            logger.info("Order " + order + " added in db");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Order can't added in db", e);
        }
    }
}
