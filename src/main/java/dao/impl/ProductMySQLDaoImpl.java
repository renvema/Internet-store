package dao.impl;

import dao.ProductDao;
import model.Product;
import org.apache.log4j.Logger;
import utils.DbConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class ProductMySQLDaoImpl implements ProductDao {

    private static final Logger logger = Logger.getLogger(ProductMySQLDaoImpl.class);

    @Override
    public void addProduct(Product product) {
        try (Connection connection = DbConnector.connect()) {
            String sql = String.format(Locale.US,
                    "INSERT INTO products (title, description, price) VALUES ('%s', '%s', %01.2f)",
                    product.getTitle(), product.getDescription(), product.getPrice());
            Statement statement = connection.createStatement();
            statement.execute(sql);
            logger.info("Product " + product + "added in db");
        } catch (SQLException e) {
            logger.error("Products can't added", e);
        }
    }

    @Override
    public List<Product> getAll() {
        List<Product> productList = new ArrayList<>();
        try (Connection connection = DbConnector.connect()) {
            Statement statement = connection.createStatement();
            String sql = String.format("SELECT * FROM products");
            ResultSet resultSet = statement.executeQuery(sql);
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
            String sql = String.format("SELECT * FROM products WHERE id=%d",
                    productId);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
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
            String sql = String.format("DELETE FROM products WHERE id=%d", id);
            Statement statement = connection.createStatement();
            statement.execute(sql);
            logger.info("Product with id " + id + " remove from db");
        } catch (SQLException e) {
            logger.error("Products can't remove from db", e);
        }
    }

    @Override
    public void update(Product product) {
        try (Connection connection = DbConnector.connect()) {
            String sql = String.format(Locale.US,
                    "UPDATE products SET title='%s', description='%s', price=%01.2f WHERE id=%d",
                    product.getTitle(), product.getDescription(), product.getPrice(), product.getId());
            Statement statement = connection.createStatement();
            statement.execute(sql);
            logger.info("Product " + product + " was update");
        } catch (SQLException e) {
            logger.error("Product can't update", e);
        }
    }
}
