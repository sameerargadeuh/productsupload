/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unityhealth.service;

import com.unityhealth.model.BiocIngredientsModel;
import com.unityhealth.model.ProductModel;

/**
 *
 * @author Clinton
 */
public interface IProductService extends IUHService {
    public boolean saveProduct(ProductModel[] productJsonArray, Integer brandID);
    public boolean saveProduct(BiocIngredientsModel productJson,Integer brandID);
     public boolean updatewarnings(BiocIngredientsModel productJson,Integer brandID);
      public boolean updatewarnings(ProductModel[] productJsonArray,Integer brandID);
     public boolean updateIndications(ProductModel[] productJsonArray,Integer brandID);
}
