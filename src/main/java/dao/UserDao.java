package dao;

import model.User;

import java.util.List;

public interface UserDao {

    void addUser(User user);

    List<User> getAll();

    User getUsersById(Long userId);

    void deleteUser(User user);

}
