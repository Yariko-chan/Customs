package main.model.dao;

import main.controller.entities.FullTrade;
import main.controller.entities.TradeType;
import main.model.entities.*;
import main.utils.constants.SdfConstants;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FullTradeDao extends Dao<FullTrade>{

    private ContractDao contractDao;
    private TradeDao tradeDao;
    private NationalCompanyDao nationalCompanyDao;
    private ForeignCompanyDao foreignCompanyDao;
    private String contracts;
    private String trades;
    private String nationals;
    private String foreigns;

    public FullTradeDao(ContractDao contractDao, TradeDao tradeDao,
                        NationalCompanyDao nationalCompanyDao, ForeignCompanyDao foreignCompanyDao) {
        this.contractDao = contractDao;
        this.tradeDao = tradeDao;
        this.nationalCompanyDao = nationalCompanyDao;
        this.foreignCompanyDao = foreignCompanyDao;
        contracts = contractDao.getTableName();
        trades = tradeDao.getTableName();
        nationals = nationalCompanyDao.getTableName();
        foreigns = foreignCompanyDao.getTableName();
    }

    /*
    SELECT * FROM contracts, export_import, company, foreign_company
	WHERE contracts.contract_id=export_import.contract
	AND contracts.national_company=company.company_id
	AND contracts.foreign_company=foreign_company.foreign_company_id
     */

    @Override
    String getTableName() {
        return contracts + "," + trades + "," + nationals + "," + foreigns +
                " WHERE contracts.contract_id=export_import.contract " +
                "AND contracts.national_company=company.company_id " +
                "AND contracts.foreign_company=foreign_company.foreign_company_id";
    }

    @Override
    String getIdColumnName() {
        return trades + "." + tradeDao.getIdColumnName();
    }

    @Override
    String createSaveRequest(FullTrade o) {
        throw new UnsupportedOperationException("Cannot save complex object");
    }

    @Override
    FullTrade parseResultSetToModel(ResultSet rs) throws SQLException {
        Contract c = contractDao.parseResultSetToModel(rs);
        Trade t = tradeDao.parseResultSetToModel(rs);
        NationalCompany nc = nationalCompanyDao.parseResultSetToModel(rs);
        ForeignCompany fc = foreignCompanyDao.parseResultSetToModel(rs);
        return new FullTrade(c, t, nc, fc);
    }

    DbResult<List<FullTrade>> getAllOfType(TradeType type) {
        String query = "SELECT * FROM " + getTableName() +
                " AND contracts.type='" + type.dbValue + "'";
        return getList(query);
    }

    DbResult<List<FullTrade>> getTop(TradeType type, int count) {
        String query = "SELECT * FROM " + getTableName() +
                " AND contracts.type='" + type.dbValue + "' LIMIT " + count;
        return getList(query);
    }

    DbResult<List<FullTrade>> getInPeriod(TradeType type, Date fromDate, Date toDate) {
        String from = SdfConstants.DB_DATE_FORMAT.format(fromDate);
        String to = SdfConstants.DB_DATE_FORMAT.format(toDate);
        String query = "SELECT * FROM " + getTableName() +
                " AND contracts.type='" + type.dbValue + "' AND " + trades + ".date BETWEEN '" + from + "' AND '" + to + "'";
        return getList(query);
    }

    DbResult<List<FullTrade>> getForNationalInPeriod(int id, TradeType type, Date fromDate, Date toDate) {
        String from = SdfConstants.DB_DATE_FORMAT.format(fromDate);
        String to = SdfConstants.DB_DATE_FORMAT.format(toDate);
        String query = "SELECT * FROM " + getTableName() +
                " AND contracts.type='" + type.dbValue + "'" +
                " AND " + trades + ".date BETWEEN '" + from + "' AND '" + to + "'" +
                " AND " + nationals + "." + nationalCompanyDao.getIdColumnName() + "=" + id;
        return getList(query);
    }

    DbResult<List<FullTrade>> getForForeignInPeriod(int id, TradeType type, Date fromDate, Date toDate) {
        String from = SdfConstants.DB_DATE_FORMAT.format(fromDate);
        String to = SdfConstants.DB_DATE_FORMAT.format(toDate);
        String query = "SELECT * FROM " + getTableName() +
                " AND contracts.type='" + type.dbValue + "'" +
                " AND " + trades + ".date BETWEEN '" + from + "' AND '" + to + "'" +
                " AND " + foreigns + "." + foreignCompanyDao.getIdColumnName() + "=" + id;
        return getList(query);
    }
}
