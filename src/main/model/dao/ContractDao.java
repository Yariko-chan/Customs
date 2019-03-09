package main.model.dao;

import main.controller.entities.Trade;
import main.model.entities.Contract;
import main.model.entities.DbResult;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static main.utils.constants.DbConstants.*;

public class ContractDao extends Dao<Contract> {

    @Override
    String getTableName() {
        return "contracts";
    }

    @Override
    String getIdColumnName() {
        return "contract_id";
    }

    @Override
    String createSaveRequest(Contract c) {
        return "INSERT INTO " + getTableName() +
                "(contract_number,type,date,national_company,foreign_company) VALUES(" +
                "\'" + c.getContractNumber() + "\'" + "," +
                "\'" + c.getType().dbValue + "\'" + "," +
                "\'" + c.getDate() + "\'" + "," +
                "\'" + c.getNational() + "\'" + "," +
                "\'" + c.getForeign() + "\'" + ")";
    }

    @Override
    Contract parseResultSetToModel(ResultSet rs) throws SQLException {
        return new Contract(
                rs.getInt(CONTRACT_ID),
                rs.getString(CONTRACT_NUMBER),
                Trade.getByDbTag(rs.getString(TYPE)),
                rs.getDate(DATE),
                rs.getInt(NATIONAL_COMPANY),
                rs.getInt(FOREIGN_COMPANY)
        );
    }

    DbResult<List<Contract>> getAll(Trade type) {
        String query = "SELECT * FROM " + getTableName() + " WHERE type='" + type.dbValue + "'";
        return getList(query);
    }
}
