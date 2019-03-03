package main.model.entities;

public class ForeignCompany {

    private int id;
    private String name;
    private String country;

    public ForeignCompany(int id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public ForeignCompany(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }
}
