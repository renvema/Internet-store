package dao;

import model.User;

public interface BasketDao {

    void addProductToBasket(Long idUser, Long productId);

    int size(Long id);

    void clean(User user);
}
