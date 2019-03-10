package main.controller.companies.trade;

import main.controller.BaseServlet;
import main.controller.entities.TradeType;
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

@WebServlet(name = "TradesView", urlPatterns = "/trades/view")
public class TradesViewServlet extends BaseServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TradeType type = TradeType.valueOf(request.getParameter(TYPE));
        DbResult<List<Contract>> contracts = Database.getInstance().getAllContractsOfType(type);
        if (contracts.isAnyError()) {
            showErrorPage(request, response, contracts);
        } else {
            request.setAttribute("contracts", contracts.value());
            forward("/trades_view.jsp", request, response);
        }
    }
}
