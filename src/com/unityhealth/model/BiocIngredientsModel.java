/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unityhealth.model;

/**
 *
 * @author Clinton
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Sameer
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BiocIngredientsModel implements Model {
    private String status;
    private String message;
    private ProductModel[] products;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
 @JsonProperty("products")
    public ProductModel[] getProducts() {
        return products;
    }

    public void setProducts(ProductModel[] products) {
        this.products = products;
    }
    
     @Override
    public String toString() {

        return " [ status : " + status
            + " message : " + message
            + " products length : " + products.length
             + " products ingredients : " + products[0].getIngredients()[0].toString()
            + " ]";

    }
}
