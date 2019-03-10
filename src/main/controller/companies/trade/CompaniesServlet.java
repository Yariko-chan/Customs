package main.controller.companies.trade;

import main.controller.BaseServlet;
import main.controller.entities.FullTrade;
import main.controller.entities.TradeType;
import main.model.dao.Database;
import main.model.entities.DbResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static main.utils.constants.Constants.EXPORT;
import static main.utils.constants.Constants.IMPORT;

@WebServlet(name = "Companies", urlPatterns = "/companies")
public class CompaniesServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DbResult<List<FullTrade>> exportTrades = Database.getInstance().getTopFullTradesOfType(TradeType.EXPORT, 20);
        DbResult<List<FullTrade>> importTrades = Database.getInstance().getTopFullTradesOfType(TradeType.IMPORT, 20);

        if (exportTrades.isAnyError()) {
            showErrorPage(req, resp, exportTrades);
        } else if (importTrades.isAnyError()) {
            showErrorPage(req, resp, importTrades);
        } else {
            req.setAttribute(EXPORT, exportTrades.value());
            req.setAttribute(IMPORT, importTrades.value());
            forward("/companies.jsp", req, resp);
        }
    }
}
