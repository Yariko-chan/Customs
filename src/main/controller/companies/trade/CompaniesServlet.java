package main.controller.companies.trade;

import main.controller.BaseServlet;
import main.controller.entities.FullTrade;
import main.controller.entities.TradeType;
import main.model.dao.Database;
import main.model.entities.Contract;
import main.model.entities.DbResult;
import main.model.entities.Trade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.ArrayList;
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
        DbResult<List<Trade>> exportTrades = Database.getInstance().getTopTrades(TradeType.EXPORT, 20);
        DbResult<List<Trade>> importTrades = Database.getInstance().getTopTrades(TradeType.IMPORT, 20);
        DbResult<List<Contract>> contracts = Database.getInstance().getAllContracts();

        if (exportTrades.isAnyError()) {
            showErrorPage(req, resp, exportTrades);
        } else if (importTrades.isAnyError()) {
            showErrorPage(req, resp, importTrades);
        } else if (contracts.isAnyError()) {
            showErrorPage(req, resp, contracts);
        } else {

            List<FullTrade> exports = merge(exportTrades.value(), contracts.value());
            List<FullTrade> imports = merge(importTrades.value(), contracts.value());

            req.setAttribute(EXPORT, exports);
            req.setAttribute(IMPORT, imports);
            forward("/companies.jsp", req, resp);
        }
    }

    private List<FullTrade> merge(List<Trade> trades, List<Contract> contracts) {
        List<FullTrade> result = new ArrayList<>();
        for (Trade t : trades) {
            result.add(new FullTrade(t, contracts.get(contracts.indexOf(new Contract(t.getContractId())))));
        }
        return result;
    }
}
