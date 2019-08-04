package service;

import model.Basket;
import model.Product;
import model.User;

import java.util.Optional;

public interface BasketService {

    void addBasket(Basket basket);

    void addProductToBasket(Basket basket, Product product);

    int size(Basket basket);

    void clean(User user);

    Optional<Basket> getBasketByUser(User user);
}
