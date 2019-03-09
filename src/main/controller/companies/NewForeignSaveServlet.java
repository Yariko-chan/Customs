package main.controller.companies;

import main.controller.BaseServlet;
import main.controller.entities.SaveResult;
import main.model.dao.Database;
import main.model.entities.DbResult;
import main.model.entities.ForeignCompany;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static main.utils.StringUtils.isCorrectCountry;
import static main.utils.StringUtils.isNullOrEmpty;

@WebServlet(name = "NewForeignSaveServlet", urlPatterns = "/new/foreign/save/")
public class NewForeignSaveServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        SaveResult saveResult = new SaveResult();

        String name = request.getParameter("name");
        String country = request.getParameter("country");
        if (isNullOrEmpty(name) || !isCorrectCountry(request.getServletContext(), country)) {
            saveResult.setInputError();
        } else {
            ForeignCompany fc = new ForeignCompany(name, country);
            DbResult<Boolean> dbResult = Database.getInstance().saveForeignCompany(fc);
            if (dbResult.isAnyError() || !dbResult.value()) {
                showErrorPage(request, resp, dbResult);
            } else {
                forward("/companies/list/", request, resp);
            }
        }
    }
}
