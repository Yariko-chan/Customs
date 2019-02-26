package main.model.data;

import main.model.entities.DbResult;
import main.model.entities.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static main.utils.constants.DbConstants.*;

public class PersonDao extends Dao {

    public DbResult<Person> getPerson(int id) {
        DbResult<Person> result = new DbResult<>();
        Person p = null;

        try {
            p = getPersonFroDb(id);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            result.setConnectionError();
        } catch (SQLException e) {
            e.printStackTrace();
            result.setSqlException();
        }
        result.setValue(p);
        return result;
    }

    public DbResult<List<Person>> getAllPersons() {
        DbResult<List<Person>> result = new DbResult<>();
        List<Person> persons = new ArrayList<>();

        try {
            persons = getPersons();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            result.setConnectionError();
        } catch (SQLException e) {
            e.printStackTrace();
            result.setSqlException();
        }
        result.setValue(persons);
        return result;
    }

    public DbResult<Boolean> savePerson(Person p) {
        DbResult<Boolean> result = new DbResult<>();
        boolean value = false;
        try {
            int savedCount = save(p);
            if (savedCount > 0) value = true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            result.setConnectionError();
        } catch (SQLException e) {
            e.printStackTrace();
            result.setSqlException();
        }
        result.setValue(value);
        return result;
    }

    private int save(Person p) throws ClassNotFoundException, SQLException {
        String dbRequest = new StringBuilder()
                .append("insert into person(first_name, last_name, patronymic, birth_date, passport, country) ")
                .append( "values(")
                .append("\'").append(p.getFirstName()).append("\'").append(",")
                .append("\'").append(p.getLastName()).append("\'").append(",")
                .append("\'").append(p.getPatronymic()).append("\'").append(",")
                .append("\'").append(p.getBirthDate()).append("\'").append(",")
                .append("\'").append(p.getPassport()).append("\'").append(",")
                .append("\'").append(p.getCountry()).append("\'").append(")")
                .toString();
        return request(dbRequest);
    }

    private List<Person> getPersons() throws ClassNotFoundException, SQLException {
        String dbRequest = "select * from person";
        ResultSet rs = requestData(dbRequest);
        List<Person> result = new ArrayList<>();
        while (rs.next()) {
            result.add(new Person(
                    rs.getInt(PERSON_ID),
                    rs.getString(FIRST_NAME),
                    rs.getString(LAST_NAME),
                    rs.getString(PATRONYMIC),
                    rs.getDate(BIRTH_DATE),
                    rs.getString(PASSPORT),
                    rs.getString(COUNTRY)));
        }
        return result;
    }

    private Person getPersonFroDb(int id) throws ClassNotFoundException, SQLException {
        String dbRequest = "select * from person where person.person_id=" + id;
        ResultSet rs = requestData(dbRequest);
        Person p = null;
        if (rs.next()) {
            p = new Person(
                    rs.getInt(PERSON_ID),
                    rs.getString(FIRST_NAME),
                    rs.getString(LAST_NAME),
                    rs.getString(PATRONYMIC),
                    rs.getDate(BIRTH_DATE),
                    rs.getString(PASSPORT),
                    rs.getString(COUNTRY));
        }
        return p;
    }
}
