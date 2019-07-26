package factory;

import dao.BasketDao;
import dao.impl.BasketMySQLDaoImpl;

public class BasketDaoFactory {

    private static BasketDao instance;

    private BasketDaoFactory() {
    }

    public static BasketDao getBasketDao() {
        if (instance == null) {
            instance = new BasketMySQLDaoImpl ();
           // instance = new BasketDaoImpl();
        }
        return instance;
    }
}
