package controller;

import dao.UserDao;
import factory.UserDaoFactory;
import model.User;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/register")
public class UserRegistrationServlet extends HttpServlet {
    private UserDao userDao = UserDaoFactory.getUserDao();
    private User user;
    private Long id = 1L;

    private static final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String repeatPassword = req.getParameter("repeatPassword");
        if (password.equals(repeatPassword)) {
            User user = new User(1L, email, password);
            userService.addUser(user);
            id++;
            resp.sendRedirect("/users");
        } else {
            req.setAttribute("email", email);
            req.setAttribute("error", "You passwords not equals");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        }
    }
}
