package service.impl;

import dao.ProductDao;
import factory.ProductDaoFactory;
import model.Product;
import service.ProductService;

import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {

    private static final ProductDao productDao = ProductDaoFactory.getProductDao();

    public void addProduct(Product product) {
        productDao.addProduct(product);
    }

    public List<Product> getAll() {
        return productDao.getAll();
    }

    public Optional<Product> getProductsById(Long productId) {
        return productDao.getProductById(productId);
    }

    public void deleteProduct(Product product) {
        productDao.deleteProduct(product);
    }

    @Override
    public void update(Product product) {
        productDao.update (product);
    }
}
