package main.controller.individuals;

import main.controller.BaseServlet;
import main.controller.entities.SaveResult;
import main.model.data.Database;
import main.model.entities.DbResult;
import main.model.entities.Person;
import main.utils.constants.Constants;
import main.utils.constants.SdfConstants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static main.utils.StringUtils.isCorrectCountry;
import static main.utils.StringUtils.isNullOrEmpty;

@WebServlet(name = "NewPersonSave", urlPatterns = "/new/person/save/")
public class NewPersonSaveServlet extends BaseServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SaveResult saveResult = new SaveResult();

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String patronymic = request.getParameter("patronymic");
        Date birthdate = parseDate(request.getParameter("birthdate"));
        String passport = request.getParameter("passport");
        String country = request.getParameter("country");
        if (isNullOrEmpty(name) || isNullOrEmpty(surname) || isNullOrEmpty(patronymic) ||
                isNullOrEmpty(birthdate) || isNullOrEmpty(passport) ||
                !isCorrectCountry(request.getServletContext(), country)) {
            saveResult.setInputError();
        } else {
            Person p = new Person(name, surname, patronymic, birthdate, passport, country);
            DbResult<Boolean> dbResult = Database.getInstance().savePerson(p);
            if (dbResult.isAnyError() || !dbResult.value()) {
                saveResult.setSavingError();
            }
        }

        request.setAttribute(Constants.SAVE_PERSON_RESULT, saveResult);
        forward("/individuals_add_person_result.jsp", request, response);
    }

    private Date parseDate(String birthdate) {
        if (birthdate == null) {
            return null;
        }
        try {
            java.util.Date date = SdfConstants.INPUT_DATE_FORMAT.parse(birthdate);
            return new Date(date.getTime());
        } catch (ParseException e) {
            return null;
        }
    }
}
