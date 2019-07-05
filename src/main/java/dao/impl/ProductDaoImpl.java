package dao.impl;

import dao.ProductDao;
import db.Storage;
import model.Product;

import java.util.List;

public class ProductDaoImpl implements ProductDao {

    @Override
    public void addProduct(Product product) {
        Storage.PRODUCTS.add(product);
    }

    @Override
    public List<Product> getAll() {
        return Storage.PRODUCTS;
    }
}
