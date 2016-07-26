/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unityhealth.daoimpl;

import com.unityhealth.dao.IProductIngredientsDao;
import com.unityhealth.model.Tblproductingredients;

/**
 *
 * @author Sameer
 */
public class ProductIngredientsDaoImpl extends GenericDaoImpl<Tblproductingredients> implements IProductIngredientsDao{
    
    public ProductIngredientsDaoImpl(){
        super(Tblproductingredients.class);
    }
    
}
