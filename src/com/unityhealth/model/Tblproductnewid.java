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
@Table(name = "tblproductnewid")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tblproductnewid.findAll", query = "SELECT t FROM Tblproductnewid t"),
    @NamedQuery(name = "Tblproductnewid.findByIID", query = "SELECT t FROM Tblproductnewid t WHERE t.iID = :iID"),
    @NamedQuery(name = "Tblproductnewid.updateNewID", query = "Update Tblproductnewid t SET t.iNewID = :iNewID"),
    @NamedQuery(name = "Tblproductnewid.findByINewID", query = "SELECT t FROM Tblproductnewid t WHERE t.iNewID = :iNewID")})
public class Tblproductnewid implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iID")
    private Integer iID;
    @Column(name = "iNewID")
    private Integer iNewID;

    public Tblproductnewid() {
    }

    public Tblproductnewid(Integer iID) {
        this.iID = iID;
    }

    public Integer getIID() {
        return iID;
    }

    public void setIID(Integer iID) {
        this.iID = iID;
    }

    public Integer getINewID() {
        return iNewID;
    }

    public void setINewID(Integer iNewID) {
        this.iNewID = iNewID;
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
        if (!(object instanceof Tblproductnewid)) {
            return false;
        }
        Tblproductnewid other = (Tblproductnewid) object;
        if ((this.iID == null && other.iID != null) || (this.iID != null && !this.iID.equals(other.iID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unityhealth.model.Tblproductnewid[ iID=" + iID + " ]";
    }
    
}
