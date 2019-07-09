package dao.impl;

import dao.ProductDao;
import db.Storage;
import model.Product;
import org.apache.log4j.Logger;

import java.util.List;

public class ProductDaoImpl implements ProductDao {

    private static final Logger logger = Logger.getLogger(UserDaoImpl.class);

    @Override
    public void addProduct(Product product) {
        Storage.PRODUCTS.add(product);
        logger.info("Product " + product + "added in db");
    }

    @Override
    public List<Product> getAll() {
        return Storage.PRODUCTS;
    }
}
