/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unityhealth.daoimpl;

import com.unityhealth.dao.IProductWarningCodesDao;
import com.unityhealth.model.Tblproductwarningcodes;

/**
 *
 * @author Clinton
 */
public class ProductWarningCodesDaoImpl extends GenericDaoImpl<Tblproductwarningcodes> implements IProductWarningCodesDao{
    
    public ProductWarningCodesDaoImpl(){
         super(Tblproductwarningcodes.class);
    }
}
