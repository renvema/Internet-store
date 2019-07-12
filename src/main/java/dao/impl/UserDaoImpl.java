package dao.impl;

import dao.UserDao;
import db.Storage;
import model.User;
import org.apache.log4j.Logger;

import java.util.List;

public class UserDaoImpl implements UserDao {

    private static final Logger logger = Logger.getLogger(UserDaoImpl.class);

    @Override
    public void addUser(User user) {
        Storage.USERS.add(user);
        logger.info("User " + user + "added in db");
    }

    @Override
    public List<User> getAll() {
        return Storage.USERS;
    }

    @Override
    public User getUsersById(Long userId) {
        for (User user : Storage.USERS) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void deleteUser(User user) {
        Storage.USERS.remove(user);
    }

    public User.ROLE getRoleByLoginPassword(String email, String password) {
        User.ROLE result = User.ROLE.UNKNOWN;

        for (User user : Storage.USERS) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                result = user.getRole();
            }
        }

        return result;
    }

    public boolean userIsExist(String email, String password) {

        boolean result = false;

        for (User user : Storage.USERS) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                result = true;
                break;
            }
        }

        return result;
    }
}
