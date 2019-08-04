package factory;

import dao.BasketDao;
import dao.impl.BasketHibernateDaoImpl;

public class BasketDaoFactory {

    private static BasketDao instance;

    private BasketDaoFactory() {
    }

    public static BasketDao getBasketDao() {
        if (instance == null) {
            instance = new BasketHibernateDaoImpl();
        }
        return instance;
    }
}
