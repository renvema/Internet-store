package controller;

import factory.UserServiceFactory;
import model.Basket;
import model.Product;
import model.User;
import service.UserService;
import utils.SaltHashUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final UserService userService = UserServiceFactory.getInstance();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email = req.getParameter("email");
        Optional<User> optUser = userService.findUserByEmail(email);
        String hashPassword= SaltHashUtil.getSHA512SecurePassword(req.getParameter("password"),
                optUser.get().getSalt());
        if (optUser.isPresent() && optUser.get().getPassword().equals(hashPassword)) {
            HttpSession session = req.getSession();
            session.setAttribute("user", optUser.get());
            Basket basket = new Basket(
                    new ArrayList<Product>(), optUser.get());
            session.setAttribute("basket", basket);
            resp.sendRedirect("/admin/users");
        } else {
            resp.sendRedirect("index.jsp");
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.sendRedirect("/");
    }
}
