package controller;

import factory.ProductServiceFactory;
import factory.UserServiceFactory;
import model.Product;
import model.User;
import service.ProductService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(value = "/", loadOnStartup = 1)
public class InitServlet extends HttpServlet {

    private static final UserService userService = UserServiceFactory.getInstance();
    private static final ProductService productService = ProductServiceFactory.getInstance();

    @Override
    public void init() throws ServletException {

        User test = new User("test@test.ua", "test", "admin");
        userService.addUser(test);
        User admin = new User("admin@admin.ua","admin", "admin");
        userService.addUser(admin);
        User user = new User("user@user.ua", "user", "user");
        userService.addUser(user);
        Product bread = new Product("bread", "white bread with garlic", 12.50);
        productService.addProduct(bread);
        Product cheese = new Product( "cheese", "parmesan", 85.90);
        productService.addProduct(cheese);
    }
}
