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
 * @author Sameer
 */
@Entity
@Table(name = "tblproductwarningcodes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tblproductwarningcodes.findAll", query = "SELECT t FROM Tblproductwarningcodes t"),
    @NamedQuery(name = "Tblproductwarningcodes.findByIID", query = "SELECT t FROM Tblproductwarningcodes t WHERE t.iID = :iID"),
    @NamedQuery(name = "Tblproductwarningcodes.findByVCode", query = "SELECT t FROM Tblproductwarningcodes t WHERE t.vCode = :vCode"),
    @NamedQuery(name = "Tblproductwarningcodes.findByBActive", query = "SELECT t FROM Tblproductwarningcodes t WHERE t.bActive = :bActive"),
    @NamedQuery(name = "Tblproductwarningcodes.findByBCustom", query = "SELECT t FROM Tblproductwarningcodes t WHERE t.bCustom = :bCustom"),
    @NamedQuery(name = "Tblproductwarningcodes.findByVCustomBy", query = "SELECT t FROM Tblproductwarningcodes t WHERE t.vCustomBy = :vCustomBy"),
    @NamedQuery(name = "Tblproductwarningcodes.findByVDescription", query = "SELECT t FROM Tblproductwarningcodes t WHERE t.vDescription in :vDescription")})
public class Tblproductwarningcodes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iID")
    private Integer iID;
    @Column(name = "vCode")
    private String vCode;
    @Lob
    @Column(name = "vDescription")
    private String vDescription;
    @Lob
    @Column(name = "vComment")
    private String vComment;
    @Basic(optional = false)
    @Column(name = "bActive")
    private boolean bActive;
    @Column(name = "bCustom")
    private Boolean bCustom;
    @Column(name = "vCustomBy")
    private String vCustomBy;

    public Tblproductwarningcodes() {
    }

    public Tblproductwarningcodes(Integer iID) {
        this.iID = iID;
    }

    public Tblproductwarningcodes(Integer iID, boolean bActive) {
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

    public String getVDescription() {
        return vDescription;
    }

    public void setVDescription(String vDescription) {
        this.vDescription = vDescription;
    }

    public String getVComment() {
        return vComment;
    }

    public void setVComment(String vComment) {
        this.vComment = vComment;
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
        if (!(object instanceof Tblproductwarningcodes)) {
            return false;
        }
        Tblproductwarningcodes other = (Tblproductwarningcodes) object;
        if ((this.iID == null && other.iID != null) || (this.iID != null && !this.iID.equals(other.iID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unityhealth.model.Tblproductwarningcodes[ iID=" + iID + " ]";
    }

}
