/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unityhealth.daoimpl;

import com.unityhealth.dao.IProductIndicationsAssocDao;
import com.unityhealth.model.Tblproductindicationassoc;

/**
 *
 * @author Sameer S Argade
 */
public class ProductIndicationsAssocDaoImpl extends GenericDaoImpl<Tblproductindicationassoc> implements IProductIndicationsAssocDao{
     public ProductIndicationsAssocDaoImpl(){
         super(Tblproductindicationassoc.class);
    }
}
