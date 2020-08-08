package com.example.lcs2;

class Product {
    private int id;
    private String name;
    private String description;
    private String product_image;
    private String category;
    private int in_stock;
    private double unit_price;

    public Product() {

    }

    public Product(int id, String name, String description, String product_image, String category, int in_stock, double unit_price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.product_image = product_image;
        this.category = category;
        this.in_stock = in_stock;
        this.unit_price = unit_price;
    }

    public Product(String name, String description, String product_image, String category, int in_stock, double unit_price) {
        this.name = name;
        this.description = description;
        this.product_image = product_image;
        this.category = category;
        this.in_stock = in_stock;
        this.unit_price = unit_price;
    }

    public Product(int id, String name, String product_image, double unit_price) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getIn_stock() {
        return in_stock;
    }

    public void setIn_stock(int in_stock) {
        this.in_stock = in_stock;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }


}
