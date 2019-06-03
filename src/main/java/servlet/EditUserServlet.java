package servlet;

import model.User;
import service.abstraction.DBService;
import service.implementation.DBServiceHibernateImpl;
import service.implementation.DBServiceJDBCImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Edit user", value = "/edit")
public class EditUserServlet extends HttpServlet {
    private final DBService dbService = new DBServiceHibernateImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = dbService.getUserById(Long.valueOf(req.getParameter("id")));
        req.getServletContext().setAttribute("user", user);
        req.getRequestDispatcher("/edit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.valueOf(req.getParameter("id"));
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        User user = new User(id, name, login, password);

        dbService.editUser(user);

        resp.sendRedirect("/admin");
    }
}
