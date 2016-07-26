/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unityhealth.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Clinton
 */
@Entity
@Table(name = "tblproductingredientassoctemp")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tblproductingredientassoctemp.findAll", query = "SELECT t FROM Tblproductingredientassoctemp t"),
    @NamedQuery(name = "Tblproductingredientassoctemp.findByIID", query = "SELECT t FROM Tblproductingredientassoctemp t WHERE t.iID = :iID"),
    @NamedQuery(name = "Tblproductingredientassoctemp.findByIProductID", query = "SELECT t FROM Tblproductingredientassoctemp t WHERE t.iProductID = :iProductID"),
    @NamedQuery(name = "Tblproductingredientassoctemp.findByIIngredientID", query = "SELECT t FROM Tblproductingredientassoctemp t WHERE t.iIngredientID = :iIngredientID"),
    @NamedQuery(name = "Tblproductingredientassoctemp.findByVQty", query = "SELECT t FROM Tblproductingredientassoctemp t WHERE t.vQty = :vQty"),
    @NamedQuery(name = "Tblproductingredientassoctemp.findByVQtyUnit", query = "SELECT t FROM Tblproductingredientassoctemp t WHERE t.vQtyUnit = :vQtyUnit"),
    @NamedQuery(name = "Tblproductingredientassoctemp.findByIParentID", query = "SELECT t FROM Tblproductingredientassoctemp t WHERE t.iParentID = :iParentID"),
    @NamedQuery(name = "Tblproductingredientassoctemp.findByIBrandID", query = "SELECT t FROM Tblproductingredientassoctemp t WHERE t.iBrandID = :iBrandID")})
public class Tblproductingredientassoctemp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iID")
    private Integer iID;
    @Column(name = "iProductID")
    private Integer iProductID;
    @Column(name = "iIngredientID")
    private Integer iIngredientID;
    @Column(name = "vQty")
    private String vQty;
    @Column(name = "vQtyUnit")
    private String vQtyUnit;
    @Column(name = "iParentID")
    private Integer iParentID;
    @Column(name = "iBrandID")
    private Integer iBrandID;

    public Tblproductingredientassoctemp() {
    }

    public Tblproductingredientassoctemp(Integer iID) {
        this.iID = iID;
    }

    public Integer getIID() {
        return iID;
    }

    public void setIID(Integer iID) {
        this.iID = iID;
    }

    public Integer getIProductID() {
        return iProductID;
    }

    public void setIProductID(Integer iProductID) {
        this.iProductID = iProductID;
    }

    public Integer getIIngredientID() {
        return iIngredientID;
    }

    public void setIIngredientID(Integer iIngredientID) {
        this.iIngredientID = iIngredientID;
    }

    public String getVQty() {
        return vQty;
    }

    public void setVQty(String vQty) {
        this.vQty = vQty;
    }

    public String getVQtyUnit() {
        return vQtyUnit;
    }

    public void setVQtyUnit(String vQtyUnit) {
        this.vQtyUnit = vQtyUnit;
    }

    public Integer getIParentID() {
        return iParentID;
    }

    public void setIParentID(Integer iParentID) {
        this.iParentID = iParentID;
    }

    public Integer getIBrandID() {
        return iBrandID;
    }

    public void setIBrandID(Integer iBrandID) {
        this.iBrandID = iBrandID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iID != null ? iID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tblproductingredientassoctemp)) {
            return false;
        }
        Tblproductingredientassoctemp other = (Tblproductingredientassoctemp) object;
        if ((this.iID == null && other.iID != null) || (this.iID != null && !this.iID.equals(other.iID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unityhealth.model.Tblproductingredientassoctemp[ iID=" + iID + " ]";
    }
    
}
