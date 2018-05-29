/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unityhealth.daoimpl;

import com.unityhealth.dao.IProductStorageTempDao;
import com.unityhealth.model.Tblproductstoragetemp;

/**
 *
 * @author Sameer S Argade
 */
public class ProductStorageTempDaoImpl  extends GenericDaoImpl<Tblproductstoragetemp> implements IProductStorageTempDao{
     public ProductStorageTempDaoImpl(){
         super(Tblproductstoragetemp.class);
    }
}
