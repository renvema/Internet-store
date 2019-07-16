package dao;

import model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {

    void addProduct(Product product);

    List<Product> getAll();

    Optional<Product> getProductsById(Long productId);

    void deleteProduct(Long id);
}
