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

@WebServlet("/delete/product")
public class DeleteProductServlet extends HttpServlet {

    private ProductService productService = ProductServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        Long id = Long.valueOf(req.getParameter("id"));
        Product product = productService.getProductsById(id);
        productService.deleteProduct(product);
        req.setAttribute("allProduct", productService.getAll());
        req.getRequestDispatcher("/products.jsp").forward(req, resp);
    }
}
