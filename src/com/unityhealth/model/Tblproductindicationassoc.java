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
 * @author Sameer S Argade
 */
@Entity
@Table(name = "tblproductindicationassoc")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tblproductindicationassoc.findAll", query = "SELECT t FROM Tblproductindicationassoc t")
    , @NamedQuery(name = "Tblproductindicationassoc.findByIID", query = "SELECT t FROM Tblproductindicationassoc t WHERE t.iID = :iID")
    , @NamedQuery(name = "Tblproductindicationassoc.findByIProductID", query = "SELECT t FROM Tblproductindicationassoc t WHERE t.iProductID = :iProductID")
    , @NamedQuery(name = "Tblproductindicationassoc.findByIIndicationID", query = "SELECT t FROM Tblproductindicationassoc t WHERE t.iIndicationID = :iIndicationID")
 ,@NamedQuery(name = "Tblproductindicationassoc.findByIProductIDIIndicationID", query = "SELECT t FROM Tblproductindicationassoc t WHERE t.iProductID = :iProductID AND t.iIndicationID = :iIndicationID")})
public class Tblproductindicationassoc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iID")
    private Integer iID;
    @Column(name = "iProductID")
    private Integer iProductID;
    @Column(name = "iIndicationID")
    private Integer iIndicationID;

    public Tblproductindicationassoc() {
    }

    public Tblproductindicationassoc(Integer iID) {
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

    public Integer getIIndicationID() {
        return iIndicationID;
    }

    public void setIIndicationID(Integer iIndicationID) {
        this.iIndicationID = iIndicationID;
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
        if (!(object instanceof Tblproductindicationassoc)) {
            return false;
        }
        Tblproductindicationassoc other = (Tblproductindicationassoc) object;
        if ((this.iID == null && other.iID != null) || (this.iID != null && !this.iID.equals(other.iID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unityhealth.model.Tblproductindicationassoc[ iID=" + iID + " ]";
    }
    
}
