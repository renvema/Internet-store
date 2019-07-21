package service.impl;

import dao.BasketDao;
import factory.BasketDaoFactory;
import model.Product;
import service.BasketService;

public class BasketServiceImpl implements BasketService {

    private static final BasketDao basketDao = BasketDaoFactory.getBasketDao();

    public void addProductToBasket(Product product) {
        basketDao.addProductToBasket(product);
    }
}
