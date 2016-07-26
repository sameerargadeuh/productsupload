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
 * @author Sameer
 */
@Entity
@Table(name = "tblproductwarningassoc")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tblproductwarningassoc.findAll", query = "SELECT t FROM Tblproductwarningassoc t"),
    @NamedQuery(name = "Tblproductwarningassoc.findByIID", query = "SELECT t FROM Tblproductwarningassoc t WHERE t.iID = :iID"),
    @NamedQuery(name = "Tblproductwarningassoc.findByIProductID", query = "SELECT t FROM Tblproductwarningassoc t WHERE t.iProductID = :iProductID"),
    @NamedQuery(name = "Tblproductwarningassoc.findByIWarningCodeID", query = "SELECT t FROM Tblproductwarningassoc t WHERE t.iWarningCodeID = :iWarningCodeID"),
    @NamedQuery(name = "Tblproductwarningassoc.findByIProductIDIWarningCodeID", query = "SELECT t FROM Tblproductwarningassoc t WHERE t.iProductID = :iProductID AND t.iWarningCodeID = :iWarningCodeID")})
public class Tblproductwarningassoc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iID")
    private Integer iID;
    @Basic(optional = false)
    @Column(name = "iProductID")
    private int iProductID;
    @Basic(optional = false)
    @Column(name = "iWarningCodeID")
    private int iWarningCodeID;

    public Tblproductwarningassoc() {
    }

    public Tblproductwarningassoc(Integer iID) {
        this.iID = iID;
    }

    public Tblproductwarningassoc(Integer iID, int iProductID, int iWarningCodeID) {
        this.iID = iID;
        this.iProductID = iProductID;
        this.iWarningCodeID = iWarningCodeID;
    }

    public Integer getIID() {
        return iID;
    }

    public void setIID(Integer iID) {
        this.iID = iID;
    }

    public int getIProductID() {
        return iProductID;
    }

    public void setIProductID(int iProductID) {
        this.iProductID = iProductID;
    }

    public int getIWarningCodeID() {
        return iWarningCodeID;
    }

    public void setIWarningCodeID(int iWarningCodeID) {
        this.iWarningCodeID = iWarningCodeID;
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
        if (!(object instanceof Tblproductwarningassoc)) {
            return false;
        }
        Tblproductwarningassoc other = (Tblproductwarningassoc) object;
        if ((this.iID == null && other.iID != null) || (this.iID != null && !this.iID.equals(other.iID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unityhealth.model.Tblproductwarningassoc[ iID=" + iID + " ]";
    }
    
}
