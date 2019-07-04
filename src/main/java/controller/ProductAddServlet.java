package controller;

import model.Product;
import service.ProductService;
import service.impl.ProductServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/add")
public class ProductAddServlet extends HttpServlet {

    private static final ProductService productService = new ProductServiceImpl();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("product.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        Double price = Double.parseDouble(req.getParameter("price"));

        Product product = new Product(1L, title, description, price);
        productService.addProduct(product);
        resp.sendRedirect("/");
    }
}
