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
import java.util.Optional;

@WebServlet("/admin/delete/product")
public class DeleteProductServlet extends HttpServlet {

    private static final ProductService productService = ProductServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        Long id = Long.valueOf(req.getParameter("id"));
        Optional<Product> optProduct = productService.getProductsById(id);
        optProduct.ifPresent(productService::deleteProduct);
        req.setAttribute("allProducts", productService.getAll());
        req.getRequestDispatcher("/products.jsp").forward(req, resp);
    }
}
