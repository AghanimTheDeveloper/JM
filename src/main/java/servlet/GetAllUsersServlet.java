package servlet;

import service.abstraction.DBService;
import service.implementation.DBServiceHibernateImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "List users", value = "/admin")
public class GetAllUsersServlet extends HttpServlet {
    private final DBService dbService = new DBServiceHibernateImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        req.setAttribute("users", dbService.getAllUsers());
        req.getServletContext().getRequestDispatcher("/admin.jsp").forward(req, resp);
        resp.setCharacterEncoding("UTF-8");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
