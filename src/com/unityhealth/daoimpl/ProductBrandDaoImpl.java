/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unityhealth.daoimpl;

import com.unityhealth.dao.IProductBrandDao;
import com.unityhealth.model.Tblproductbrand;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.TypedQuery;

/**
 *
 * @author Clinton
 */
public class ProductBrandDaoImpl extends GenericDaoImpl<Tblproductbrand> implements IProductBrandDao {

    public ProductBrandDaoImpl() {
        // TODO Auto-generated constructor stub
        super(Tblproductbrand.class);
    }

    

}
