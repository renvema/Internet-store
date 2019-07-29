package dao.impl;

import dao.CodeDao;
import model.Code;
import model.User;
import org.apache.log4j.Logger;
import utils.DbConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class CodeMysqlDaoImpl implements CodeDao {

    private static final Logger logger = Logger.getLogger(CodeMysqlDaoImpl.class);
    private static final String ADD_CODE = "INSERT INTO code(code, id_user) VALUES (?, ?) ";
    private static final String GET_CODE_FOR_USER = "SELECT * FROM code " +
            " WHERE id_user = ? ORDER BY id DESC LIMIT 1";

    @Override
    public void add(Code code) {
        try (Connection connection = DbConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_CODE);
            preparedStatement.setString(1, code.getCode());
            preparedStatement.setLong(2, code.getUser().getId());
            preparedStatement.execute();
            logger.info("Code " + code + " added in db");
        } catch (SQLException e) {
            logger.error("Code can't added in db", e);
        }
    }

    @Override
    public Optional<Code> getCodeForUser(User user) {
        try (Connection connection = DbConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_CODE_FOR_USER);
            preparedStatement.setLong(1, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Code code = new Code(
                        resultSet.getLong("id"),
                        resultSet.getString("code"),
                        user);
                return Optional.of(code);
            }
        } catch (SQLException e) {
            logger.error("Code can't get from database", e);
        }
        return Optional.empty();
    }
}
