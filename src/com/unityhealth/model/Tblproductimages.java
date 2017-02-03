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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Clinton
 */
@Entity
@Table(name = "tblproductimages")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tblproductimages.findAll", query = "SELECT t FROM Tblproductimages t"),
    @NamedQuery(name = "Tblproductimages.findByIID", query = "SELECT t FROM Tblproductimages t WHERE t.iID = :iID"),
    @NamedQuery(name = "Tblproductimages.findByIBrandID", query = "SELECT t FROM Tblproductimages t WHERE t.iBrandID = :iBrandID"),
    @NamedQuery(name = "Tblproductimages.findByVFilename", query = "SELECT t FROM Tblproductimages t WHERE t.vFilename = :vFilename")})
public class Tblproductimages implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iID")
    private Integer iID;
    @Column(name = "iBrandID")
    private Integer iBrandID;
    @Column(name = "vFilename")
    private String vFilename;
    @Lob
    @Column(name = "vDesc")
    private byte[] vDesc;

    public Tblproductimages() {
    }

    public Tblproductimages(Integer iID) {
        this.iID = iID;
    }

    public Integer getIID() {
        return iID;
    }

    public void setIID(Integer iID) {
        this.iID = iID;
    }

    public Integer getIBrandID() {
        return iBrandID;
    }

    public void setIBrandID(Integer iBrandID) {
        this.iBrandID = iBrandID;
    }

    public String getVFilename() {
        return vFilename;
    }

    public void setVFilename(String vFilename) {
        this.vFilename = vFilename;
    }

    public byte[] getVDesc() {
        return vDesc;
    }

    public void setVDesc(byte[] vDesc) {
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
        if (!(object instanceof Tblproductimages)) {
            return false;
        }
        Tblproductimages other = (Tblproductimages) object;
        if ((this.iID == null && other.iID != null) || (this.iID != null && !this.iID.equals(other.iID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unityhealth.model.Tblproductimages[ iID=" + iID + " ]";
    }
    
}
