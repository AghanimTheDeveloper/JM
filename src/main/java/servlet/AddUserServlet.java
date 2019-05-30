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

@WebServlet(name = "Add user", value = "/adduser")
public class AddUserServlet extends HttpServlet {
    private final DBService dbService = new DBServiceJDBCImpl();

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

        dbService.addUser(user);

        req.setAttribute("user", user);
        req.getRequestDispatcher("/admin").forward(req, resp);
    }
}
