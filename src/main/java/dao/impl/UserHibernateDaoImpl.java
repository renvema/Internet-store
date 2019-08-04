package dao.impl;

import dao.UserDao;
import model.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;
import utils.SaltHashUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserHibernateDaoImpl implements UserDao {

    private static final Logger logger = Logger.getLogger(UserHibernateDaoImpl.class);

    @Override
    public void addUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String saltPassword= SaltHashUtil.getSHA512SecurePassword(user.getPassword(),user.getSalt());
            user.setPassword(saltPassword);
            session.save(user);
            transaction.commit();
            logger.info("User " + user + " added in db");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("User can't added in db", e);
        }
    }

    @Override
    public List<User> getAll() {
        List<User> userList = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            userList = session.createQuery("FROM User",User.class).list();
        } catch (Exception e) {
            logger.error("Try to get all users was failed!", e);
        }
        return userList;
    }


    @Override
    public Optional<User> getUserById(Long userId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            User user = session.get(User.class, userId);
            return Optional.of(user);
        } catch (Exception e) {
            logger.error("User id = " + userId + " can't be taken from DB", e);
        }
        return Optional.empty();
    }

    @Override
    public void deleteUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(user);
            transaction.commit();
            logger.info("User " + user + " remove in db");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("User can't remove in db", e);
        }
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM User WHERE email = :email");
            query.setParameter("email", email);
            User user = (User) query.uniqueResult();
            return Optional.of(user);
        } catch (Exception e) {
            logger.error("User can't find by email  = " + email, e);
        }
        return Optional.empty();
    }

    @Override
    public void update(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String saltPassword= SaltHashUtil.getSHA512SecurePassword(user.getPassword(),user.getSalt());
            user.setPassword(saltPassword);
            session.update(user);
            transaction.commit();
            logger.info("User " + user + " update in db");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("User can't update", e);
        }
    }
}
