package service;

import model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    void addProduct(Product product);

    List<Product> getAll();

    Optional<Product> getProductsById(Long productId);

    void deleteProduct(Product product);

    void update(Product product);
}
