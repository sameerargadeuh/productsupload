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
public class ProductImageModel implements Model {
     
            private String imageFile;  //"ImageFile"
            private String imageDimension;  //"ImageFile"
            private String CDN;  //"ImageFile"
            private String CDNImageUrl;  //"ImageFile"
 @JsonProperty("ImageFile")
    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }
 //"ImageFile"
 @JsonProperty("ImageDimension")
    public String getImageDimension() {
        return imageDimension;
    }

    public void setImageDimension(String imageDimension) {
        this.imageDimension = imageDimension;
    }
 //"ImageFile"
 @JsonProperty("CDN")
    public String getCDN() {
        return CDN;
    }

    public void setCDN(String CDN) {
        this.CDN = CDN;
    }
 //"ImageFile"
 @JsonProperty("CDNImageUrl")
    public String getCDNImageUrl() {
        return CDNImageUrl;
    }

    public void setCDNImageUrl(String CDNImageUrl) {
        this.CDNImageUrl = CDNImageUrl;
    }
 //"ImageFile"

}
