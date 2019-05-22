package main.model.dao;

import main.model.entities.DbResult;
import main.model.entities.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static main.utils.constants.DbConstants.*;

class PersonDao extends Dao<Person> {

    @Override
    String getTableName() {
        return PERSON;
    }

    @Override
    String getIdColumnName() {
        return PERSON_ID;
    }

    @Override
    String createSaveRequest(Person p) {
        return "insert into person(first_name, last_name, patronymic, birth_date, passport, country, description) " +
                "values(" +
                "\'" + p.getFirstName() + "\'" + "," +
                "\'" + p.getLastName() + "\'" + "," +
                "\'" + p.getPatronymic() + "\'" + "," +
                "\'" + p.getBirthDate() + "\'" + "," +
                "\'" + p.getPassport() + "\'" + "," +
                "\'" + p.getCountry() + "\'" + "," +
                "\'" + p.getDescription() + "\'" + ")";
    }

    @Override
    Person parseResultSetToModel(ResultSet rs) throws SQLException {
        return new Person(
                rs.getInt(PERSON_ID),
                rs.getString(FIRST_NAME),
                rs.getString(LAST_NAME),
                rs.getString(PATRONYMIC),
                rs.getDate(BIRTH_DATE),
                rs.getString(PASSPORT),
                rs.getString(COUNTRY),
                rs.getString(DESCRIPTION));
    }

    DbResult<List<Person>> searchByName(String name) {
        String request = "SELECT * FROM " + getTableName() + " WHERE ((last_name LIKE '" + name + "%') OR (passport LIKE '"+ name + "%'))";
        return getList(request);
    }
}
