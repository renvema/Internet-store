package factory;

import service.BasketService;
import service.impl.BasketServiceImpl;

public class BasketServiceFactory {

    private static BasketService instance;

    private BasketServiceFactory() {
    }

    public static BasketService getInstance() {
        if (instance == null) {
            instance = new BasketServiceImpl();
        }
        return instance;
    }
}
