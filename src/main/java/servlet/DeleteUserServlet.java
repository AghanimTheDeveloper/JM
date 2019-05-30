package servlet;

import service.abstraction.DBService;
import service.implementation.DBServiceJDBCImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Delete user", value = "/delete")
public class DeleteUserServlet extends HttpServlet {
    private final DBService dbService = new DBServiceJDBCImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dbService.deleteUser(Long.parseLong(req.getParameter("id")));
        req.getRequestDispatcher("/admin").forward(req, resp);
    }
}
