package controller;

import factory.CodeServiceFactory;
import factory.MailServiceFactory;
import factory.OrderServiceFactory;
import model.Code;
import model.Order;
import model.User;
import service.CodeService;
import service.MailService;
import service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

    private static final MailService mailService = MailServiceFactory.getMailService();
    private static final OrderService orderService = OrderServiceFactory.getInstance();
    private static final CodeService codeService = CodeServiceFactory.getCodeService();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/order.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String surname = req.getParameter("surname");
        String name = req.getParameter("name");
        String city = req.getParameter("city");
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");
        if (surname.isEmpty() || name.isEmpty() || city.isEmpty() || address.isEmpty()
                || phone.isEmpty()) {
            req.setAttribute("valid", "The fields is valid");
            req.getRequestDispatcher("/order.jsp").forward(req, resp);
        } else {
            User user = (User) req.getSession().getAttribute("user");
            codeService.add(new Code(user));
            Code code = null;
            Optional<Code> optionalCode = codeService.getCodeForUser(user);
            if (optionalCode.isPresent()) {
                code = optionalCode.get();
            }
            Order order = new Order(user, code, name, surname, city, address, phone);
            orderService.add(order);
            mailService.sendConfirmCode(order);
            resp.sendRedirect("/confirm");
        }
    }
}
