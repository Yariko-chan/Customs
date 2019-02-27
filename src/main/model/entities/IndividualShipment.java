package main.model.entities;

import java.sql.Date;

public class IndividualShipment {
    private Date date = new Date(-1);
    private String product = "";
    private float price = 0f;
    private int quantity = 0;
    private int personId = -1;

    public IndividualShipment(Date date, String product, float price, int quantity, int personId) {
        this.date = date;
        this.product = product;
        this.price = price;
        this.quantity = quantity;
        this.personId = personId;
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

    public int getPersonId() {
        return personId;
    }
}
