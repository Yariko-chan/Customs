package main.model.entities;

import java.sql.Date;

public class Trade {

    private int id;
    private Date date;
    private String product;
    private float price;
    private int quantity;
    private  int contractId;

    public Trade(int id, Date date, String product, float price, int quantity, int contractId) {
        this.id = id;
        this.date = date;
        this.product = product;
        this.price = price;
        this.quantity = quantity;
        this.contractId = contractId;
    }

    public Trade(Date date, String product, float price, int quantity, int contractId) {
        this.date = date;
        this.product = product;
        this.price = price;
        this.quantity = quantity;
        this.contractId = contractId;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getProduct() {
        return product;
    }

    public float getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getContractId() {
        return contractId;
    }
}
