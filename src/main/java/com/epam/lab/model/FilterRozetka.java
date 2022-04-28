package com.epam.lab.model;

public class FilterRozetka {
    private String product;
    private String brand;
    private String sum;

    public FilterRozetka(String product, String brand, String sum) {
        this.product = product;
        this.brand = brand;
        this.sum = sum;
    }
    public String getProduct() {
        return product;
    }

    public String getBrand() {
        return brand;
    }

    public String getSum() {
        return sum;
    }

}
