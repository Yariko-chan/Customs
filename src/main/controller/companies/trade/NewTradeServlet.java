package main.controller.companies.trade;

import main.controller.BaseServlet;
import main.controller.entities.Trade;
import main.model.dao.Database;
import main.model.entities.Contract;
import main.model.entities.DbResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static main.utils.constants.Constants.TYPE;

@WebServlet(name = "NewTrade", urlPatterns = "/new/trade/")
public class NewTradeServlet extends BaseServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // todo retrieve export/import and load contracts
        Trade type = Trade.valueOf(request.getParameter(TYPE));
        DbResult<List<Contract>> contracts = Database.getInstance().getAllContractsOfType(type);
        if (contracts.isAnyError()) {
            showErrorPage(request, response, contracts);
        } else {
            request.setAttribute("contracts", contracts.value());
            forward("/trade_new.jsp", request, response);
        }
    }
}
