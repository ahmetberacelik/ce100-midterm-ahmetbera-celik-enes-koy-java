/**
 * @file ProductSeason.java
 *
 * @brief This file contains the implementation of the ProductSeason class.
 */
package com.bera.farmermarket;
/**
 * Represents a product season with price, name, and season information.
 */
public class ProductSeason {
    private int price; /**< Price of the product. */
    private String name; /**< Name of the product. */
    private String season; /**< Season associated with the product. */
    /**
     * Constructs a ProductSeason with the specified price, name, and season.
     *
     * @param price  the price of the product
     * @param name   the name of the product
     * @param season the season of the product
     */
    public ProductSeason(int price, String name, String season) {
        this.price = price;
        this.name = name;
        this.season = season;
    }
    /**
     * Gets the price of the product.
     *
     * @return the price of the product
     */
    public int getPrice() {
        return price;
    }
    /**
     * Gets the name of the product.
     *
     * @return the name of the product
     */
    public String getName() {
        return name;
    }
    /**
     * Gets the season of the product.
     *
     * @return the season of the product
     */
    public String getSeason() {
        return season;
    }
}
