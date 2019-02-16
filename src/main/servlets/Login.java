package main.servlets;

import main.data.Database;
import main.models.DbResult;
import main.models.User;
import main.models.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "login", urlPatterns = {"/login"})
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        DbResult<User> loginResult = Database.getInstance().login(login, password);

        if (loginResult.isAnyError()) {

        } else if (loginResult.value().getUserType() == UserType.ADMIN) {
            response.sendRedirect(request.getContextPath() + "/main.jsp");
        } else if (loginResult.value().getUserType() == UserType.USER) {
            response.sendRedirect(request.getContextPath() + "/main.jsp");
        }
    }
}
