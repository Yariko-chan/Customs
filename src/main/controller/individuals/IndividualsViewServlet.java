package main.controller.individuals;

import javafx.util.Pair;
import main.controller.BaseServlet;
import main.model.dao.Database;
import main.model.entities.DbResult;
import main.model.entities.IndividualShipment;
import main.model.entities.Person;
import main.utils.DateUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import static main.utils.DateUtils.parseSqlDate;
import static main.utils.NumberUtils.parseInt;
import static main.utils.constants.Constants.*;
import static main.utils.constants.Constants.TO;
import static main.utils.constants.SdfConstants.INPUT_DATE_FORMAT;

@WebServlet(name = "IndividualsView", urlPatterns = "/individuals_view")
public class IndividualsViewServlet extends BaseServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = parseInt(request.getParameter(ID));
        DbResult<Person> person = Database.getInstance().getPerson(id);
        if (person.isAnyError()) {
            showErrorPage(request, response, person);
        } else {
            request.setAttribute(PERSON, person.value());
            Date from = parseSqlDate(request.getParameter(FROM));
            Date to = parseSqlDate(request.getParameter(TO));
            if (from == null || to == null) {
                Pair<Date, Date> lastMonth = DateUtils.getLastMonthPeriod();
                from = lastMonth.getKey();
                to = lastMonth.getValue();
            }
            DbResult<List<IndividualShipment>> personShipments = Database.getInstance().getShipmentsInPeriod(id, from, to);
            if (!personShipments.isAnyError()) {
                request.setAttribute(SHIPMENTS, personShipments.value());
                request.setAttribute(FROM, INPUT_DATE_FORMAT.format(from));
                request.setAttribute(TO, INPUT_DATE_FORMAT.format(to));
            }
            forward("/individuals_view.jsp", request, response);
        }
    }
}
