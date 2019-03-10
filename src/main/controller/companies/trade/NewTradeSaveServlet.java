package main.controller.companies.trade;

import main.controller.BaseServlet;
import main.model.dao.Database;
import main.model.entities.DbResult;
import main.model.entities.Trade;
import main.utils.DateUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

import static main.utils.NumberUtils.parseFloat;
import static main.utils.NumberUtils.parseInt;
import static main.utils.StringUtils.isNullOrEmpty;
import static main.utils.constants.Constants.*;

@WebServlet(name = "NewTradeSave", urlPatterns = "/new/trade/save/")
public class NewTradeSaveServlet extends BaseServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Date date = DateUtils.parseSqlDate(req.getParameter(DATE));
        String product = req.getParameter(PRODUCT);
        float price = parseFloat(req.getParameter(PRICE));
        int quantity = parseInt(req.getParameter(QUANTITY));
        int contractId = parseInt(req.getParameter(CONTRACT));

        if (DateUtils.isNullOrEmpty(date) || isNullOrEmpty(product) ||
                price <= ERROR || quantity <= ERROR || contractId <= ERROR ) {
            showErrorPage(req, resp, getStringProperty("error.input"));
        } else {
            Trade t = new Trade(date, product, price, quantity, contractId);
            DbResult<Boolean> dbResult = Database.getInstance().saveTrade(t);
            if (dbResult.isAnyError() || !dbResult.value()) {
                showErrorPage(req, resp, dbResult);
            } else {
                forward("/companies", req, resp);
            }
        }
    }
}
