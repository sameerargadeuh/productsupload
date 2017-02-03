/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unityhealth.daoimpl;

import com.unityhealth.dao.IProductImagesDao;
import com.unityhealth.model.Tblproductimages;

/**
 *
 * @author Clinton
 */
public class ProductImagesDaoImpl extends GenericDaoImpl<Tblproductimages> implements IProductImagesDao {
    public ProductImagesDaoImpl(){
        super(Tblproductimages.class);
    }
     
}
