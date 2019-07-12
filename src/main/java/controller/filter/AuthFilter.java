package controller.filter;

import factory.UserServiceFactory;
import model.User;
import service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

@WebFilter(value = "/signin")
public class AuthFilter implements Filter {

    private static final UserService userService = UserServiceFactory.getInstance();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(final ServletRequest servletRequest,
                         final ServletResponse servletResponse,
                         final FilterChain filterChain)
            throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse resp = (HttpServletResponse) servletResponse;

        final String email = req.getParameter("email");
        final String password = req.getParameter("password");

        final HttpSession session = req.getSession();

        if (nonNull(session.getAttribute("email")) &&
                (nonNull(session.getAttribute("dao")))) {
            final User.ROLE role = (User.ROLE) session.getAttribute("role");
            moveToMenu(req, resp, role);
        } else if (userService.userIsExist(email, password)) {
            final User.ROLE role = userService.getRoleByLoginPassword(email, password);
            session.setAttribute("password", password);
            session.setAttribute("email", email);
            session.setAttribute("role", role);
            moveToMenu(req, resp, role);
        } else {
            moveToMenu(req, resp, User.ROLE.UNKNOWN);
        }
    }

    private void moveToMenu(final HttpServletRequest req,
                            final HttpServletResponse resp,
                            final User.ROLE role) throws IOException, ServletException {
        if (role.equals(User.ROLE.ADMIN)) {
            req.getRequestDispatcher("/users.jsp").forward(req, resp);
        } else if (role.equals(User.ROLE.USER)) {
            req.getRequestDispatcher("/products.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }

    @Override
    public void destroy() {

    }
}
