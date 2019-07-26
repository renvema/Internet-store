package dao.impl;

import dao.UserDao;
import model.User;
import org.apache.log4j.Logger;
import utils.DbConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserMySQLDaoImpl implements UserDao {

    private static final Logger logger = Logger.getLogger(UserDaoImpl.class);

    @Override
    public void addUser(User user) {
        try (Connection connection = DbConnector.connect()) {
            String sql = String.format("INSERT INTO users (email, password, role)" +
                            "VALUES ('%s', '%s', '%s')",
                    user.getEmail(), user.getPassword(), user.getRole());
            Statement statement = connection.createStatement();
            statement.execute(sql);
            logger.info("User " + user + " added in db");
        } catch (SQLException e) {
            logger.error("User can't added in db", e);
        }
    }

    @Override
    public List<User> getAll() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = DbConnector.connect()) {
            Statement statement = connection.createStatement();
            String sql = String.format("SELECT * FROM users");
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User userFromDb = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("role"));
                userList.add(userFromDb);
            }
            logger.info("All users get from database");
        } catch (SQLException e) {
            logger.error("Users can't get from database", e);
        }
        return userList;
    }

    @Override
    public Optional<User> getUsersById(Long userId) {
        try (Connection connection = DbConnector.connect()) {
            String sql = String.format("SELECT * FROM users WHERE id=%d",
                    userId);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User userFromDb = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("role"));
                return Optional.of(userFromDb);
            }
            logger.info("User with index " + userId);

        } catch (SQLException e) {
            logger.error("User can't get by index from db", e);
        }
        return Optional.empty();
    }

    @Override
    public void deleteUser(Long id) {
        try (Connection connection = DbConnector.connect()) {
            String sql = String.format("DELETE FROM users WHERE id=%d",
                    id);
            Statement statement = connection.createStatement();
            statement.execute(sql);
            logger.info("User with id " + id + " remove from db");
        } catch (SQLException e) {
            logger.error("User can't remove from db", e);
        }
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        try (Connection connection = DbConnector.connect()) {
            String sql = String.format("SELECT * FROM users WHERE email='%s'",
                    email);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                User userFromDb = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("role"));
                return Optional.of(userFromDb);
            }
        } catch (SQLException e) {
            logger.error("User can't find in db", e);
        }
        return Optional.empty();
    }

    @Override
    public void update(User user) {
        try (Connection connection = DbConnector.connect()) {
            String sql = String.format("UPDATE users SET email='%s', password='%s', role='%s' WHERE id=%d",
                    user.getEmail(), user.getPassword(), user.getRole(), user.getId());
            Statement statement = connection.createStatement();
            statement.execute(sql);
            logger.info("User " + user + " was update");
        } catch (SQLException e) {
            logger.error("User can't update", e);
        }
    }
}
