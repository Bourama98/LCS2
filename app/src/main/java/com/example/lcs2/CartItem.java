/*
 * CS-499
 * 4-2 Milestone Three: Enhancement Two: Algorithms and Data Structure
 * Bourama Mangara
 * 26 July 2020
 */

package com.example.lcs2;

public class CartItem {
    private int id;
    private String name;
    private String product_image;
    private double unit_price;
    private int quantity;

   // private int product_rating;


    public CartItem() {
    }

    public CartItem(String name, String product_image, double unit_price, int quantity) {
        this.name = name;
        this.product_image = product_image;
        this.unit_price = unit_price;
        this.quantity = quantity;
    }

    public CartItem(int id, String name, String product_image, double unit_price, int quantity) {
        this.id = id;
        this.name = name;
        this.product_image = product_image;
        this.unit_price = unit_price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
