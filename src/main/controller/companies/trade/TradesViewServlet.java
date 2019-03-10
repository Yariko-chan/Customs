package main.controller.companies.trade;

import javafx.util.Pair;
import main.controller.BaseServlet;
import main.controller.entities.FullTrade;
import main.controller.entities.TradeType;
import main.model.dao.Database;
import main.model.entities.DbResult;
import main.utils.DateUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import static main.utils.DateUtils.parseSqlDate;
import static main.utils.constants.Constants.*;
import static main.utils.constants.SdfConstants.INPUT_DATE_FORMAT;

@WebServlet(name = "TradesView", urlPatterns = "/trades/view")
public class TradesViewServlet extends BaseServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TradeType type = TradeType.valueOf(request.getParameter(TYPE));

        Date from = parseSqlDate(request.getParameter(FROM));
        Date to = parseSqlDate(request.getParameter(TO));
        if (from == null || to == null) {
            Pair<Date, Date> lastMonth = DateUtils.getLastMonthPeriod();
            from = lastMonth.getKey();
            to = lastMonth.getValue();
        }

        DbResult<List<FullTrade>> trades = Database.getInstance().getFullTradesInPeriod(type, from, to);
        if (trades.isAnyError()) {
            showErrorPage(request, response, trades);
        } else {
            request.setAttribute(TYPE, type);
            request.setAttribute(FROM, INPUT_DATE_FORMAT.format(from));
            request.setAttribute(TO, INPUT_DATE_FORMAT.format(to));
            request.setAttribute(TRADES, trades.value());
            forward("/trades_view.jsp", request, response);
        }
    }
}
