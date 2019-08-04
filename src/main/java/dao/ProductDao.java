package dao;

import model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {

    void addProduct(Product product);

    List<Product> getAll();

    Optional<Product> getProductById(Long productId);

    void deleteProduct(Product product);

    public void update(Product product);
}
