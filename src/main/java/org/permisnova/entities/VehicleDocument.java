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
@Table(name = "vehicle_document")
public class VehicleDocument implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "gray_card")
    private Short grayCard;
    @Size(max = 254)
    @Column(name = "immatriculation")
    private String immatriculation;
    @Column(name = "first_immatriculation_date")
    @Temporal(TemporalType.DATE)
    private Date firstImmatriculationDate;
    @Column(name = "vehicle_assurance_attestation")
    private Short vehicleAssuranceAttestation;
    @Size(max = 254)
    @Column(name = "label")
    private String label;
    @OneToMany(mappedBy = "vehicleDocument", fetch = FetchType.LAZY)
    private List<AppUser> monitorList;

    public VehicleDocument() {
    }

    public VehicleDocument(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getGrayCard() {
        return grayCard;
    }

    public void setGrayCard(Short grayCard) {
        this.grayCard = grayCard;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public Date getFirstImmatriculationDate() {
        return firstImmatriculationDate;
    }

    public void setFirstImmatriculationDate(Date firstImmatriculationDate) {
        this.firstImmatriculationDate = firstImmatriculationDate;
    }

    public Short getVehicleAssuranceAttestation() {
        return vehicleAssuranceAttestation;
    }

    public void setVehicleAssuranceAttestation(Short vehicleAssuranceAttestation) {
        this.vehicleAssuranceAttestation = vehicleAssuranceAttestation;
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
        if (!(object instanceof VehicleDocument)) {
            return false;
        }
        VehicleDocument other = (VehicleDocument) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.test.entities.VehicleDocument[ id=" + id + " ]";
    }
    
}
