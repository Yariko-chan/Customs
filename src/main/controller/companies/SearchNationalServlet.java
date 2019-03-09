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
import java.util.ArrayList;
import java.util.List;

import static main.utils.constants.Constants.*;

@WebServlet(name = "SearchNational", urlPatterns = "/national/search")
public class SearchNationalServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String query = req.getParameter(SEARCH);
        if (query != null) {
            DbResult<List<NationalCompany>> nationalCompanies = Database.getInstance().searchNational(query);
            if (nationalCompanies.isAnyError()) {
                showErrorPage(req, resp, nationalCompanies);
            } else {
                // put search results of national companies
                req.setAttribute(NATIONAL, nationalCompanies.value());

                // put top foreign companies or empty list - doesnt matter for nationals search
                DbResult<List<ForeignCompany>> foreignCompanies = Database.getInstance().getTopForeignCompanies(20);
                if (!foreignCompanies.isAnyError()) {
                    req.setAttribute(FOREIGN, foreignCompanies.value());
                } else {
                    req.setAttribute(FOREIGN, new ArrayList<ForeignCompany>());
                }
                forward("/companies_list.jsp", req, resp);
            }
        }
    }
}
