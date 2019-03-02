package main.controller;

import main.model.entities.DbResult;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import static main.utils.constants.Constants.MSG;

public abstract class BaseServlet extends HttpServlet {

    private ResourceBundle rb = ResourceBundle.getBundle("main/resources/text");

    protected void forward(String path, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }

    protected void showErrorPage(HttpServletRequest request, HttpServletResponse response, DbResult errorResult) throws ServletException, IOException {
        String msg;
        if (errorResult.isConnectionError()) {
            msg = getStringProperty("error.connection");
        } else if (errorResult.isSqlException()) {
            msg = getStringProperty("error.sql");
        } else if (errorResult.isAuthError()) {
            msg = getStringProperty("error.auth");
        } else {
            msg = getStringProperty("error.unknown");
        }
        request.setAttribute(MSG, msg);
        forward("/error_page.jsp", request, response);
    }

    protected String getStringProperty(String key) {
        if (key == null) {
            return null;
        }
        try {
            return rb.getString(key);
        } catch (MissingResourceException|ClassCastException e) {
            return null;
        }
    }
}
