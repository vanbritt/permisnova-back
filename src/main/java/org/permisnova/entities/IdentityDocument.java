/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.permisnova.entities;

import java.io.Serializable;
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
import javax.validation.constraints.Size;

/**
 *
 * @author vanbritt
 */
@Entity
@Table(name = "identity_document")
public class IdentityDocument implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "recto")
    private Short recto;
    @Column(name = "verso")
    private Short verso;
    @Size(max = 254)
    @Column(name = "label")
    private String label;
    @OneToMany(mappedBy = "identityDocument", fetch = FetchType.LAZY)
    private List<AppUser> monitorList;

    public IdentityDocument() {
    }

    public IdentityDocument(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<AppUser> getAppUserList() {
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
        if (!(object instanceof IdentityDocument)) {
            return false;
        }
        IdentityDocument other = (IdentityDocument) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.test.entities.IdentityDocument[ id=" + id + " ]";
    }
    
}
