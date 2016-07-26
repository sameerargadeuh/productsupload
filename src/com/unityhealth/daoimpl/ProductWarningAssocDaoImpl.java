/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unityhealth.daoimpl;

import com.unityhealth.dao.IProductWarningAssocDao;
import com.unityhealth.model.Tblproductwarningassoc;

/**
 *
 * @author Clinton
 */
public class ProductWarningAssocDaoImpl extends GenericDaoImpl<Tblproductwarningassoc> implements IProductWarningAssocDao{
    public ProductWarningAssocDaoImpl(){
        super(Tblproductwarningassoc.class);
    }
}
