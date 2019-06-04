package servlet;

import lombok.SneakyThrows;
import service.abstraction.DBService;
import service.implementation.DBServiceHibernateImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "List users", value = "/admin")
public class GetAllUsersServlet extends HttpServlet {
    private final DBService dbService = new DBServiceHibernateImpl();

    @Override
    @SneakyThrows
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        req.setCharacterEncoding("UTF-8");
        req.setAttribute("users", dbService.getAllUsers());
        req.getServletContext().getRequestDispatcher("/admin.jsp").forward(req, resp);
        resp.setCharacterEncoding("UTF-8");
    }

    @Override
    @SneakyThrows
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        doGet(req, resp);
    }
}
