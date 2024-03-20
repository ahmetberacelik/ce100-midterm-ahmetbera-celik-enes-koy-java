package com.bera.farmermarket;
public class ProductSeason {
    private int price;
    private String name;
    private String season;

    public ProductSeason(int price, String name, String season) {
        this.price = price;
        this.name = name;
        this.season = season;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getSeason() {
        return season;
    }
}
