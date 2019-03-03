package main.model.entities;

import java.sql.Date;

public class NationalCompany {

    private int id;
    private String name;
    private String UNP;
    private String license;
    private Date licenseValidDate;

    public NationalCompany(int id, String name, String UNP, String license, Date licenseValidDate) {
        this.id = id;
        this.name = name;
        this.UNP = UNP;
        this.license = license;
        this.licenseValidDate = licenseValidDate;
    }

    public NationalCompany(String name, String UNP, String license, Date licenseValidDate) {
        this.name = name;
        this.UNP = UNP;
        this.license = license;
        this.licenseValidDate = licenseValidDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUNP() {
        return UNP;
    }

    public String getLicense() {
        return license;
    }

    public Date getLicenseValidDate() {
        return licenseValidDate;
    }
}
