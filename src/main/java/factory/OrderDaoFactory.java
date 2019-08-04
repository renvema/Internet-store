package factory;

import dao.OrderDao;
import dao.impl.OrderHibernateDaoImpl;

public class OrderDaoFactory {

    private static OrderDao instance;

    private OrderDaoFactory() {
    }

    public static OrderDao getInstance() {
        if (instance == null) {
            instance = new OrderHibernateDaoImpl();
        }
        return instance;
    }
}
