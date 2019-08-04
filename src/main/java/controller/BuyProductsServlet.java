package controller;

import factory.BasketServiceFactory;
import factory.ProductServiceFactory;
import model.Basket;
import model.Product;
import model.User;
import service.BasketService;
import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet("/buy/product")
public class BuyProductsServlet extends HttpServlet {

    private static final ProductService productService = ProductServiceFactory.getInstance();
    private static final BasketService basketService = BasketServiceFactory.getInstance();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Long id = Long.valueOf(req.getParameter("id"));
        User user = (User) req.getSession().getAttribute("user");
        Optional<Product> optProduct = productService.getProductsById(id);
        if (optProduct.isPresent()) {
            Optional<Basket> optBasket = basketService.getBasketByUser(user);
            Basket basket;
            Product product = optProduct.get();
            if (optBasket.isPresent()) {
                basket = optBasket.get();
                basketService.addProductToBasket(basket, product);
            } else {
                List<Product> products = new ArrayList<>();
                products.add(product);
                basket = new Basket(products,user);
                basketService.addBasket(basket);
                req.setAttribute("message", "Product add in your basket");
            }
            HttpSession session = req.getSession();
            session.setAttribute("basket", basket);
            req.setAttribute("size", basketService.size(basket));
            req.setAttribute("products", productService.getAll());
            req.getRequestDispatcher("/products_user.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/buy/product");
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("products", productService.getAll());
        req.getRequestDispatcher("/products_user.jsp").forward(req, resp);
    }
}
