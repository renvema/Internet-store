package dao.impl;

import dao.OrderDao;
import model.Code;
import model.Order;
import model.User;
import org.apache.log4j.Logger;
import utils.DbConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class OrderMySQLDaoImpl implements OrderDao {

    private static final Logger logger = Logger.getLogger(OrderMySQLDaoImpl.class);
    private static final String ADD_ORDER = "INSERT INTO `order` (id_user, id_code, " +
            "name, surname, city, address, phone) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_ORDER = "SELECT `order`.id, `order`.id_user, id_code, code, " +
            "name, surname, city, address, phone " +
            "FROM `order` INNER JOIN code ON `order`.id_code = code.id " +
            "WHERE `order`.id_user = ? ORDER BY `order`.id DESC LIMIT 1";

    @Override
    public Optional<Order> getOrder(User user) {
        try (Connection connection = DbConnector.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER)) {
            preparedStatement.setLong(1, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Code code = new Code(
                        resultSet.getLong("id_code"),
                        resultSet.getString("code"),
                        user);
                Order order = new Order(
                        resultSet.getLong("id"),
                        user,
                        code,
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getString("city"),
                        resultSet.getString("address"),
                        resultSet.getString("phone"));
                return Optional.of(order);
            }
        } catch (SQLException e) {
            logger.error("Order can't get from database", e);
        }
        return Optional.empty();
    }

    @Override
    public void add(Order order) {
        try (Connection connection = DbConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_ORDER);
            preparedStatement.setLong(1, order.getUser().getId());
            preparedStatement.setLong(2, order.getCode().getId());
            preparedStatement.setString(3, order.getName());
            preparedStatement.setString(4, order.getSurname());
            preparedStatement.setString(5, order.getCity());
            preparedStatement.setString(6, order.getAddress());
            preparedStatement.setString(7, order.getPhone());
            preparedStatement.execute();
            logger.info("Order " + order + " added in db");
        } catch (SQLException e) {
            logger.error("Order can't added in db", e);
        }
    }
}
