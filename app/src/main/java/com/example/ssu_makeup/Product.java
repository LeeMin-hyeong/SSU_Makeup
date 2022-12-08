package com.example.ssu_makeup;

import java.io.Serializable;

public class Product implements Serializable {
    private final String productName;
    private final String productBrand;
    private final String productImageURL;
    private final String productIngredient;

    public Product(String productBrand, String productImageURL, String productName, String productIngredient){
        this.productBrand = productBrand;
        this.productImageURL = productImageURL;
        this.productName = productName;
        this.productIngredient = productIngredient;
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
