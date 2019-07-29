package dao.impl;

import dao.UserDao;
import model.User;
import org.apache.log4j.Logger;
import utils.DbConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserMySQLDaoImpl implements UserDao {

    private static final Logger logger = Logger.getLogger(UserDaoImpl.class);
    private static final String ADD_USER = "INSERT INTO users (email, password, role) VALUES (?, ?, ?)";
    private static final String GET_ALL_USER = "SELECT * FROM users";
    private static final String GET_USER_BY_ID = "SELECT * FROM users WHERE id=?";
    private static final String DELETE_USER = "DELETE FROM users WHERE id=?";
    private static final String FIND_USER_BY_EMAIL = "SELECT * FROM users WHERE email=?";
    private static final String UPDATE_USER = "UPDATE users SET email=?, password=?, role=? WHERE id=?";

    @Override
    public void addUser(User user) {
        try (Connection connection = DbConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole());
            preparedStatement.execute();
            logger.info("User " + user + " added in db");
        } catch (SQLException e) {
            logger.error("User can't added in db", e);
        }
    }

    @Override
    public List<User> getAll() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = DbConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_USER);
            ResultSet resultSet = preparedStatement.executeQuery();
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
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_ID);
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
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
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            logger.info("User with id " + id + " remove from db");
        } catch (SQLException e) {
            logger.error("User can't remove from db", e);
        }
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        try (Connection connection = DbConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_EMAIL);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
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
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole());
            preparedStatement.setLong(4, user.getId());
            preparedStatement.execute();
            logger.info("User " + user + " was update");
        } catch (SQLException e) {
            logger.error("User can't update", e);
        }
    }
}
