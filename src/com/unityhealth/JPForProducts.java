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
 * @author Clinton
 */
public class JPForProducts {

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
//             jParser.parse("E:\\biocueticalsload\\28-11-2016\\product\\product.json",biocIngModel);
            // jParser.parse("E:\\biocueticalsload\\product-ingredients.json",iModel);
            //if(productService.saveProduct((ProductModel[])(jParser.parse("E:\\biocueticalsload\\product.json",pModel)))){
            long startTime = System.currentTimeMillis();
            if (brandId == 15){
                success = productService.saveProduct((BiocIngredientsModel) jParser.parse(biocModel, biocIngModel));
            }else {
                System.out.println("product 88");
                success = productService.saveProduct((ProductModel[])(jParser.parse(strPModel,pModel)))
               &&   ingredientsService.saveProductIngredients((IngredientsModel[])(jParser.parse(strIModel,iModel)));

            }
            
            //jParser.parse("E:\\biocueticalsload\\ProductLoadBioc.json",biocIngModel);
            // boolean success = productService.saveProduct((ProductModel[])(jParser.parse("E:\\biocueticalsload\\imgateway-json-item-extract\\products.json",pModel)))
            //   &&   ingredientsService.saveProductIngredients((IngredientsModel[])(jParser.parse("E:\\biocueticalsload\\imgateway-json-item-extract\\product-ingredients.json",iModel)))
            // ;
            if (success) {

                System.out.println("yippi");
            } else {
                System.out.println("oops");
            }
            // jParser.parse();
            //  jParser.setObjectMapper(objectMapper);
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
