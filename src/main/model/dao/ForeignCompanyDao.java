package main.model.dao;

import main.model.entities.DbResult;
import main.model.entities.ForeignCompany;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static main.utils.constants.DbConstants.*;

public class ForeignCompanyDao extends Dao<ForeignCompany> {

    @Override
    String getTableName() {
        return FOREIGN_COMPANY;
    }

    @Override
    String getIdColumnName() {
        return FOREIGN_COMPANY_ID;
    }

    @Override
    String createSaveRequest(ForeignCompany fc) {
        return "insert into " + getTableName() + "(name,country) " +
                "values(" +
                "\'" + fc.getName() + "\'" + "," +
                "\'" + fc.getCountry() + "\'" + ")";
    }

    @Override
    ForeignCompany parseResultSetToModel(ResultSet rs) throws SQLException {
        return new ForeignCompany(
                rs.getInt(FOREIGN_COMPANY_ID),
                rs.getString(NAME),
                rs.getString(COUNTRY)
        );
    }

    public DbResult<List<ForeignCompany>> searchByName(String query) {
        String request = "SELECT * FROM " + getTableName() + " WHERE (name LIKE '" + query + "%')";
        return getList(request);
    }
}
