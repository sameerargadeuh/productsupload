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
@Table(name = "tblproductbrand")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tblproductbrand.findAll", query = "SELECT t FROM Tblproductbrand t"),
    @NamedQuery(name = "Tblproductbrand.findByIID", query = "SELECT t FROM Tblproductbrand t WHERE t.iID = :iID"),
    @NamedQuery(name = "Tblproductbrand.findByVDir", query = "SELECT t FROM Tblproductbrand t WHERE t.vDir = :vDir"),
    @NamedQuery(name = "Tblproductbrand.findByVName", query = "SELECT t FROM Tblproductbrand t WHERE t.vName = :vName"),
    @NamedQuery(name = "Tblproductbrand.findByVWebsite", query = "SELECT t FROM Tblproductbrand t WHERE t.vWebsite = :vWebsite"),
    @NamedQuery(name = "Tblproductbrand.findByVLogoFilename", query = "SELECT t FROM Tblproductbrand t WHERE t.vLogoFilename = :vLogoFilename"),
    @NamedQuery(name = "Tblproductbrand.findByBActive", query = "SELECT t FROM Tblproductbrand t WHERE t.bActive = :bActive"),
    @NamedQuery(name = "Tblproductbrand.findByBPractitionerOnly", query = "SELECT t FROM Tblproductbrand t WHERE t.bPractitionerOnly = :bPractitionerOnly"),
    @NamedQuery(name = "Tblproductbrand.findByICategoryID", query = "SELECT t FROM Tblproductbrand t WHERE t.iCategoryID = :iCategoryID"),
    @NamedQuery(name = "Tblproductbrand.findByVSize", query = "SELECT t FROM Tblproductbrand t WHERE t.vSize = :vSize"),
    @NamedQuery(name = "Tblproductbrand.findByVQtyUnit", query = "SELECT t FROM Tblproductbrand t WHERE t.vQtyUnit = :vQtyUnit"),
    @NamedQuery(name = "Tblproductbrand.findByIStorageTempID", query = "SELECT t FROM Tblproductbrand t WHERE t.iStorageTempID = :iStorageTempID"),
    @NamedQuery(name = "Tblproductbrand.findByIStorageInfoID", query = "SELECT t FROM Tblproductbrand t WHERE t.iStorageInfoID = :iStorageInfoID"),
    @NamedQuery(name = "Tblproductbrand.findByVCompanyName", query = "SELECT t FROM Tblproductbrand t WHERE t.vCompanyName = :vCompanyName"),
    @NamedQuery(name = "Tblproductbrand.findByVContactName", query = "SELECT t FROM Tblproductbrand t WHERE t.vContactName = :vContactName"),
    @NamedQuery(name = "Tblproductbrand.findByVContactPosition", query = "SELECT t FROM Tblproductbrand t WHERE t.vContactPosition = :vContactPosition"),
    @NamedQuery(name = "Tblproductbrand.findByVAddressLine1", query = "SELECT t FROM Tblproductbrand t WHERE t.vAddressLine1 = :vAddressLine1"),
    @NamedQuery(name = "Tblproductbrand.findByVAddressLine2", query = "SELECT t FROM Tblproductbrand t WHERE t.vAddressLine2 = :vAddressLine2"),
    @NamedQuery(name = "Tblproductbrand.findByVSuburb", query = "SELECT t FROM Tblproductbrand t WHERE t.vSuburb = :vSuburb"),
    @NamedQuery(name = "Tblproductbrand.findByVState", query = "SELECT t FROM Tblproductbrand t WHERE t.vState = :vState"),
    @NamedQuery(name = "Tblproductbrand.findByVPostcode", query = "SELECT t FROM Tblproductbrand t WHERE t.vPostcode = :vPostcode"),
    @NamedQuery(name = "Tblproductbrand.findByVPhone", query = "SELECT t FROM Tblproductbrand t WHERE t.vPhone = :vPhone"),
    @NamedQuery(name = "Tblproductbrand.findByVFax", query = "SELECT t FROM Tblproductbrand t WHERE t.vFax = :vFax"),
    @NamedQuery(name = "Tblproductbrand.findByVEmail", query = "SELECT t FROM Tblproductbrand t WHERE t.vEmail = :vEmail"),
    @NamedQuery(name = "Tblproductbrand.findByVNumProducts", query = "SELECT t FROM Tblproductbrand t WHERE t.vNumProducts = :vNumProducts")})
