/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unityhealth.daoimpl;

import com.unityhealth.dao.IProductStorageInfoDao;
import com.unityhealth.model.Tblproductstorageinfo;

/**
 *
 * @author Sameer S Argade
 */
public class ProductStorageInfoDaoImpl extends GenericDaoImpl<Tblproductstorageinfo> implements IProductStorageInfoDao{
     public ProductStorageInfoDaoImpl(){
         super(Tblproductstorageinfo.class);
    }
}