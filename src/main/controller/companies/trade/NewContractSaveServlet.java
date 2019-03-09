package main.controller.companies.trade;

import main.controller.BaseServlet;
import main.controller.entities.Trade;
import main.model.dao.Database;
import main.model.entities.Contract;
import main.model.entities.DbResult;
import main.utils.DateUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

import static main.utils.constants.Constants.*;
import static main.utils.StringUtils.isNullOrEmpty;
import static main.utils.NumberUtils.parseInt;

@WebServlet(name = "NewContractSave", urlPatterns = "/new/contract/save/")
public class NewContractSaveServlet extends BaseServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Date date = DateUtils.parseSqlDate(req.getParameter(DATE));
        String number = req.getParameter(NUMBER);
        Trade type = Trade.valueOf(req.getParameter(TYPE));
        int national = parseInt(req.getParameter(NATIONAL));
        int foreign = parseInt(req.getParameter(FOREIGN));

        if (DateUtils.isNullOrEmpty(date) || isNullOrEmpty(number) || type == null ||
                national <= ERROR || foreign <= ERROR) {
            showErrorPage(req, resp, getStringProperty("error.input"));
        } else {
            Contract c = new Contract(number, type, date, national, foreign);
            DbResult<Boolean> dbResult = Database.getInstance().saveContract(c);
            if (dbResult.isAnyError() || !dbResult.value()) {
                showErrorPage(req, resp, dbResult);
            } else {
                forward("/new/trade/", req, resp);
            }
        }
    }
}
