package service;

import model.Product;

import java.util.List;

public interface ProductService {
    void addProduct(Product product);

    List<Product> getAll();

}
