package controller;

import factory.MailServiceFactory;
import model.Basket;
import model.Code;
import model.Order;
import model.User;
import service.MailService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

    private static final MailService mailService = MailServiceFactory.getMailService();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/order.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String surname = req.getParameter("surname");
        String name = req.getParameter("name");
        String city = req.getParameter("city");
        String adress = req.getParameter("adress");
        String phone = req.getParameter("phone");
        if (surname.isEmpty() || name.isEmpty() || city.isEmpty() || adress.isEmpty()
                || phone.isEmpty()) {
            req.setAttribute("valid", "The fields is valid");
            req.getRequestDispatcher("/order.jsp").forward(req, resp);
        } else {
            User user = (User) req.getSession().getAttribute("user");
            Code code = new Code(user);
            Basket basket = (Basket) req.getSession().getAttribute("basket");
            Order order = new Order(user, basket, code, name, surname, city, adress, phone);
            req.getSession().setAttribute("order", order);
            mailService.sendConfirmCode(order);
            resp.sendRedirect("/confirm");
        }
    }
}
