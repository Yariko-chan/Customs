package main.model.dao;

import main.controller.entities.TradeType;
import main.model.entities.DbResult;
import main.model.entities.Trade;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static main.utils.constants.DbConstants.*;

public class TradeDao extends Dao<Trade> {
    @Override
    String getTableName() {
        return "export_import";
    }

    @Override
    String getIdColumnName() {
        return "id";
    }

    @Override
    String createSaveRequest(Trade t) {
        return "INSERT INTO " + getTableName() +
                "(date,product,price,quantity,contract) VALUES(" +
                "\'" + t.getDate() + "\'" + "," +
                "\'" + t.getProduct() + "\'" + "," +
                "\'" + t.getPrice() + "\'" + "," +
                "\'" + t.getQuantity() + "\'" + "," +
                "\'" + t.getContractId() + "\'" + ")";
    }

    @Override
    Trade parseResultSetToModel(ResultSet rs) throws SQLException {
        return new Trade(
                rs.getInt(ID),
                rs.getDate(DATE),
                rs.getString(PRODUCT),
                rs.getFloat(PRICE),
                rs.getInt(QUANTITY),
                rs.getInt(CONTRACT)
        );
    }

    DbResult<List<Trade>> getAll(TradeType type) {
        //SELECT * FROM export_import,contracts
        // WHERE contracts.contract_id=export_import.contract
        // AND type='EXP';
        String query =
                "SELECT * FROM " + getTableName() + ",contracts " +
                "WHERE contracts.contract_id=" + getTableName() + ".contract " +
                "AND type='" + type.dbValue + "'";
        return getList(query);
    }

    DbResult<List<Trade>> getTop(TradeType type, int count) {
        //SELECT * FROM export_import,contracts
        // WHERE contracts.contract_id=export_import.contract
        // AND type='EXP' LIMIT 20;
        String query =
                "SELECT * FROM " + getTableName() + ",contracts " +
                        "WHERE contracts.contract_id=" + getTableName() + ".contract " +
                        "AND type='" + type.dbValue + "' limit " + count;
        return getList(query);
    }
}
