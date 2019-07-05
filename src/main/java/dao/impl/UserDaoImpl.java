package dao.impl;

import dao.UserDao;
import db.Storage;
import model.User;

import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public void addUser(User user) {
        Storage.USERS.add(user);
    }

    @Override
    public List<User> getAll() {
        return Storage.USERS;
    }
}
