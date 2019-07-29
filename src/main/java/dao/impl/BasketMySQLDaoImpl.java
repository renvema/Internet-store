package dao.impl;

import dao.BasketDao;
import model.User;
import org.apache.log4j.Logger;
import utils.DbConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BasketMySQLDaoImpl implements BasketDao {

    private static final Logger logger = Logger.getLogger(BasketMySQLDaoImpl.class);
    private static final String ADD_PRODUCT_IN_BASKET = "INSERT INTO basket (id_user,id_product) VALUES (?, ?)";
    private static final String SIZE_BASKET = "SELECT COUNT(*) FROM basket WHERE id_user=?";
    private static final String CLEAN_BASKET = "DELETE FROM basket WHERE id_user=?";

    @Override
    public void addProductToBasket(Long idUser, Long productId) {
        try (Connection connection = DbConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_PRODUCT_IN_BASKET);
            preparedStatement.setLong(1, idUser);
            preparedStatement.setLong(2, productId);
            preparedStatement.execute();
            logger.info("Product added in basket");
        } catch (SQLException e) {
            logger.error("Product can't added in basket", e);
        }
    }

    @Override
    public int size(Long id) {
        int count = 0;
        try (Connection connection = DbConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SIZE_BASKET);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.error("Can't show size backets", e);
        }
        return count;
    }

    @Override
    public void clean(User user) {
        try (Connection connection = DbConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CLEAN_BASKET);
            preparedStatement.setLong(1, user.getId());
            preparedStatement.execute();
            logger.info("Basket was cleaned");
        } catch (SQLException e) {
            logger.error("Basket wasn't cleaned", e);
        }
    }
}
