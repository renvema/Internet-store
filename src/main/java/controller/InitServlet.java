package controller;

import factory.UserServiceFactory;
import model.User;
import service.UserService;
import utils.IdGenerator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(value = "/", loadOnStartup = 1)
public class InitServlet extends HttpServlet {

    private static final UserService userService = UserServiceFactory.getInstance();

    @Override
    public void init() throws ServletException {
        User test = new User(IdGenerator.generateIdUser(), "test@test.ua", "test");
        userService.addUser(test);
        User admin = new User(IdGenerator.generateIdUser(), "admin@admin.ua", "admin", "admin");
        userService.addUser(admin);
        User user = new User(IdGenerator.generateIdUser(), "user@user.ua", "user", "user");
        userService.addUser(user);
    }
}
