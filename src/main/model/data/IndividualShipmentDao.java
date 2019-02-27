package main.model.data;

import main.model.entities.DbResult;
import main.model.entities.IndividualShipment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


class IndividualShipmentDao extends Dao<IndividualShipment> {

    @Override
    DbResult<IndividualShipment> getSingle(int id) {
        throw new UnsupportedOperationException("Operation not supported yet, no id column for table provided");
    }

    @Override
    String getIdColumnName() {
        throw new UnsupportedOperationException("Operation not supported yet, no id column for table provided");
    }

    @Override
    String getTableName() {
        return "individual_shipments";
    }

    @Override
    String createSaveRequest(IndividualShipment sh) {
        return "insert into individual_shipments(date, product, price, quantity, receiver) " +
                "values(" +
                "\'" + sh.getDate() + "\'" + "," +
                "\'" + sh.getProduct() + "\'" + "," +
                "\'" + sh.getPrice() + "\'" + "," +
                "\'" + sh.getQuantity() + "\'" + "," +
                "\'" + sh.getPersonId() + "\'" + ")";
    }

    @Override
    IndividualShipment parseResultSetToModel(ResultSet rs) throws SQLException {
        return new IndividualShipment(
                rs.getDate("date"),
                rs.getString("product"),
                rs.getFloat("price"),
                rs.getInt("quantity"),
                rs.getInt("receiver")
        );
    }

    DbResult<List<IndividualShipment>> getAllByPersonId(int id) {
        DbResult<List<IndividualShipment>> result = new DbResult<>();
        List<IndividualShipment> list = new ArrayList<>();

        try {
            String dbRequest = "select * from " + getTableName() + " where receiver=" + id;
            ResultSet rs = requestData(dbRequest);
            while (rs.next()) {
                list.add(parseResultSetToModel(rs));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            result.setConnectionError();
        } catch (SQLException e) {
            e.printStackTrace();
            result.setSqlException();
        }
        result.setValue(list);
        return result;
    }
}
