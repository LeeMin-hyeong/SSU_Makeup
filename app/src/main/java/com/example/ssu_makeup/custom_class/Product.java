package com.example.ssu_makeup.custom_class;

import java.io.Serializable;

public class Product implements Serializable {
    private final String productBrand;
    private final String productImageURL;
    private final String productIngredient;
    private final String productName;
    private final String productCategory;
    private final String productIndex;

    public Product(String productBrand, String productImageURL, String productIngredient, String productName, String productCategory,String productIndex){
        this.productBrand = productBrand;
        this.productImageURL = productImageURL;
        this.productIngredient = productIngredient;
        this.productName = productName;
        this.productCategory = productCategory;
        this.productIndex = productIndex;
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
    public String getProductCategory(){
        return productCategory;
    }
    public String getProductIndex(){
        return productIndex;
    }
}
