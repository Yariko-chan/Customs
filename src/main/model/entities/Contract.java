package main.model.entities;

import main.controller.entities.TradeType;

import java.sql.Date;

public class Contract {

    private int id;
    private String contractNumber;
    private TradeType type;
    private Date date;
    private int national;
    private int foreign;

    public Contract(int id) {
        this.id = id;
    }

    public Contract(int id, String contractNumber, TradeType type, Date date, int national, int foreign) {
        this.id = id;
        this.contractNumber = contractNumber;
        this.type = type;
        this.date = date;
        this.national = national;
        this.foreign = foreign;
    }

    public Contract(String contractNumber, TradeType type, Date date, int national, int foreign) {
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

    public TradeType getType() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contract contract = (Contract) o;

        return id == contract.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
