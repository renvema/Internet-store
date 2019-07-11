package service;

import model.User;

import java.util.List;

public interface UserService {

    void addUser(User user);

    List<User> getAll();

    User getUsersById(Long userId);

    void deleteUser(Long userId);

}
