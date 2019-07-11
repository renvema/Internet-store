package dao.impl;

import dao.ProductDao;
import db.Storage;
import model.Product;
import org.apache.log4j.Logger;

import java.util.List;

public class ProductDaoImpl implements ProductDao {

    private static final Logger logger = Logger.getLogger(ProductDaoImpl.class);

    @Override
    public void addProduct(Product product) {
        Storage.PRODUCTS.add(product);
        logger.info("Product " + product + "added in db");
    }

    @Override
    public List<Product> getAll() {
        return Storage.PRODUCTS;
    }

    @Override
    public Product getProductsById(Long productId) {
        for (Product product : Storage.PRODUCTS) {
            if (product.getId().equals(productId)) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void deleteProduct(Product product) {
        Storage.PRODUCTS.remove(product);

    }
}
