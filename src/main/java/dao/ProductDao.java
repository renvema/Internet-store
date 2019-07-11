package dao;

import model.Product;

import java.util.List;

public interface ProductDao {

    void addProduct(Product product);

    List<Product> getAll();

    Product getProductsById(Long productId);

    void deleteProduct(Long productId);
}
