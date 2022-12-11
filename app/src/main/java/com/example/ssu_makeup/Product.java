package com.example.ssu_makeup;

import java.io.Serializable;

public class Product implements Serializable {
    private final String productBrand;
    private final String productImageURL;
    private final String productIngredient;
    private final String productName;

    public Product(){
        this.productBrand = null;
        this.productImageURL = null;
        this.productIngredient = null;
        this.productName = null;
    }

    public Product(String productBrand, String productImageURL, String productIngredient, String productName){
        this.productBrand = productBrand;
        this.productImageURL = productImageURL;
        this.productIngredient = productIngredient;
        this.productName = productName;
    }
    public String getProductName(){
        return productName;
    }
    public String getProductBrand(){
        return productBrand;
    }
    public String getProductImageURL(){
        return productImageURL;
    }
    public String getProductIngredient(){
        return productIngredient;
    }
}
