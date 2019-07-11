package service.impl;

import dao.ProductDao;
import dao.impl.ProductDaoImpl;
import db.Storage;
import model.Product;
import service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private static final ProductDao productDao = new ProductDaoImpl();

    public void addProduct(Product product) {
        productDao.addProduct(product);
    }

    public List<Product> getAll() {
        return productDao.getAll();
    }
    public Product getProductsById(Long productId) {
        return productDao.getProductsById(productId);
    }

    public void deleteProduct(Product product) {
        productDao.deleteProduct(product);
    }

}
