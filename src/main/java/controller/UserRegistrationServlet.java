package controller;

import factory.UserServiceFactory;
import model.User;
import org.apache.log4j.Logger;
import service.UserService;
import utils.HashUtil;
import utils.IdGenerator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/add/user")
public class UserRegistrationServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger (UserRegistrationServlet.class);
    private static final UserService userService = UserServiceFactory.getInstance ( );

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher ("/add_user.jsp").forward (req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String email = req.getParameter ("email");
        String hashPassword = HashUtil.getSHA256SecurePassword (req.getParameter ("password"));
        String hashRepeatPassword = HashUtil.getSHA256SecurePassword (req.getParameter ("repeatPassword"));
        String role = req.getParameter ("role");

        if (email.isEmpty ( ) || email.isEmpty ( ) || hashPassword.isEmpty ( )) {
            req.setAttribute ("error", "Empty fields!");
            req.getRequestDispatcher ("/add_user.jsp").forward (req, resp);
        } else if (hashPassword.equals (hashRepeatPassword)) {
            User user = new User (IdGenerator.generateIdUser ( ), email, hashPassword, role);
            userService.addUser (user);
            resp.sendRedirect ("/admin/users");
        } else {
            req.setAttribute ("email", email);
            req.setAttribute ("error", "You passwords not equals");

            req.getRequestDispatcher ("/add_user.jsp").forward (req, resp);
            logger.info ("Passwords {" + hashPassword + "} and {" + hashRepeatPassword + "} not equals");
        }
    }
}
