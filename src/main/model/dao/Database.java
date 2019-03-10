package main.model.dao;

import main.controller.entities.FullTrade;
import main.controller.entities.TradeType;
import main.model.entities.*;

import java.sql.Date;
import java.util.List;

public class Database {

    private static volatile Database instance;

    public static Database getInstance() {
        Database localInstance = instance;
        if (localInstance == null) {
            synchronized (Database.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Database();
                }
            }
        }
        return localInstance;
    }

    private LoginDao loginDao = new LoginDao();
    private PersonDao personDao = new PersonDao();
    private IndividualShipmentDao individualShipmentDao = new IndividualShipmentDao();
    private NationalCompanyDao nationalCompanyDao = new NationalCompanyDao();
    private ForeignCompanyDao foreignCompanyDao = new ForeignCompanyDao();
    private ContractDao contractDao = new ContractDao();
    private TradeDao tradeDao = new TradeDao();
    private FullTradeDao fullTradeDao = new FullTradeDao(contractDao, tradeDao, nationalCompanyDao, foreignCompanyDao);

    public DbResult<User> login(String login, String password) {
        return loginDao.login(login, password);
    }

    public DbResult<List<Person>> getAllPersons() {
        return personDao.getAll();
    }

    public DbResult<List<Person>> searchPersons(String name) {
        return personDao.searchByName(name);
    }

    public DbResult<Person> getPerson(int id) {
        return personDao.getSingle(id);
    }

    public DbResult<Boolean> savePerson(Person p) {
        return personDao.saveSingle(p);
    }

    public DbResult<Boolean> saveIndividualShipment(IndividualShipment sh) {
        return individualShipmentDao.saveSingle(sh);
    }

    public DbResult<List<IndividualShipment>> getShipmentsByPersonId(int id) {
        return individualShipmentDao.getAllByPersonId(id);
    }

    public DbResult<List<IndividualShipment>> getShipmentsInPeriod(int personId, Date from, Date to) {
        return individualShipmentDao.getSearchInPeriod(personId, from, to);
    }

    public DbResult<Boolean> saveNationalCompany(NationalCompany nc) {
        return nationalCompanyDao.saveSingle(nc);
    }

    public DbResult<List<NationalCompany>> getAllNationalCompanies() {
        return nationalCompanyDao.getAll();
    }

    public DbResult<List<NationalCompany>> getTopNationalCompanies(int count) {
        return nationalCompanyDao.getTopItems(count);
    }

    public DbResult<List<NationalCompany>> searchNational(String query) {
        return nationalCompanyDao.searchByNameOrUnp(query);
    }

    public DbResult<Boolean> saveForeignCompany(ForeignCompany fc) {
        return foreignCompanyDao.saveSingle(fc);
    }

    public DbResult<List<ForeignCompany>> getAllForeignCompanies() {
        return foreignCompanyDao.getAll();
    }

    public DbResult<List<ForeignCompany>> getTopForeignCompanies(int count) {
        return foreignCompanyDao.getTopItems(count);
    }

    public DbResult<List<ForeignCompany>> searchForeign(String query) {
        return foreignCompanyDao.searchByName(query);
    }

    public DbResult<List<Contract>> getAllContractsOfType(TradeType type) {
        return contractDao.getAll(type);
    }

    public DbResult<List<Contract>> getAllContracts() {
        return contractDao.getAll();
    }

    public DbResult<Boolean> saveContract(Contract c) {
        return contractDao.saveSingle(c);
    }

    public DbResult<List<Trade>> getAllTrades() {
        return tradeDao.getAll();
    }

    public DbResult<List<Trade>> getAllTradesOfType(TradeType tradeType) {
        return tradeDao.getAll(tradeType);
    }

    public DbResult<List<Trade>> getTopTrades(TradeType tradeType, int count) {
        return tradeDao.getTop(tradeType, count);
    }

    public DbResult<Boolean> saveTrade(Trade trade) {
        return tradeDao.saveSingle(trade);
    }

    public DbResult<List<FullTrade>> getAllFullTrades() {
        return fullTradeDao.getAll();
    }

    public DbResult<List<FullTrade>> getAllFullTradesOfType(TradeType type) {
        return fullTradeDao.getAllOfType(type);
    }

    public DbResult<List<FullTrade>> getTopFullTradesOfType(TradeType type, int count) {
        return fullTradeDao.getTop(type, count);
    }

    public DbResult<List<FullTrade>> getFullTradesInPeriod(TradeType type, Date fromDate, Date toDate) {
        return fullTradeDao.getInPeriod(type, fromDate, toDate);
    }
}
