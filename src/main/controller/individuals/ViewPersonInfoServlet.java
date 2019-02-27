package main.controller.individuals;

import main.controller.BaseServlet;
import main.model.data.Database;
import main.model.entities.DbResult;
import main.model.entities.IndividualShipment;
import main.model.entities.Person;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static main.utils.constants.Constants.ID;
import static main.utils.constants.Constants.PERSON;
import static main.utils.constants.Constants.SHIPMENTS;

@WebServlet(name = "ViewPersonInfo", urlPatterns = "/view/person/")
public class ViewPersonInfoServlet extends BaseServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.valueOf(request.getParameter(ID));
        DbResult<Person> person = Database.getInstance().getPerson(id);
        DbResult<List<IndividualShipment>> personShipments = Database.getInstance().getShipmentsByPersonId(id);
        if (person.isAnyError()) {
            // todo show error
        } else {
            request.setAttribute(PERSON, person.value());
            if (!personShipments.isAnyError()) {
                request.setAttribute(SHIPMENTS, personShipments.value());
            }
            forward("/individuals_view.jsp", request, response);
        }

    }
}
