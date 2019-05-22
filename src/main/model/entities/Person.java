package main.model.entities;

import java.sql.Date;

public class Person {
    private int personId = -1;
    private String firstName = "";
    private String lastName = "";
    private String patronymic = "";
    private Date birthDate = new Date(-1);
    private String passport = "";
    private String country = "";
    private String description = "";

    public Person() {
    }

    public Person(String firstName, String lastName, String patronymic,
                  Date birthDate, String passport, String country, String description) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.birthDate = birthDate;
        this.passport = passport;
        this.country = country;
        this.description = description;
    }

    public Person(int personId, String firstName, String lastName, String patronymic,
                  Date birthDate, String passport, String country, String description) {
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.birthDate = birthDate;
        this.passport = passport;
        this.country = country;
        this.description = description;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
