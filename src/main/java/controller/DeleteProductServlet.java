package controller;

import factory.ProductServiceFactory;
import service.ProductService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete/product")
public class DeleteProductServlet extends HttpServlet {

    private ProductService productService = ProductServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        if (id != null) {
            productService.deleteProduct(id);
        }
        resp.sendRedirect("/products");
    }
}
