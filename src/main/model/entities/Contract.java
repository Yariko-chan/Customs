package main.model.entities;

import main.controller.entities.Trade;

import java.sql.Date;

public class Contract {

    private int id;
    private String contractNumber;
    private Trade type;
    private Date date;
    private int national;
    private int foreign;

    public Contract(int id, String contractNumber, Trade type, Date date, int national, int foreign) {
        this.id = id;
        this.contractNumber = contractNumber;
        this.type = type;
        this.date = date;
        this.national = national;
        this.foreign = foreign;
    }

    public Contract(String contractNumber, Trade type, Date date, int national, int foreign) {
        this.contractNumber = contractNumber;
        this.type = type;
        this.date = date;
        this.national = national;
        this.foreign = foreign;
    }

    public int getId() {
        return id;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public Trade getType() {
        return type;
    }

    public Date getDate() {
        return date;
    }

    public int getNational() {
        return national;
    }

    public int getForeign() {
        return foreign;
    }
}
