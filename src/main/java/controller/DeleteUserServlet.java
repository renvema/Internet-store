package controller;

import factory.UserServiceFactory;
import service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete/user")
public class DeleteUserServlet extends HttpServlet {

    private UserService userService = UserServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        if (id != null) {
            userService.deleteUser(id);
        }
        resp.sendRedirect("/users");
    }
}
