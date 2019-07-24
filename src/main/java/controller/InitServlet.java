package controller;

import factory.ProductServiceFactory;
import factory.UserServiceFactory;
import model.Product;
import model.User;
import service.ProductService;
import service.UserService;
import utils.HashUtil;
import utils.IdGenerator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(value = "/", loadOnStartup = 1)
public class InitServlet extends HttpServlet {

    private static final UserService userService = UserServiceFactory.getInstance();
    private static final ProductService productService = ProductServiceFactory.getInstance();

    @Override
    public void init() throws ServletException {
        String hashPasswordTest = HashUtil.getSHA256SecurePassword ("test");
        String hashPasswordAdmin = HashUtil.getSHA256SecurePassword ("admin");
        String hashPasswordUser = HashUtil.getSHA256SecurePassword ("user");
        User test = new User(IdGenerator.generateIdUser(), "test@test.ua", hashPasswordTest, "admin");
        userService.addUser(test);
        User admin = new User(IdGenerator.generateIdUser(), "admin@admin.ua", hashPasswordAdmin, "admin");
        userService.addUser(admin);
        User user = new User(IdGenerator.generateIdUser(), "user@user.ua", hashPasswordUser, "user");
        userService.addUser(user);
        Product bread = new Product(IdGenerator.generateIdProdut(), "bread", "white bread with garlic", 12.50);
        productService.addProduct(bread);
        Product cheese = new Product(IdGenerator.generateIdProdut(), "cheese", "parmesan", 85.90);
        productService.addProduct(cheese);
    }
}
