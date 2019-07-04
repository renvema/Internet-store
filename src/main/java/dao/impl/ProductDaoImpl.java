package dao.impl;

import dao.ProductDao;
import model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    private List<Product> productList = new ArrayList<>();

    @Override
    public void addProduct(Product product) {
        productList.add(product);
    }

    @Override
    public List<Product> getAll() {
        return productList;
    }
}
