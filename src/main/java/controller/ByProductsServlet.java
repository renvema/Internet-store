package controller;

import db.Storage;
import factory.ProductServiceFactory;
import model.Basket;
import model.Product;
import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/buy/product")
public class ByProductsServlet extends HttpServlet {

    private static final ProductService productService = ProductServiceFactory.getInstance();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Long id = Long.valueOf(req.getParameter("id"));

        Basket basket = (Basket) req.getSession().getAttribute("basket");
        Optional<Product> OptProduct = productService.getProductsById(id);
        if (OptProduct.isPresent()) {
            basket.addProductInBasket(OptProduct.get());
            req.setAttribute("message", "Product add in your basket");
            HttpSession session = req.getSession();
            session.setAttribute("basket", basket);
            req.setAttribute("size", basket.getSize());
            req.setAttribute("products", Storage.products);
            req.getRequestDispatcher("/products_user.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("index.jsp");
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("products", Storage.products);
        req.getRequestDispatcher("/products_user.jsp").forward(req, resp);
    }
}
