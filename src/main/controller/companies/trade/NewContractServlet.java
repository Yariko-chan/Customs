package main.controller.companies.trade;

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

import static main.utils.constants.Constants.*;

@WebServlet(name = "NewContract", urlPatterns = "/new/contract/")
public class NewContractServlet extends BaseServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DbResult<List<ForeignCompany>> foreignCompanies = Database.getInstance().getAllForeignCompanies();
        DbResult<List<NationalCompany>> nationalCompanies = Database.getInstance().getAllNationalCompanies();
        if (nationalCompanies.isAnyError()) {
            showErrorPage(request, response, nationalCompanies);
        } else if (foreignCompanies.isAnyError()) {
            showErrorPage(request, response, foreignCompanies);
        } else {
            request.setAttribute(FOREIGN, foreignCompanies.value());
            request.setAttribute(NATIONAL, nationalCompanies.value());
            forward("/contract_new.jsp", request, response);
        }
    }
}
