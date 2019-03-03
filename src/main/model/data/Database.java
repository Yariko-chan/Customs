package main.model.data;

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
    private ForeignCountryDao foreignCountryDao = new ForeignCountryDao();

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

    public DbResult<List<NationalCompany>> getTopNationalCompanies(int count) {
        return nationalCompanyDao.getTopItems(count);
    }

    public DbResult<Boolean> saveForeignCompany(ForeignCompany fc) {
        return foreignCountryDao.saveSingle(fc);
    }

    public DbResult<List<ForeignCompany>> getTopForeignCompanies(int count) {
        return foreignCountryDao.getTopItems(count);
    }
}
