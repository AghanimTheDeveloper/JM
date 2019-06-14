package filter;

import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/admin")
public class AdminFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String adminUri = req.getContextPath() + "/admin";

        boolean loggedIn = session != null && session.getAttribute("user") != null;
        boolean adminRequest = req.getRequestURI().contains(adminUri);

        if (loggedIn) {

            if (adminRequest) {
                if (user.getRole().equals("admin")){
                    filterChain.doFilter(req, resp);
                } else {
                    resp.sendRedirect("/user.jsp?id="+user.getId());
                }
            }
        } else {
            resp.sendRedirect("/index");
        }
    }
}
