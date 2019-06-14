package servlet;

import model.User;
import service.abstraction.DBService;
import service.implementation.DBServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Sign Up", value = "/signup")
public class SignUpServlet extends HttpServlet {
    private DBService dbService;

    public SignUpServlet() {
        this.dbService = new DBServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
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
        user.setRole("user");

        dbService.addUser(user);
        req.getSession().setAttribute("user", user);

        req.setAttribute("user", user);
        req.getServletContext().getRequestDispatcher("/user.jsp?id="+user.getId()).forward(req, resp);
    }
}
