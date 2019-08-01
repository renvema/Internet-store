package dao.impl;

import dao.BasketDao;
import model.Basket;
import model.Product;
import model.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.util.Optional;

public class BasketHibernateDaoImpl implements BasketDao {

    private static final Logger logger = Logger.getLogger(BasketHibernateDaoImpl.class);

    @Override
    public void addBasket(Basket basket) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(basket);
            transaction.commit();
            logger.info("Basket " + basket + " added in db");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Basket can't added in db", e);
        }
    }

    @Override
    public void addProductToBasket(Basket basket, Product product) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            basket.getProducts().add(product);
            session.update(basket);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Product " + product + "can't be added in basket", e);
        }
    }

    @Override
    public int size(Basket basket) {
        return basket.getProducts().size();
    }

    @Override
    public Optional<Basket> getBasketByUser(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM Basket WHERE user = :user ORDER BY id DESC");
            query.setParameter("user", user);
            query.setMaxResults(1);
            Basket basket = (Basket) query.uniqueResult();
            return Optional.of(basket);
        } catch (Exception e) {
            logger.error("Can't get from basket user " + user, e);
        }
        return Optional.empty();
    }

    @Override
    public void clean(User user) {
    }
}
