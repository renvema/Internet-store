package controller;

import factory.ProductServiceFactory;
import model.Product;
import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/edit/product")
public class EditProductServlet extends HttpServlet {
    private static final ProductService productService = ProductServiceFactory.getInstance();
    private Product product;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        product = productService.getProductsById(id);
        req.setAttribute("oldTitle", product.getTitle());
        req.setAttribute("oldDescription", product.getDescription());
        req.setAttribute("oldPrice", product.getPrice());

        req.getRequestDispatcher("/edit_product.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String title = req.getParameter("title");
            String description = req.getParameter("description");
            Double price = Double.parseDouble(req.getParameter("price"));
            product.setTitle(title);
            product.setDescription(description);
            product.setPrice(price);
            req.getRequestDispatcher("/products.jsp").forward(req, resp);
        } catch (NumberFormatException | NullPointerException ex) {
            req.setAttribute("valid", "something is wrong");
            req.getRequestDispatcher("/edit_product.jsp").forward(req, resp);
        }
    }
}
