package dao;

import model.Order;
import model.User;

import java.util.Optional;

public interface OrderDao {

    Optional<Order> getOrder(User user);

    void add(Order order);
}
