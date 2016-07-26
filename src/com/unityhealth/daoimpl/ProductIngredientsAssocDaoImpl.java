/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unityhealth.daoimpl;

import com.unityhealth.dao.IProductIngredientsAssocDao;
import com.unityhealth.model.Tblproductingredientassoc;

/**
 *
 * @author Clinton
 */
public class ProductIngredientsAssocDaoImpl extends GenericDaoImpl<Tblproductingredientassoc> implements IProductIngredientsAssocDao  {
    public ProductIngredientsAssocDaoImpl(){
        super(Tblproductingredientassoc.class);
    }
}
