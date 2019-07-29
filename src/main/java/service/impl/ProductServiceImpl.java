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
        return productDao.getProductsById(productId);
    }

    public void deleteProduct(Long id) {
        productDao.deleteProduct(id);
    }

    @Override
    public void update(Product product) {
        productDao.update (product);
    }
}
