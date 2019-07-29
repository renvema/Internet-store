package dao.impl;

import dao.ProductDao;
import model.Product;
import org.apache.log4j.Logger;
import utils.DbConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductMySQLDaoImpl implements ProductDao {

    private static final Logger logger = Logger.getLogger(ProductMySQLDaoImpl.class);
    private static final String ADD_PRODUCT = "INSERT INTO products (title, description, price)" +
            " VALUES (?, ?, ?)";
    private static final String GET_ALL_PRODUCT = "SELECT * FROM products";
    private static final String GET_PRODUCT_BY_ID = "SELECT * FROM products WHERE id=?";
    private static final String DELETE_PRODUCT = "DELETE FROM products WHERE id=?";
    private static final String UPDATE_PRODUCT = "UPDATE products SET title=?, description=?, " +
            "price=? WHERE id=?";

    @Override
    public void addProduct(Product product) {
        try (Connection connection = DbConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_PRODUCT);
            preparedStatement.setString(1, product.getTitle());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.execute();
            logger.info("Product " + product + "added in db");
        } catch (SQLException e) {
            logger.error("Products can't added", e);
        }
    }

    @Override
    public List<Product> getAll() {
        List<Product> productList = new ArrayList<>();
        try (Connection connection = DbConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_PRODUCT);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product userFromDb = new Product(
                        resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getDouble("price"));
                productList.add(userFromDb);
            }
            logger.info("All products get from database");
        } catch (SQLException e) {
            logger.error("Products can't get from database", e);
        }
        return productList;
    }

    @Override
    public Optional<Product> getProductsById(Long productId) {
        try (Connection connection = DbConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCT_BY_ID);
            preparedStatement.setLong(1, productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Product productFromDb = new Product(
                        resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getDouble("price"));
                return Optional.of(productFromDb);
            }
            logger.info("User with index " + productId);
        } catch (SQLException e) {
            logger.error("User can't get by index from db", e);
        }
        return Optional.empty();
    }

    @Override
    public void deleteProduct(Long id) {
        try (Connection connection = DbConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            logger.info("Product with id " + id + " remove from db");
        } catch (SQLException e) {
            logger.error("Products can't remove from db", e);
        }
    }

    @Override
    public void update(Product product) {
        try (Connection connection = DbConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT);
            preparedStatement.setString(1, product.getTitle());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setLong(4, product.getId());
            preparedStatement.execute();
            logger.info("Product " + product + " was update");
        } catch (SQLException e) {
            logger.error("Product can't update", e);
        }
    }
}
