package main.model.data;

import main.model.entities.ForeignCompany;

import java.sql.ResultSet;
import java.sql.SQLException;

import static main.utils.constants.DbConstants.*;

public class ForeignCountryDao extends Dao<ForeignCompany> {

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
}
