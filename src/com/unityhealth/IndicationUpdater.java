/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unityhealth;

import com.unityhealth.model.BiocIngredientsModel;
import com.unityhealth.model.IngredientsModel;
import com.unityhealth.model.ProductModel;
import com.unityhealth.parser.UHJParser;
import com.unityhealth.service.IProductIngredientsService;
import com.unityhealth.service.IProductService;
import com.unityhealth.serviceimpl.ProductIngredientsServiceImpl;
import com.unityhealth.serviceimpl.ProductServiceImpl;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Sameer S Argade
 */
public class IndicationUpdater {
     public static void main(String[] agrs) {
        Properties prop = new Properties();
        InputStream input = null;
        int brandId = 0;
        String biocModel = null ,strPModel = null,strIModel = null;
        try {
            input = JPForProducts.class.getClassLoader().getResourceAsStream("application.properties");
            prop.load(input);
            brandId = Integer.parseInt(prop.getProperty("brandId"));
            biocModel  = prop.getProperty("biocModel");
            strPModel = prop.getProperty("productModel");
            strIModel = prop.getProperty("ingredientModel");
            // load a properties file
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(JPForProducts.class.getName()).log(Level.SEVERE, null, ex);
        }
        UHJParser jParser = new UHJParser();
        IProductService productService = new ProductServiceImpl();
        IProductIngredientsService ingredientsService = new ProductIngredientsServiceImpl();
        BiocIngredientsModel biocIngModel = new BiocIngredientsModel();
        ProductModel[] pModel = new ProductModel[1];
        IngredientsModel[] iModel = new IngredientsModel[1];
         boolean success = false;
        try {

            long startTime = System.currentTimeMillis();
            if (brandId == 15){
                 System.out.println("product 15");
                //success = productService.updatewarnings((BiocIngredientsModel) jParser.parse(biocModel, biocIngModel),15);
              //  biocIngModel = (BiocIngredientsModel) jParser.parse(biocModel, biocIngModel);
              
     
            }
            else{
                 System.out.println("product " + brandId);
           productService.updateIndications((ProductModel[])(jParser.parse(strPModel,pModel)),brandId);
            }
           
         
            if (success) {

                System.out.println("yippi");
            } else {
                System.out.println("oops");
            }
          
            long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            System.out.println("total time taken to execute " + totalTime / 60000);
        } catch (IOException ex) {
            Logger.getLogger(UHJParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            productService.releasePC();
        }

    }
}
