package factory;

import dao.UserDao;
import dao.impl.UserDaoImpl;

public class UserDaoFactory {
    private static UserDao instance;

    public static UserDao getUserDao() {
        if (instance == null) {
            instance = new UserDaoImpl();
        }
        return instance;
    }
}