public class Tblproductbrand implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iID")
    private Integer iID;
    @Column(name = "vDir")
    private String vDir;
    @Basic(optional = false)
    @Column(name = "vName")
    private String vName;
    @Column(name = "vWebsite")
    private String vWebsite;
    @Column(name = "vLogoFilename")
    private String vLogoFilename;
    @Basic(optional = false)
    @Column(name = "bActive")
    private int bActive;
    @Basic(optional = false)
    @Column(name = "bPractitionerOnly")
    private int bPractitionerOnly;
    @Column(name = "iCategoryID")
    private Integer iCategoryID;
    @Column(name = "vSize")
    private String vSize;
    @Column(name = "vQtyUnit")
    private String vQtyUnit;
    @Column(name = "iStorageTempID")
    private Integer iStorageTempID;
    @Column(name = "iStorageInfoID")
    private Integer iStorageInfoID;
    @Column(name = "vCompanyName")
    private String vCompanyName;
    @Column(name = "vContactName")
    private String vContactName;
    @Column(name = "vContactPosition")
    private String vContactPosition;
    @Column(name = "vAddressLine1")
    private String vAddressLine1;
    @Column(name = "vAddressLine2")
    private String vAddressLine2;
    @Column(name = "vSuburb")
    private String vSuburb;
    @Column(name = "vState")
    private String vState;
    @Column(name = "vPostcode")
    private String vPostcode;
    @Column(name = "vPhone")
    private String vPhone;
    @Column(name = "vFax")
    private String vFax;
    @Column(name = "vEmail")
    private String vEmail;
    @Column(name = "vNumProducts")
    private String vNumProducts;

    public Tblproductbrand() {
    }

    public Tblproductbrand(Integer iID) {
        this.iID = iID;
    }

    public Tblproductbrand(Integer iID, String vName, int bActive, int bPractitionerOnly) {
        this.iID = iID;
        this.vName = vName;
        this.bActive = bActive;
        this.bPractitionerOnly = bPractitionerOnly;
    }

    public Integer getIID() {
        return iID;
    }

    public void setIID(Integer iID) {
        this.iID = iID;
    }

    public String getVDir() {
        return vDir;
    }

    public void setVDir(String vDir) {
        this.vDir = vDir;
    }

    public String getVName() {
        return vName;
    }

    public void setVName(String vName) {
        this.vName = vName;
    }

    public String getVWebsite() {
        return vWebsite;
    }

    public void setVWebsite(String vWebsite) {
        this.vWebsite = vWebsite;
    }

    public String getVLogoFilename() {
        return vLogoFilename;
    }

    public void setVLogoFilename(String vLogoFilename) {
        this.vLogoFilename = vLogoFilename;
    }

    public int getBActive() {
        return bActive;
    }

    public void setBActive(int bActive) {
        this.bActive = bActive;
    }

    public int getBPractitionerOnly() {
        return bPractitionerOnly;
    }

    public void setBPractitionerOnly(int bPractitionerOnly) {
        this.bPractitionerOnly = bPractitionerOnly;
    }

    public Integer getICategoryID() {
        return iCategoryID;
    }

    public void setICategoryID(Integer iCategoryID) {
        this.iCategoryID = iCategoryID;
    }

    public String getVSize() {
        return vSize;
    }

    public void setVSize(String vSize) {
        this.vSize = vSize;
    }

    public String getVQtyUnit() {
        return vQtyUnit;
    }

    public void setVQtyUnit(String vQtyUnit) {
        this.vQtyUnit = vQtyUnit;
    }

    public Integer getIStorageTempID() {
        return iStorageTempID;
    }

    public void setIStorageTempID(Integer iStorageTempID) {
        this.iStorageTempID = iStorageTempID;
    }

    public Integer getIStorageInfoID() {
        return iStorageInfoID;
    }

    public void setIStorageInfoID(Integer iStorageInfoID) {
        this.iStorageInfoID = iStorageInfoID;
    }

    public String getVCompanyName() {
        return vCompanyName;
    }

    public void setVCompanyName(String vCompanyName) {
        this.vCompanyName = vCompanyName;
    }

    public String getVContactName() {
        return vContactName;
    }

    public void setVContactName(String vContactName) {
        this.vContactName = vContactName;
    }

    public String getVContactPosition() {
        return vContactPosition;
    }

    public void setVContactPosition(String vContactPosition) {
        this.vContactPosition = vContactPosition;
    }

    public String getVAddressLine1() {
        return vAddressLine1;
    }

    public void setVAddressLine1(String vAddressLine1) {
        this.vAddressLine1 = vAddressLine1;
    }

    public String getVAddressLine2() {
        return vAddressLine2;
    }

    public void setVAddressLine2(String vAddressLine2) {
        this.vAddressLine2 = vAddressLine2;
    }

    public String getVSuburb() {
        return vSuburb;
    }

    public void setVSuburb(String vSuburb) {
        this.vSuburb = vSuburb;
    }

    public String getVState() {
        return vState;
    }

    public void setVState(String vState) {
        this.vState = vState;
    }

    public String getVPostcode() {
        return vPostcode;
    }

    public void setVPostcode(String vPostcode) {
        this.vPostcode = vPostcode;
    }

    public String getVPhone() {
        return vPhone;
    }

    public void setVPhone(String vPhone) {
        this.vPhone = vPhone;
    }

    public String getVFax() {
        return vFax;
    }

    public void setVFax(String vFax) {
        this.vFax = vFax;
    }

    public String getVEmail() {
        return vEmail;
    }

    public void setVEmail(String vEmail) {
        this.vEmail = vEmail;
    }

    public String getVNumProducts() {
        return vNumProducts;
    }

    public void setVNumProducts(String vNumProducts) {
        this.vNumProducts = vNumProducts;
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
        if (!(object instanceof Tblproductbrand)) {
            return false;
        }
        Tblproductbrand other = (Tblproductbrand) object;
        if ((this.iID == null && other.iID != null) || (this.iID != null && !this.iID.equals(other.iID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unityhealth.model.Tblproductbrand[ iID=" + iID + " ]";
    }
    
}
