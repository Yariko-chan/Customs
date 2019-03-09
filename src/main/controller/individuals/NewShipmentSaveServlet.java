package main.controller.individuals;

import main.controller.BaseServlet;
import main.controller.entities.SaveResult;
import main.model.dao.Database;
import main.model.entities.DbResult;
import main.model.entities.IndividualShipment;
import main.utils.DateUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

import static main.utils.StringUtils.isNullOrEmpty;
import static main.utils.DateUtils.parseSqlDate;
import static main.utils.NumberUtils.parseFloat;
import static main.utils.NumberUtils.parseInt;
import static main.utils.constants.Constants.*;

@WebServlet(name = "NewShipmentSaveServlet", urlPatterns = "/add/shipment/save/")
public class NewShipmentSaveServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SaveResult saveResult = new SaveResult();

        int personId = parseInt(req.getParameter("id"));
        Date date = parseSqlDate(req.getParameter("date"));
        String product = req.getParameter("product");
        float price = parseFloat(req.getParameter("price"));
        int quantity = parseInt(req.getParameter("quantity"));
        if (personId <= ERROR || DateUtils.isNullOrEmpty(date) || isNullOrEmpty(product) ||
                price <= ERROR || quantity <= ERROR) {
            saveResult.setInputError();
        } else {
            IndividualShipment shipment = new IndividualShipment(date, product, price, quantity, personId);
            DbResult<Boolean> dbResult = Database.getInstance().saveIndividualShipment(shipment);
            if (dbResult.isAnyError() || !dbResult.value()) {
                saveResult.setSavingError();
            }
        }

        req.setAttribute(SAVE_RESULT, saveResult);
        req.setAttribute(ID, personId);
        forward("/individuals_add_shipment_result.jsp", req, resp);
    }
}
