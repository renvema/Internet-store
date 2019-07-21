package service;

import model.Order;

public interface MailService {

    void sendConfirmCode(Order order);
}
