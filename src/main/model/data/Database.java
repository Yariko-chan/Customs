package main.model.data;

import main.model.entities.DbResult;
import main.model.entities.Person;
import main.model.entities.User;

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

    public DbResult<User> login(String login, String password) {
        return loginDao.login(login, password);
    }

    public DbResult<List<Person>> getAllPersons() {
        return personDao.getAllPersons();
    }

    public DbResult<Boolean> savePerson(Person p) {
        return personDao.savePerson(p);
    }
}
