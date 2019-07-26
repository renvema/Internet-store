package dao.impl;

import dao.CodeDao;
import model.Code;
import model.User;
import org.apache.log4j.Logger;
import utils.DbConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class CodeMysqlDaoImpl implements CodeDao {

    private static final Logger logger = Logger.getLogger(CodeMysqlDaoImpl.class);

    @Override
    public void add(Code code) {
        try (Connection connection = DbConnector.connect()) {
            String sql = String.format("INSERT INTO code(code, id_user)" +
                            "VALUES ('%s', '%s')",
                    code.getCode(), code.getUser().getId());
            Statement statement = connection.createStatement();
            statement.execute(sql);
            logger.info("Code " + code + " added in db");
        } catch (SQLException e) {
            logger.error("Code can't added in db", e);
        }
    }

    @Override
    public Optional<Code> getCode(User user) {
        try (Connection connection = DbConnector.connect()) {
            Statement statement = connection.createStatement();
            String sql = String.format("SELECT code.id, code, id_user, email, password,role " +
                    "FROM code INNER JOIN users on code.id_user = users.id" +
                    "WHERE id_user=%d ORDER BY code.id DESC LIMIT 1", user.getId());
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
                return Optional.of(code);
            }
        } catch (SQLException e) {
            logger.error("Code can't get from database", e);
        }
        return Optional.empty();
    }
}
