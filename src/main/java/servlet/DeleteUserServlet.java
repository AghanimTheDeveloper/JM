package servlet;

import lombok.SneakyThrows;
import service.abstraction.DBService;
import service.implementation.DBServiceHibernateImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Delete user", value = "/delete")
public class DeleteUserServlet extends HttpServlet {
    private final DBService dbService = new DBServiceHibernateImpl();

    @Override
    @SneakyThrows
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        req.setCharacterEncoding("UTF-8");
        long id = Long.valueOf(req.getParameter("id"));
        dbService.deleteUser(id);

        resp.sendRedirect("/admin");
    }
}
