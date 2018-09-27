/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.permisnova.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author vanbritt
 */
@Entity
@Table(name = "autorisation_document")
public class AutorisationDocument implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 254)
    @Column(name = "label")
    private String label;
    @Column(name = "recto")
    private Short recto;
    @Column(name = "verso")
    private Short verso;
    @Column(name = "validityenddate")
    @Temporal(TemporalType.DATE)
    private Date validityenddate;
    @Column(name = "reneweddate")
    @Temporal(TemporalType.DATE)
    private Date reneweddate;
    @OneToMany(mappedBy = "autorisationDocument", fetch = FetchType.LAZY)
    private List<AppUser> monitorList;

    public AutorisationDocument() {
    }

    public AutorisationDocument(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Short getRecto() {
        return recto;
    }

    public void setRecto(Short recto) {
        this.recto = recto;
    }

    public Short getVerso() {
        return verso;
    }

    public void setVerso(Short verso) {
        this.verso = verso;
    }

    public Date getValidityenddate() {
        return validityenddate;
    }

    public void setValidityenddate(Date validityenddate) {
        this.validityenddate = validityenddate;
    }

    public Date getReneweddate() {
        return reneweddate;
    }

    public void setReneweddate(Date reneweddate) {
        this.reneweddate = reneweddate;
    }

    public List<AppUser> getonitorList() {
        return monitorList;
    }

    public void setAppUserList(List<AppUser> monitorList) {
        this.monitorList = monitorList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AutorisationDocument)) {
            return false;
        }
        AutorisationDocument other = (AutorisationDocument) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.test.entities.AutorisationDocument[ id=" + id + " ]";
    }
    
}
