/*
    This class holds the model for Products of imgateway
 */
package com.unityhealth.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Sameer
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductModel implements Model {

    private String optionSize;
    private String productAustl;
    private String productDosage;
    private String productBrand;
    private String productID;
    private String productName;
    private String productNote;
    private String productWarning;
    private String optionPartNo;
    private IngredientsModel[] ingredients;
    private ProductItemsModel[] items;
   
    //optional properties
    private String optionBarCode;
    private String optionDepth;
    private String optionHeight;
    private String optionWeight;
    private String optionWidth;
    private String productByLine;
    private String productFeature;
    
    
    /**
     * @return the optionsSize
     */
     @JsonProperty("OptionSize")
    public String getOptionSize() {
        return optionSize;
    }

    /**
     * @param optionSize the optionsSize to set
     */
    public void setOptionsSize(String optionSize) {
        this.optionSize = optionSize;
    }

    /**
     * @return the productAustl
     */
    @JsonProperty("ProductAustl")
    public String getProductAustl() {
        return productAustl;
    }

    /**
     * @param productAustl the productAustl to set
     */
    public void setProductAustl(String productAustl) {
        this.productAustl = productAustl;
    }

    /**
     * @return the productDosage
     */
    @JsonProperty("ProductDosage")
    public String getProductDosage() {
        return productDosage;
    }

    /**
     * @param productDosage the productDosage to set
     */
    public void setProductDosage(String productDosage) {
        this.productDosage = productDosage;
    }

    /**
     * @return the productBrand
     */
    @JsonProperty("ProductBrand")
    public String getProductBrand() {
        return productBrand;
    }

    /**
     * @param productBrand the productBrand to set
     */
    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
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
     * @return the productName
     */
    @JsonProperty("ProductName")
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return the productNote
     */
    @JsonProperty("ProductNote")
    public String getProductNote() {
        return productNote;
    }

    /**
     * @param productNote the productNote to set
     */
    public void setProductNote(String productNote) {
        this.productNote = productNote;
    }

    @Override
    public String toString() {

        return " [ optionSize : " + optionSize
            + " productAustl : " + productAustl
            + " productDosage : " + productDosage
            + " productBrand : " + productBrand
            + " productID : " + productID
            + " productName : " + productName
            + " productNote : " + productNote
            + " productNote : " + productWarning
            + " ]";

    }

    /**
     * @return the optionBarCode
     */
    public String getOptionBarCode() {
        return optionBarCode;
    }

    /**
     * @param optionBarCode the optionBarCode to set
     */
    public void setOptionBarCode(String optionBarCode) {
        this.optionBarCode = optionBarCode;
    }

    /**
     * @return the optionDepth
     */
    public String getOptionDepth() {
        return optionDepth;
    }

    /**
     * @param optionDepth the optionDepth to set
     */
    public void setOptionDepth(String optionDepth) {
        this.optionDepth = optionDepth;
    }

    /**
     * @return the optionHeight
     */
    public String getOptionHeight() {
        return optionHeight;
    }

    /**
     * @param optionHeight the optionHeight to set
     */
    public void setOptionHeight(String optionHeight) {
        this.optionHeight = optionHeight;
    }

    /**
     * @return the optionWeight
     */
    public String getOptionWeight() {
        return optionWeight;
    }

    /**
     * @param optionWeight the optionWeight to set
     */
    public void setOptionWeight(String optionWeight) {
        this.optionWeight = optionWeight;
    }

    /**
     * @return the optionWidth
     */
    public String getOptionWidth() {
        return optionWidth;
    }

    /**
     * @param optionWidth the optionWidth to set
     */
    public void setOptionWidth(String optionWidth) {
        this.optionWidth = optionWidth;
    }

    /**
     * @return the productByLine
     */
    public String getProductByLine() {
        return productByLine;
    }

    /**
     * @param productByLine the productByLine to set
     */
    public void setProductByLine(String productByLine) {
        this.productByLine = productByLine;
    }

    /**
     * @return the productFeature
     */
    public String getProductFeature() {
        return productFeature;
    }

    /**
     * @param productFeature the productFeature to set
     */
    public void setProductFeature(String productFeature) {
        this.productFeature = productFeature;
    }

    /**
     * @return the productWarning
     */
    @JsonProperty("ProductWarning")
    public String getProductWarning() {
        return productWarning;
    }

    /**
     * @param productWarning the productWarning to set
     */
    public void setProductWarning(String productWarning) {
        this.productWarning = productWarning;
    }
     @JsonProperty("OptionPartNo")
     public String getOptionPartNo() {
        return optionPartNo;
    }

    public void setOptionPartNo(String optionPartNo) {
        this.optionPartNo = optionPartNo;
    }
 @JsonProperty("Ingredients")
    public IngredientsModel[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(IngredientsModel[] ingredients) {
        this.ingredients = ingredients;
    }
@JsonProperty("Items")
    public ProductItemsModel[] getItems() {
        return items;
    }

    public void setItems(ProductItemsModel[] items) {
        this.items = items;
    }
}
