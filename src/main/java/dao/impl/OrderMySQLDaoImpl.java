package dao.impl;

import dao.OrderDao;
import model.Code;
import model.Order;
import model.User;
import org.apache.log4j.Logger;
import utils.DbConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class OrderMySQLDaoImpl implements OrderDao {

    private static final Logger logger = Logger.getLogger(OrderMySQLDaoImpl.class);

    @Override
    public Optional<Order> getOrder(User user) {
        try (Connection connection = DbConnector.connect()) {
            Statement statement = connection.createStatement();
            String sql = String.format("SELECT `order`.id, id_user, id_code, id_basket, name, surname, city, adress, phone, email, password, role, code" +
                    "FROM `order` INNER JOIN users on `order`.id_user = users.id" +
                    "INNER JOIN basket on `order`.id_basket = basket.id" +
                    "INNER JOIN code on `order`.id_code = code.id" +
                    "where `order`.id_user=users.id", user.getId());
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                User userFromDB = new User(
                        resultSet.getLong("id_user"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("role"));
                Code code = new Code(
                        resultSet.getLong("id"),
                        resultSet.getString("code"),
                        userFromDB);
                Order order = new Order(
                        resultSet.getLong("id"),
                        userFromDB,
                        code,
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getString("city"),
                        resultSet.getString("adress"),
                        resultSet.getString("phone"));
                return Optional.of(order);
            }
        } catch (SQLException e) {
            logger.error("Orde can't get from database", e);
        }
        return Optional.empty();
    }

    @Override
    public void add(Order order) {
        try (Connection connection = DbConnector.connect()) {
            String sql = String.format("INSERT INTO users (id_user, id_code, id_basket, name, " +
                            "surname, city, adress, phone) " +
                            "VALUES (%d, %d, %d, '%s', '%s', '%s', '%s', '%s')",
                    order.getUser().getId(), order.getCode().getId(),
                    order.getBasket().getIdBasket(), order.getName(), order.getSurname(),
                    order.getCity(), order.getAdress(), order.getPhone());
            Statement statement = connection.createStatement();
            statement.execute(sql);
            logger.info("Order " + order + " added in db");
        } catch (SQLException e) {
            logger.error("Order can't added in db", e);
        }
    }
}
