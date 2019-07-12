package service.impl;

import dao.UserDao;
import factory.UserDaoFactory;
import model.User;
import service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private static final UserDao userDao = UserDaoFactory.getUserDao();

    public void addUser(User user) {
        userDao.addUser(user);
    }

    public List<User> getAll() {
        return userDao.getAll();
    }

    public User getUsersById(Long userId) {
        return userDao.getUsersById(userId);
    }

    public void deleteUser(User user) {
        userDao.deleteUser(user);
    }

    @Override
    public User.ROLE getRoleByLoginPassword(String email, String password) {
        return userDao.getRoleByLoginPassword(email, password);
    }

    @Override
    public boolean userIsExist(String email, String password) {
        return userDao.userIsExist(email, password);
    }
}
