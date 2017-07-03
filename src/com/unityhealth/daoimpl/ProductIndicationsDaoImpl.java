/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unityhealth.daoimpl;

import com.unityhealth.dao.IGenericDao;
import com.unityhealth.dao.IProductIndicationsDao;
import com.unityhealth.model.Tblproductindications;

/**
 *
 * @author Sameer S Argade
 */
public class ProductIndicationsDaoImpl extends GenericDaoImpl<Tblproductindications> implements IProductIndicationsDao{
    public ProductIndicationsDaoImpl(){
         super(Tblproductindications.class);
    }
}
