package service;

import model.User;

import java.util.List;

public interface UserService {

    void addUser(User user);

    List<User> getAll();

    User getUsersById(Long userId);

    void deleteUser(User user);

    User.ROLE getRoleByLoginPassword(String email, String password);

    boolean userIsExist(String email, String password);

}
