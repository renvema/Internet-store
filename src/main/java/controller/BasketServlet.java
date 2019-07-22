package controller;

import factory.ProductServiceFactory;
import model.Basket;
import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/basket")
public class BasketServlet extends HttpServlet {

    private static final ProductService productService = ProductServiceFactory.getInstance();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Basket basket = (Basket) req.getSession().getAttribute("basket");
        req.setAttribute("products", basket.getProducts());
        req.getRequestDispatcher("/basket.jsp").forward(req, resp);
    }
}
