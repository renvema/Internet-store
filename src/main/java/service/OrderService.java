package service;

import model.Order;
import model.User;

import java.util.Optional;

public interface OrderService {

    Optional<Order> getOrder(User user);

    void add(Order order);
}
