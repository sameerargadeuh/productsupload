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
@Table(name = "tblproductstorageinfo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tblproductstorageinfo.findAll", query = "SELECT t FROM Tblproductstorageinfo t")
    , @NamedQuery(name = "Tblproductstorageinfo.findByIID", query = "SELECT t FROM Tblproductstorageinfo t WHERE t.iID = :iID")
    , @NamedQuery(name = "Tblproductstorageinfo.findByVCode", query = "SELECT t FROM Tblproductstorageinfo t WHERE t.vCode = :vCode")
    , @NamedQuery(name = "Tblproductstorageinfo.findByVDesc", query = "SELECT t FROM Tblproductstorageinfo t WHERE t.vDesc = :vDesc")})
public class Tblproductstorageinfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iID")
    private Integer iID;
    @Column(name = "vCode")
    private String vCode;
    @Column(name = "vDesc")
    private String vDesc;

    public Tblproductstorageinfo() {
    }

    public Tblproductstorageinfo(Integer iID) {
        this.iID = iID;
    }

    public Integer getIID() {
        return iID;
    }

    public void setIID(Integer iID) {
        this.iID = iID;
    }

    public String getVCode() {
        return vCode;
    }

    public void setVCode(String vCode) {
        this.vCode = vCode;
    }

    public String getVDesc() {
        return vDesc;
    }

    public void setVDesc(String vDesc) {
        this.vDesc = vDesc;
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
        if (!(object instanceof Tblproductstorageinfo)) {
            return false;
        }
        Tblproductstorageinfo other = (Tblproductstorageinfo) object;
        if ((this.iID == null && other.iID != null) || (this.iID != null && !this.iID.equals(other.iID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unityhealth.model.Tblproductstorageinfo[ iID=" + iID + " ]";
    }
    
}
