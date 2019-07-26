package factory;

import service.OrderService;
import service.impl.OrderServiceImpl;

public class OrderServiceFactory {

    private static OrderService instance;

    private OrderServiceFactory() {
    }

    public static OrderService getInstance() {
        if (instance == null) {
            instance = new OrderServiceImpl();
        }
        return instance;
    }
}
