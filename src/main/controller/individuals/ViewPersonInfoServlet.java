package main.controller.individuals;

import main.controller.BaseServlet;
import main.model.data.Database;
import main.model.entities.DbResult;
import main.model.entities.Person;
import main.utils.constants.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ViewPersonInfo", urlPatterns = "/view/person/")
public class ViewPersonInfoServlet extends BaseServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.valueOf(request.getParameter(Constants.ID));
        DbResult<Person> person = Database.getInstance().getPerson(id);
        if (person.isAnyError()) {
            // todo show error
        } else {
            request.setAttribute(Constants.PERSON, person.value());
            forward("/individuals_view.jsp", request, response);
        }

    }
}
