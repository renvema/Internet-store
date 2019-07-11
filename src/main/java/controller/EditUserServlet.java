package controller;

import factory.UserServiceFactory;
import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/edit/user")
public class EditUserServlet extends HttpServlet {

    private static final UserService userService = UserServiceFactory.getInstance();
    private User user;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        user = userService.getUsersById(id);
        req.setAttribute("oldEmail", user.getEmail());
        req.setAttribute("oldPassword", user.getPassword());
        req.getRequestDispatcher("/edit_user.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            user.setEmail(email);
            user.setPassword(password);
            req.setAttribute("allUser", userService.getAll());
            req.getRequestDispatcher("/users.jsp").forward(req, resp);
        } catch (NumberFormatException | NullPointerException ex) {
            req.setAttribute("valid", "something is wrong");
            req.getRequestDispatcher("/edit_user.jsp").forward(req, resp);
        }
    }
}
