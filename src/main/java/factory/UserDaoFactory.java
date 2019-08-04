package factory;

import dao.UserDao;
import dao.impl.UserHibernateDaoImpl;

public class UserDaoFactory {

    private static UserDao instance;

    private UserDaoFactory() {
    }

    public static UserDao getUserDao() {
        if (instance == null) {
            instance = new UserHibernateDaoImpl();
        }
        return instance;
    }
}
