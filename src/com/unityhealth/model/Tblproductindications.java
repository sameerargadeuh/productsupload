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
@Table(name = "tblproductindications")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tblproductindications.findAll", query = "SELECT t FROM Tblproductindications t")
    , @NamedQuery(name = "Tblproductindications.findByIID", query = "SELECT t FROM Tblproductindications t WHERE t.iID = :iID")
    , @NamedQuery(name = "Tblproductindications.findByVCode", query = "SELECT t FROM Tblproductindications t WHERE t.vCode = :vCode")
    , @NamedQuery(name = "Tblproductindications.findByVDesc", query = "SELECT t FROM Tblproductindications t WHERE t.vDesc in :vDesc")
    , @NamedQuery(name = "Tblproductindications.findByVComments", query = "SELECT t FROM Tblproductindications t WHERE t.vComments = :vComments")
    , @NamedQuery(name = "Tblproductindications.findByBActive", query = "SELECT t FROM Tblproductindications t WHERE t.bActive = :bActive")
    , @NamedQuery(name = "Tblproductindications.findByBCustom", query = "SELECT t FROM Tblproductindications t WHERE t.bCustom = :bCustom")
    , @NamedQuery(name = "Tblproductindications.findByVCustomBy", query = "SELECT t FROM Tblproductindications t WHERE t.vCustomBy = :vCustomBy")})
public class Tblproductindications implements Serializable {

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
    @Column(name = "vComments")
    private String vComments;
    @Basic(optional = false)
    @Column(name = "bActive")
    private boolean bActive;
    @Column(name = "bCustom")
    private Boolean bCustom;
    @Column(name = "vCustomBy")
    private String vCustomBy;

    public Tblproductindications() {
    }

    public Tblproductindications(Integer iID) {
        this.iID = iID;
    }

    public Tblproductindications(Integer iID, boolean bActive) {
        this.iID = iID;
        this.bActive = bActive;
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

    public String getVComments() {
        return vComments;
    }

    public void setVComments(String vComments) {
        this.vComments = vComments;
    }

    public boolean getBActive() {
        return bActive;
    }

    public void setBActive(boolean bActive) {
        this.bActive = bActive;
    }

    public Boolean getBCustom() {
        return bCustom;
    }

    public void setBCustom(Boolean bCustom) {
        this.bCustom = bCustom;
    }

    public String getVCustomBy() {
        return vCustomBy;
    }

    public void setVCustomBy(String vCustomBy) {
        this.vCustomBy = vCustomBy;
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
        if (!(object instanceof Tblproductindications)) {
            return false;
        }
        Tblproductindications other = (Tblproductindications) object;
        if ((this.iID == null && other.iID != null) || (this.iID != null && !this.iID.equals(other.iID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unityhealth.model.Tblproductindications[ iID=" + iID + " ]";
    }
    
}
