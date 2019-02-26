package main.controller.individuals;

import main.controller.BaseServlet;
import main.model.data.Database;
import main.model.entities.DbResult;
import main.model.entities.Person;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static main.utils.constants.Constants.ALL_PERSONS;

@WebServlet(name = "Individuals", urlPatterns = "/individuals")
public class IndividualsServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DbResult<List<Person>> allPersons = Database.getInstance().getAllPersons();
        if (allPersons.isAnyError()) {
            // todo show error
        } else {
            req.setAttribute(ALL_PERSONS, allPersons.value());
            forward("/individuals.jsp", req, resp);
        }
    }
}
