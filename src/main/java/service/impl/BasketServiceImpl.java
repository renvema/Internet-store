package service.impl;

import dao.BasketDao;
import factory.BasketDaoFactory;
import model.User;
import service.BasketService;

public class BasketServiceImpl implements BasketService {

    private static final BasketDao basketDao = BasketDaoFactory.getBasketDao();

    public void addProductToBasket(Long idUser, Long productId) {
        basketDao.addProductToBasket(idUser, productId);
    }

    @Override
    public int size(Long id) {
        return basketDao.size(id);
    }

    @Override
    public void clean(User user) {
        basketDao.clean(user);
    }
}
