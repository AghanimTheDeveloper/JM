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

@WebServlet(name = "Sign In", value = "/signin")
public class SignInServlet extends HttpServlet {
    private DBService dbService;

    public SignInServlet() {
        this.dbService = new DBServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user;

        if (login.equals("") | password.equals("")){
            resp.setContentType("text/html");
            resp.getWriter().println("Please fill in all fields");
        }

        if (dbService.getUserByLogin(login).getPassword().equals(password)){
            user = dbService.getUserByLogin(login);
            if (user.getRole().equals("admin")){
                resp.sendRedirect("/admin");
            }else{
                req.setAttribute("user", user);
                req.getServletContext().getRequestDispatcher("/user.jsp?id="+user.getId()).forward(req, resp);
            }
        }else{
            resp.sendRedirect("/index");
        }
    }
}
