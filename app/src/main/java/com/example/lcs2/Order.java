package com.example.lcs2;

import java.util.ArrayList;
import java.util.Date;

class Order {

    int orderID;
    ArrayList<CartItem> items;
    String Status;
    Double total;
    String date;
    public Order() {
    }

    public Order(ArrayList<CartItem> items, String status) {
        this.items = items;
        Status = status;

    }

    public Order(int orderID, ArrayList<CartItem> items, String status, Double total, String date) {
        this.orderID = orderID;
        this.items = items;
        Status = status;
        this.total = total;
        this.date = date;
    }

    public ArrayList<CartItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<CartItem> items) {
        this.items = items;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }
}
