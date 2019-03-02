package main.controller;

import main.model.data.Database;
import main.model.entities.DbResult;
import main.model.entities.User;
import main.model.entities.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "login", urlPatterns = "/login")
public class LoginServlet extends BaseServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        DbResult<User> loginResult = Database.getInstance().login(login, password);

        if (loginResult.isAnyError()) {
            showErrorPage(request, response, loginResult);
        } else if (loginResult.value().getUserType() == UserType.ADMIN) {
            response.sendRedirect(request.getContextPath() + "/main.jsp");
        } else if (loginResult.value().getUserType() == UserType.USER) {
            response.sendRedirect(request.getContextPath() + "/main.jsp");
        }
    }
}
