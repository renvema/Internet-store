package dao.impl;

import dao.ProductDao;
import db.Storage;
import model.Product;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class ProductDaoImpl implements ProductDao {

    private static final Logger logger = Logger.getLogger(ProductDaoImpl.class);

    @Override
    public void addProduct(Product product) {
        Storage.products.add(product);
        logger.info("Product " + product + "added in db");
    }

    @Override
    public List<Product> getAll() {
        return Storage.products;
    }

//    @Override
//    public Product getProductsById(Long productId) {
//        for (Product product : Storage.products) {
//            if (product.getId().equals(productId)) {
//                return product;
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public void deleteProduct(Product product) {
//        Storage.products.remove(product);
//
//    }
    public Optional<Product> getProductsById(Long productId) {
        List<Product> allProducts = getAll();
        return allProducts.stream()
                .filter(product -> product.getId().equals(productId))
                .findFirst();
    }

    public void deleteProduct(Long id) {
        Optional<Product> optionalProduct = getProductsById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            Storage.users.remove(product);
            logger.info(product + " was deleted.");
        } else {
            logger.info("Product not deleted");
        }
    }

}
