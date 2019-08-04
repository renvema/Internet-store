package dao.impl;

import dao.CodeDao;
import model.Code;
import model.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;
import org.hibernate.query.Query;
import java.util.Optional;

public class CodeHibernateDaoImpl implements CodeDao {

    private static final Logger logger = Logger.getLogger(CodeHibernateDaoImpl.class);

    @Override
    public void add(Code code) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(code);
            transaction.commit();
            logger.info("Code " + code + " added in db");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Code can't added in db", e);
        }
    }

    @Override
    public Optional<Code> getCodeForUser(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM Code WHERE user = :user ORDER BY id DESC ");
            query.setParameter("user", user);
            query.setMaxResults(1);
            Code code = (Code) query.uniqueResult();
            return Optional.of(code);
        } catch (Exception e) {
            logger.error("Code cann't get for user " + user, e);
        }
        return Optional.empty();
    }
}
