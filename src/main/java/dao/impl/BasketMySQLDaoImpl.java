package dao.impl;

import dao.BasketDao;
import model.User;
import org.apache.log4j.Logger;
import utils.DbConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BasketMySQLDaoImpl implements BasketDao {

    private static final Logger logger = Logger.getLogger(BasketMySQLDaoImpl.class);

    @Override
    public void addProductToBasket(Long idUser, Long productId) {
        try (Connection connection = DbConnector.connect()) {
            String sql = String.format("INSERT INTO basket (id_user, " +
                            "id_product) VALUES (%d, %d)",
                    idUser, productId);
            Statement statement = connection.createStatement();
            statement.execute(sql);
            logger.info("Product added in basket");
        } catch (SQLException e) {
            logger.error("Product can't added in basket", e);
        }
    }

    @Override
    public int size(Long id) {
        int count = 0;
        try (Connection connection = DbConnector.connect()) {
            String sql = String.format("SELECT COUNT(*) FROM basket WHERE id_user=%d",
                    id);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
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
            String sql = String.format("DELETE FROM basket where id_user=%d",
                    user.getId());
            Statement statement = connection.createStatement();
            statement.execute(sql);
            logger.info("Basket was cleaned");
        } catch (SQLException e) {
            logger.error("Basket wasn't cleaned", e);
        }
    }
}
