/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unityhealth;

import com.unityhealth.model.IngredientsModel;
import com.unityhealth.model.ProductModel;
import com.unityhealth.parser.UHJParser;
import com.unityhealth.service.IProductIngredientsService;
import com.unityhealth.service.IProductService;
import com.unityhealth.serviceimpl.ProductIngredientsServiceImpl;
import com.unityhealth.serviceimpl.ProductServiceImpl;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Clinton
 */
public class JPForProducts {
    
   public static void main(String[] agrs) {
        
        UHJParser jParser = new UHJParser();
        IProductService productService = new ProductServiceImpl();
        IProductIngredientsService ingredientsService = new ProductIngredientsServiceImpl();
      ProductModel[] pModel = new  ProductModel[1];
     IngredientsModel []iModel = new IngredientsModel[1];
        try {
            // jParser.parse("E:\\biocueticalsload\\product.json",pModel);
            // jParser.parse("E:\\biocueticalsload\\product-ingredients.json",iModel);
            //if(productService.saveProduct((ProductModel[])(jParser.parse("E:\\biocueticalsload\\product.json",pModel)))){
            long startTime = System.currentTimeMillis();


            boolean success = productService.saveProduct((ProductModel[])(jParser.parse("E:\\biocueticalsload\\imgateway-json-item-extract\\products.json",pModel)))
                &&   ingredientsService.saveProductIngredients((IngredientsModel[])(jParser.parse("E:\\biocueticalsload\\imgateway-json-item-extract\\product-ingredients.json",iModel)))
                ;
                if(success){
               
                System.out.println("yippi");
        }else{
            System.out.println("oops");
            }
          // jParser.parse();
            //  jParser.setObjectMapper(objectMapper);
            long endTime   = System.currentTimeMillis();
long totalTime = endTime - startTime;
System.out.println("total time taken to execute " + totalTime/60000);
        } catch (IOException ex) {
            Logger.getLogger(UHJParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch(Exception e){
            e.printStackTrace();
        }finally{
            productService.releasePC();
        }
        

    }
}
