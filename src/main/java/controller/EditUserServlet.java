package controller;

import factory.UserServiceFactory;
import model.User;
import org.apache.log4j.Logger;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(value = "/admin/edit/user")
public class EditUserServlet extends HttpServlet {

    private static final UserService userService = UserServiceFactory.getInstance();
    private static final Logger logger = Logger.getLogger(EditUserServlet.class);

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        Optional<User> optUser = userService.getUsersById(id);
        if (optUser.isPresent()) {
            User user = optUser.get();
            req.setAttribute("userId", user.getId());
            req.setAttribute("oldEmail", user.getEmail());
            req.setAttribute("oldPassword", user.getPassword());
            req.getRequestDispatcher("/edit_user.jsp").forward(req, resp);
        } else {
            logger.info("User is not found");
            resp.sendRedirect("/admin/users");
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String role = req.getParameter("role");
        Long id = Long.valueOf(req.getParameter("id"));
        if (email.isEmpty() || password.isEmpty()) {
            req.setAttribute("error", "Empty fields!");
            req.setAttribute("oldEmail", email);
            req.setAttribute("oldPassword", password);
            req.getRequestDispatcher("/change_user.jsp").forward(req, resp);
        } else {
            User user = new User(id, email, password, role);
            userService.update(user);
            resp.sendRedirect("/admin/users");
        }
    }
}
