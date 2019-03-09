package main.controller.companies;

import main.controller.BaseServlet;
import main.model.dao.Database;
import main.model.entities.DbResult;
import main.model.entities.ForeignCompany;
import main.model.entities.NationalCompany;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static main.utils.constants.Constants.FOREIGN;
import static main.utils.constants.Constants.NATIONAL;

@WebServlet(name = "CompaniesList", urlPatterns = "/companies/list/")
public class CompaniesListServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DbResult<List<NationalCompany>> nationalCompanies = Database.getInstance().getTopNationalCompanies(20);
        DbResult<List<ForeignCompany>> foreignCompanies = Database.getInstance().getTopForeignCompanies(20);
        if (nationalCompanies.isAnyError()) {
            showErrorPage(req, resp, nationalCompanies);
        } else if (foreignCompanies.isAnyError()) {
            showErrorPage(req, resp, foreignCompanies);
        } else {
            req.setAttribute(NATIONAL, nationalCompanies.value());
            req.setAttribute(FOREIGN, foreignCompanies.value());
            forward("/companies_list.jsp", req, resp);
        }
    }
}
