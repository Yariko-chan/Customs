package main.controller.companies;

import main.controller.BaseServlet;
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

import static main.utils.StringUtils.isNullOrEmpty;
import static main.utils.StringUtils.isValidUnp;

@WebServlet(name = "NewNationalSaveServlet", urlPatterns = "/new/national/save/")
public class NewNationalSaveServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        String name = request.getParameter("name");
        String unp = request.getParameter("unp");
        String licenseNo = request.getParameter("license");
        Date validDate = DateUtils.parseSqlDate(request.getParameter("valid_date"));
        if (isNullOrEmpty(name) || isNullOrEmpty(unp) || !isValidUnp(unp) ||
                isNullOrEmpty(licenseNo) || DateUtils.isNullOrEmpty(validDate)) {
            showErrorPage(request, resp, getStringProperty("error.input"));
        } else {
            NationalCompany nc = new NationalCompany(name, unp, licenseNo, validDate);
            DbResult<Boolean> dbResult = Database.getInstance().saveNationalCompany(nc);
            if (dbResult.isAnyError() || !dbResult.value()) {
                showErrorPage(request, resp, dbResult);
            } else {
                forward("/companies/list/", request, resp);
            }
        }
    }
}
