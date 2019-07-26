package service.impl;

import dao.OrderDao;
import factory.OrderDaoFactory;
import model.Order;
import model.User;
import service.OrderService;

import java.util.Optional;

public class OrderServiceImpl implements OrderService {

    private static final OrderDao orderDao = OrderDaoFactory.getInstance();

    @Override
    public Optional<Order> getOrder(User user) {
        return orderDao.getOrder(user);
    }

    @Override
    public void add(Order order) {
        orderDao.add(order);
    }
}
