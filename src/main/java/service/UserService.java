package service;

import model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void addUser(User user);

    List<User> getAll();

    Optional<User> getUsersById(Long userId);

    void deleteUser(Long id);

    Optional<User> findUserByEmail(String email);
}
