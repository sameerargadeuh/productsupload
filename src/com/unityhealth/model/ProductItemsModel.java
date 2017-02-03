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
public class ProductItemsModel implements Model {

    private String partNo;

    private String size;

    private ProductImageModel[] images;

    @JsonProperty("PartNo")
    public String getPartNo() {
        return partNo;
    }

    public void setPartNo(String partNo) {
        this.partNo = partNo;
    }

    @JsonProperty("Size")
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @JsonProperty("Images")
    public ProductImageModel[] getImages() {
        return images;
    }

    public void setImages(ProductImageModel[] images) {
        this.images = images;
    }

}
