/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unityhealth.daoimpl;

import com.unityhealth.dao.IProductNewIDDao;
import com.unityhealth.model.Tblproductnewid;

/**
 *
 * @author Clinton
 */
public class ProductNewIDDaoImpl  extends GenericDaoImpl<Tblproductnewid> implements IProductNewIDDao{
 public ProductNewIDDaoImpl(){
     super(Tblproductnewid.class);
 }   
}
