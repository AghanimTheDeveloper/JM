package servlet;

import model.User;
import service.abstraction.DBService;
import dao.abstraction.UserDAOFactory;
import service.implementation.DBServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Add user", value = "/add")
public class AddUserServlet extends HttpServlet {
    private DBService dbService;

    public AddUserServlet() {
        this.dbService = new DBServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (name.equals("") | login.equals("") | password.equals("")){
            resp.setContentType("text/html");
            resp.getWriter().println("Please fill in all fields");
        }

        User user = new User(name, login, password);

        dbService.addUser(user);


        resp.sendRedirect("/admin");
    }
}
