/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unityhealth.service;

import com.unityhealth.model.IngredientsModel;
import com.unityhealth.model.ProductModel;

/**
 *
 * @author Clinton
 */
public interface IProductIngredientsService {
     public boolean saveProductIngredients(IngredientsModel[] ingredientsJsonArray);
     public boolean updateProductIngredientsCommonNames(IngredientsModel[] ingredientsJsonArray);
}
