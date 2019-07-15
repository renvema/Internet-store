package dao.impl;

import dao.UserDao;
import db.Storage;
import model.User;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    private static final Logger logger = Logger.getLogger(UserDaoImpl.class);


    @Override
    public void addUser(User user) {
        Storage.users.add(user);
        logger.info("User " + user + "added in db");
    }

    @Override
    public List<User> getAll() {
        return Storage.users;
    }

    @Override
    public Optional<User> getUsersById(Long userId) {
        List<User> allUsers = getAll();
        return allUsers.stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst();
    }

    public void deleteUser(Long id) {
        Optional<User> optionalUser = getUsersById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Storage.users.remove(user);
            logger.info(user + " was deleted.");
        } else {
            logger.info("User not deleted");
        }
    }

    public Optional<User> findUserByEmail(String email) {
        return Storage.users.stream().filter(user -> user.getEmail().equals(email)).findFirst();
    }

    public boolean userIsExist(String email, String password) {

        boolean result = false;

        for (User user : Storage.users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                result = true;
                break;
            }
        }

        return result;
    }
}
