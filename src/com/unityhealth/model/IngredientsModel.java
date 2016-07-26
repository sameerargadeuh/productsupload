/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unityhealth.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Sameer
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class IngredientsModel implements Model {

    private String equivalentValue;
    private String ingredientID;
    private String ingredientName;
    private String ingredientProductID;
    private String ingredientScientific;
    private String ingredientSource;
    private String ingredientType;
    private String orderNumber;
    private String productID;
    private String quantity;

    /**
     * @return the equivalentValue
     */
    @JsonProperty("EquivalentValue")
    public String getEquivalentValue() {
        return equivalentValue;
    }

    /**
     * @param equivalentValue the equivalentValue to set
     */
    public void setEquivalentValue(String equivalentValue) {
        this.equivalentValue = equivalentValue;
    }

    /**
     * @return the ingredientID
     */
    @JsonProperty("IngredientID")
    public String getIngredientID() {
        return ingredientID;
    }

    /**
     * @param ingredientID the ingredientID to set
     */
    public void setIngredientID(String ingredientID) {
        this.ingredientID = ingredientID;
    }

    /**
     * @return the ingredientName
     */
    @JsonProperty("IngredientName")
    public String getIngredientName() {
        return ingredientName;
    }

    /**
     * @param ingredientName the ingredientName to set
     */
    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    /**
     * @return the ingredientProductID
     */
    @JsonProperty("IngredientProductID")
    public String getIngredientProductID() {
        return ingredientProductID;
    }

    /**
     * @param ingredientProductID the ingredientProductID to set
     */
    public void setIngredientProductID(String ingredientProductID) {
        this.ingredientProductID = ingredientProductID;
    }

    /**
     * @return the ingredientScientific
     */
    @JsonProperty("IngredientScientific")
    public String getIngredientScientific() {
        return ingredientScientific;
    }

    /**
     * @param ingredientScientific the ingredientScientific to set
     */
    public void setIngredientScientific(String ingredientScientific) {
        this.ingredientScientific = ingredientScientific;
    }

    /**
     * @return the ingredientSource
     */
    @JsonProperty("IngredientSource")
    public String getIngredientSource() {
        return ingredientSource;
    }

    /**
     * @param ingredientSource the ingredientSource to set
     */
    public void setIngredientSource(String ingredientSource) {
        this.ingredientSource = ingredientSource;
    }

    /**
     * @return the ingredientType
     */
    @JsonProperty("IngredientType")
    public String getIngredientType() {
        return ingredientType;
    }

    /**
     * @param ingredientType the ingredientType to set
     */
    public void setIngredientType(String ingredientType) {
        this.ingredientType = ingredientType;
    }

    /**
     * @return the orderNumber
     */
    @JsonProperty("OrderNumber")
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * @param orderNumber the orderNumber to set
     */
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * @return the productID
     */
    @JsonProperty("ProductID")
    public String getProductID() {
        return productID;
    }

    /**
     * @param productID the productID to set
     */
    public void setProductID(String productID) {
        this.productID = productID;
    }

    /**
     * @return the quantity
     */
    @JsonProperty("Quantity")
    public String getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    
    @Override
    public String toString() {

        return " [ equivalentValue : " + equivalentValue
            + " ingredientID : " + ingredientID
            + " quantity : " + quantity
            + " orderNumber : " + orderNumber
            + " productID : " + productID
            + " ingredientType : " + ingredientType
            + " ingredientSource : " + ingredientSource
            + " ingredientScientific : " + ingredientScientific
            + " ]";

    }


}
