/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unityhealth.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unityhealth.model.Model;
import com.unityhealth.model.ProductModel;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Clinton
 */
public class UHJParser {

    private ObjectMapper objectMapper;

    private File file;

    private Model[] pModel;

    
    
    
    
    public Model[] parse(String fileName, Model[] model) throws IOException{
        
        objectMapper = new ObjectMapper();
        //file = new File("E:\\biocueticalsload\\product.json");
        file = new File(fileName);
        
          pModel =  objectMapper.readValue(file,model.getClass());
        for(Model pm:pModel){
           System.out.println(pm.toString());
        }
       return pModel;
    }

    /**
     * @return the objectMapper
     */
    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    /**
     * @param objectMapper the objectMapper to set
     */
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * @return the file
     */
    public File getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(File file) {
        this.file = file;
    }

    /**
     * @return the product
     */
    public Model[] getProduct() {
        return pModel;
    }

    /**
     * @param product the product to set
     */
    public void setProduct(Model[] pModel) {
        this.pModel = pModel;
    }
}
