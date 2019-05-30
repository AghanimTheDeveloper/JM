package servlet;

import model.User;
import service.abstraction.DBService;
import service.implementation.DBServiceJDBCImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Edit user", value = "/edit")
public class EditUserServlet extends HttpServlet {
    private final DBService dbService = new DBServiceJDBCImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = dbService.getUserById(Long.parseLong(req.getParameter("id")));
        req.getServletContext().setAttribute("user", user);
        req.getRequestDispatcher("/edituser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (name.equals("") | login.equals("") | password.equals("")){
            resp.setContentType("text/html");
            resp.getWriter().println("Please fill in all fields");
        }

        User user = new User(name, login, password);

        dbService.editUser(user);

        req.getRequestDispatcher("/admin").forward(req, resp);
    }
}
