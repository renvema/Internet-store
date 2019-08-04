package dao.impl;

import dao.ProductDao;
import model.Product;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductHibernateDaoImpl implements ProductDao {

    private static final Logger logger = Logger.getLogger(ProductHibernateDaoImpl.class);
    @Override
    public void addProduct(Product product) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(product);
            transaction.commit();
            logger.info("Product " + product + " added in db");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Product can't added in db", e);
        }
    }

    @Override
    public List<Product> getAll() {
        List<Product> productList = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            productList = session.createQuery("FROM Product",Product.class).list();
        } catch (Exception e) {
            logger.error("Try to get all products was failed!", e);
        }
        return productList;
    }

    @Override
    public Optional<Product> getProductById(Long productId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Product product = session.get(Product.class, productId);
            return Optional.of(product);
        } catch (Exception e) {
            logger.error("Product = " + productId + " can't be taken from DB", e);
        }
        return Optional.empty();
    }

    @Override
    public void deleteProduct(Product product) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(product);
            transaction.commit();
            logger.info("Product " + product + " remove in db");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Product can't remove in db", e);
        }
    }

    @Override
    public void update(Product product) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(product);
            transaction.commit();
            logger.info("Product " + product + " update in db");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Product can't update", e);
        }
    }
}
