package dao.impl;

import dao.UserDao;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private List<User> userList = new ArrayList<>();

    @Override
    public void addUser(User user) {
        userList.add(user);
    }

    @Override
    public List<User> getAll() {
        return userList;
    }
}
