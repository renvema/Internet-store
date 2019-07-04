package service.impl;

import dao.ProductDao;
import dao.impl.ProductDaoImpl;
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
}
