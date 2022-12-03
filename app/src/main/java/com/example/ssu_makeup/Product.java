package com.example.ssu_makeup;

public class Product {
    private final String productName;
    private final String productBrand;
    private final String productImageURL;

    Product(String productBrand, String productImageURL, String productName){
        this.productBrand = productBrand;
        this.productImageURL = productImageURL;
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
}
