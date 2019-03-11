package main.controller.companies.trade;

import javafx.util.Pair;
import main.controller.BaseServlet;
import main.controller.entities.FullTrade;
import main.controller.entities.TradeType;
import main.model.dao.Database;
import main.model.entities.DbResult;
import main.model.entities.NationalCompany;
import main.utils.DateUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static main.utils.DateUtils.parseSqlDate;
import static main.utils.ListUtils.getSum;
import static main.utils.NumberUtils.parseInt;
import static main.utils.constants.Constants.*;
import static main.utils.constants.SdfConstants.INPUT_DATE_FORMAT;

@WebServlet(name = "NationalView", urlPatterns = "/view/national")
public class NationalViewServlet extends BaseServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = parseInt(request.getParameter(ID));

        DbResult<NationalCompany> company = Database.getInstance().getNational(id);

        if (company.isAnyError()) {
            showErrorPage(request, response, company);
        } else {
            request.setAttribute(COMPANY, company.value());

            Date from = parseSqlDate(request.getParameter(FROM));
            Date to = parseSqlDate(request.getParameter(TO));
            if (from == null || to == null) {
                Pair<Date, Date> lastMonth = DateUtils.getLastMonthPeriod();
                from = lastMonth.getKey();
                to = lastMonth.getValue();
            }
            request.setAttribute(FROM, INPUT_DATE_FORMAT.format(from));
            request.setAttribute(TO, INPUT_DATE_FORMAT.format(to));

            DbResult<List<FullTrade>> exports = Database.getInstance().getFullTradesForNationalInPeriod(id, TradeType.EXPORT, from, to);
            DbResult<List<FullTrade>> imports = Database.getInstance().getFullTradesForNationalInPeriod(id, TradeType.IMPORT, from, to);
            if (exports.isAnyError()) {
                request.setAttribute(EXPORT, new ArrayList<FullTrade>());
                request.setAttribute(EXPORT_SUM, 0f);
            } else {
                request.setAttribute(EXPORT, exports.value());
                request.setAttribute(EXPORT_SUM, getSum(exports.value()));

            }
            if (imports.isAnyError()) {
                request.setAttribute(IMPORT, new ArrayList<FullTrade>());
                request.setAttribute(IMPORT_SUM, 0f);
            } else {
                request.setAttribute(IMPORT, imports.value());
                request.setAttribute(IMPORT_SUM, getSum(imports.value()));
            }
            forward("/national_view.jsp", request, response);
        }
    }
}
