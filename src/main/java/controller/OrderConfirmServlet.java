package controller;

import model.Order;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/confirm")
public class OrderConfirmServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String enteredCode = req.getParameter("code");
        User user = (User) req.getSession().getAttribute("user");
        Order order = (Order) req.getSession().getAttribute("order");

        if (user == order.getUser()) {
            if (enteredCode.equals(order.getCode().getCode())) {
                req.setAttribute("message", "Your buying is successful.");
            } else {
                req.setAttribute("message", "The code is wrong. Try again");
            }
            req.getRequestDispatcher("/confirm.jsp").forward(req, resp);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/confirm.jsp").forward(req, resp);
    }
}
