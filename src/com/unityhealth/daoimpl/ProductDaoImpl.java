/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unityhealth.daoimpl;

import com.unityhealth.dao.IProductDao;
import com.unityhealth.model.Tblproduct;

/**
 *
 * @author Clinton
 */
public class ProductDaoImpl extends GenericDaoImpl<Tblproduct> implements IProductDao{

     
   public ProductDaoImpl() {
		// TODO Auto-generated constructor stub
		super(Tblproduct.class);
	}
  
}
