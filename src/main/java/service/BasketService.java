package service;

import model.User;

public interface BasketService {

    void addProductToBasket(Long idUser, Long productId);

    int size(Long id);

    void clean(User user);
}
