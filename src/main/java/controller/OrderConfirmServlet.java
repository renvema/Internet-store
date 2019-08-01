package controller;

import factory.OrderServiceFactory;
import model.Order;
import model.User;
import service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/confirm")
public class OrderConfirmServlet extends HttpServlet {

    OrderService orderService = OrderServiceFactory.getInstance();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String enteredCode = req.getParameter("code");
        User user = (User) req.getSession().getAttribute("user");
        Optional<Order> optOrder = orderService.getOrder(user);
        if (optOrder.isPresent()) {
            Order order = optOrder.get();
                if (enteredCode.equals(order.getCode().getCode())) {
                    req.setAttribute("message", "Your buying is successful.");
                } else {
                    req.setAttribute("message", "The code is wrong. Try again");
                }
                req.getRequestDispatcher("/confirm.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/confirm.jsp").forward(req, resp);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/confirm.jsp").forward(req, resp);
    }
}
