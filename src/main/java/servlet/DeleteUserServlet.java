package servlet;

import service.abstraction.DBService;
import dao.abstraction.UserDAOFactory;
import service.implementation.DBServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Delete user", value = "/delete")
public class DeleteUserServlet extends HttpServlet {
    private DBService dbService;

    public DeleteUserServlet() {
        this.dbService = new DBServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        long id = Long.valueOf(req.getParameter("id"));
        dbService.deleteUser(id);

        resp.sendRedirect("/admin");
    }
}
