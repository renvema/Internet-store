package controller;

import dao.UserDao;
import factory.UserDaoFactory;
import factory.UserServiceFactory;
import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/register")
public class UserRegistrationServlet extends HttpServlet {

    private Long id = 1L;

    private static final UserService userService = UserServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String repeatPassword = req.getParameter("repeatPassword");

        if (email.isEmpty() || email.isEmpty() || password.isEmpty()) {
            req.setAttribute("error", "Empty fields!");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        } else if (password.equals(repeatPassword)) {
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
