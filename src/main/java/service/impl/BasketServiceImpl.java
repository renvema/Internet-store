package service.impl;

import dao.BasketDao;
import factory.BasketDaoFactory;
import model.Basket;
import model.Product;
import model.User;
import service.BasketService;

import java.util.Optional;

public class BasketServiceImpl implements BasketService {

    private static final BasketDao basketDao = BasketDaoFactory.getBasketDao();

    @Override
    public void addBasket(Basket basket) {
        basketDao.addBasket(basket);
    }

    @Override
    public void addProductToBasket(Basket basket, Product product) {
        basketDao.addProductToBasket(basket, product);
    }

    @Override
    public int size(Basket basket) {
        return basketDao.size(basket);
    }

    @Override
    public void clean(User user) {
        basketDao.clean(user);
    }

    @Override
    public Optional<Basket> getBasketByUser(User user) {
        return basketDao.getBasketByUser(user);
    }
}
