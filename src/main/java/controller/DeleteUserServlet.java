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
import java.util.Optional;

@WebServlet("/admin/delete/user")
public class DeleteUserServlet extends HttpServlet {

    private static final UserService userService = UserServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        Long id = Long.valueOf(req.getParameter("id"));
        Optional<User> optUser = userService.getUsersById(id);
        optUser.ifPresent(userService::deleteUser);
        req.setAttribute("allUsers", userService.getAll());
        req.getRequestDispatcher("/admin/users").forward(req, resp);
    }
}
