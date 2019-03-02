package main.model.data;

import main.model.entities.DbResult;
import main.model.entities.IndividualShipment;
import main.utils.constants.SdfConstants;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        String request = "select * from " + getTableName() + " where receiver=" + id;
        return getList(request);
    }

    // select * from individual_shipments where date between '2019-02-01' and '2019-02-20'
    DbResult<List<IndividualShipment>> getSearchInPeriod(int personId, Date fromDate, Date toDate) {
        String from = SdfConstants.DB_DATE_FORMAT.format(fromDate);
        String to = SdfConstants.DB_DATE_FORMAT.format(toDate);
        String request = "SELECT * FROM " + getTableName() + " WHERE receiver=" + personId + " AND date BETWEEN '" + from + "' AND '" + to + "'";
        return getList(request);
    }
}
