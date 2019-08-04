package factory;

import dao.ProductDao;
import dao.impl.ProductHibernateDaoImpl;

public class ProductDaoFactory {

    private static ProductDao instance;

    private ProductDaoFactory() {
    }

    public static ProductDao getProductDao() {
        if (instance == null) {
            instance = new ProductHibernateDaoImpl();
        }
        return instance;
    }
}
