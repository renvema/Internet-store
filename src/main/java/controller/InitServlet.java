package controller;

import factory.UserServiceFactory;
import model.User;
import service.UserService;
import utils.IdGenerator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/", loadOnStartup = 1)
public class InitServlet extends HttpServlet {

    private static final UserService userService = UserServiceFactory.getInstance();

    @Override
    public void init() throws ServletException {
        User test = new User(IdGenerator.generateIdUser(), "test@test.ua", "test");
        userService.addUser(test);
        User admin = new User(IdGenerator.generateIdUser(), "admin@admin.ua", "admin", User.ROLE.ADMIN);
        userService.addUser(admin);
        User user = new User(IdGenerator.generateIdUser(), "user@user.ua", "user", User.ROLE.USER);
        userService.addUser(user);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

    }
}
