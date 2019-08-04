package service.impl;

import dao.UserDao;
import factory.UserDaoFactory;
import model.User;
import service.UserService;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static final UserDao userDao = UserDaoFactory.getUserDao();

    public void addUser(User user) {
        userDao.addUser(user);
    }

    public List<User> getAll() {
        return userDao.getAll();
    }

    public Optional<User> getUsersById(Long userId) {
        return userDao.getUserById(userId);
    }

    public void deleteUser(User user) {
        userDao.deleteUser(user);
    }

    public Optional<User> findUserByEmail(String email) {
        return userDao.findUserByEmail(email);
    }

    @Override
    public void update(User user) {
       userDao.update (user);
    }
}
