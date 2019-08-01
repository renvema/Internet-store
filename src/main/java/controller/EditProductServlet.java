package controller;

import factory.ProductServiceFactory;
import model.Product;
import org.apache.log4j.Logger;
import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(value = "/admin/edit/product")
public class EditProductServlet extends HttpServlet {

    private static final ProductService productService = ProductServiceFactory.getInstance();
    private static final Logger logger = Logger.getLogger(EditProductServlet.class);

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        Optional<Product> optProduct = productService.getProductsById(id);
        if (optProduct.isPresent()) {
            Product product = optProduct.get();
            req.setAttribute("productId", product.getId());
            req.setAttribute("oldTitle", product.getTitle());
            req.setAttribute("oldDescription", product.getDescription());
            req.setAttribute("oldPrice", product.getPrice());
            req.getRequestDispatcher("/edit_product.jsp").forward(req, resp);
        } else {
            logger.info("Product is not found");
            resp.sendRedirect("/products");
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String title = req.getParameter("title");
        String description = req.getParameter("description");
        Double price = Double.parseDouble(req.getParameter("price"));
        Long id = Long.valueOf(req.getParameter("id"));
//        Product product;
//        Optional<Product> optProduct = productService.getProductById(id);
//        if (optProduct.isPresent()) {
//            product = optProduct.get();
        if (title.isEmpty() || description.isEmpty()) {
            req.setAttribute("error", "Empty fields!");
            req.setAttribute("oldTitle", title);
            req.setAttribute("oldDescription", description);
            req.setAttribute("oldPrice", price);
            req.getRequestDispatcher("/change_user.jsp").forward(req, resp);
        } else {
            Product product = new Product(id, title, description, price);
            productService.update(product);
            resp.sendRedirect("/products");
        }
    }
}
