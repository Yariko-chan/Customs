package main.model.dao;

import main.model.entities.DbResult;
import main.model.entities.NationalCompany;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static main.utils.constants.DbConstants.*;

public class NationalCompanyDao extends Dao<NationalCompany> {
    @Override
    String getTableName() {
        return COMPANY;
    }

    @Override
    String getIdColumnName() {
        return COMPANY_ID;
    }

    @Override
    String createSaveRequest(NationalCompany nc) {
        return "insert into " + getTableName() + "(name,UNP,license_number,license_valid_date) " +
                "values(" +
                "\'" + nc.getName() + "\'" + "," +
                "\'" + nc.getUNP() + "\'" + "," +
                "\'" + nc.getLicense() + "\'" + "," +
                "\'" + nc.getLicenseValidDate() + "\'" + ")";
    }

    @Override
    NationalCompany parseResultSetToModel(ResultSet rs) throws SQLException {
        return new NationalCompany(
                rs.getInt(COMPANY_ID),
                rs.getString(NAME),
                rs.getString(UNP),
                rs.getString(LICENSE_NUM),
                rs.getDate(LICENSE_VALID_DATE));
    }

    public DbResult<List<NationalCompany>> searchByNameOrUnp(String query) {
        String request = "SELECT * FROM " + getTableName() + " WHERE ((name LIKE '" + query + "%') OR (unp LIKE '"+ query + "%'))";
        return getList(request);
    }
}
