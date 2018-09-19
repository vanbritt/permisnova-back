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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author vanbritt
 */
@Entity
@Table(name = "monitor")
public class Monitor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "partnership_contract")
    private Short partnershipContract;
    @Column(name = "register_date")
    @Temporal(TemporalType.DATE)
    private Date registerDate;
    @Column(name = "status")
    private Boolean status;
    @JoinColumn(name = "account", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AppUser account;
   
//    @JoinColumn(name = "autorisation_document", referencedColumnName = "id")
//    @ManyToOne(fetch = FetchType.LAZY)
//    private AutorisationDocument autorisationDocument;
//    @JoinColumn(name = "bank_account", referencedColumnName = "id")
//    @ManyToOne(fetch = FetchType.LAZY)
//    private BankAccount bankAccount;
//    @JoinColumn(name = "enterprise_document", referencedColumnName = "id")
//    @ManyToOne(fetch = FetchType.LAZY)
//    private EnterpriseDocument enterpriseDocument;
//    @JoinColumn(name = "identity_document", referencedColumnName = "id")
//    @ManyToOne(fetch = FetchType.LAZY)
//    private IdentityDocument identityDocument;
//    @JoinColumn(name = "vehicle_document", referencedColumnName = "id")
//    @ManyToOne(fetch = FetchType.LAZY)
//    private VehicleDocument vehicleDocument;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "monitor", fetch = FetchType.LAZY)
//    private List<Disponibility> disponibilityList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "monitor", fetch = FetchType.LAZY)
    private List<Rendezvouslocation> rendezvouslocationList;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "monitor", fetch = FetchType.LAZY)
//    private List<CourseMaterial> courseMaterialList;

    public Monitor() {
    }

    public Monitor(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getPartnershipContract() {
        return partnershipContract;
    }

    public void setPartnershipContract(Short partnershipContract) {
        this.partnershipContract = partnershipContract;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public AppUser getAccount() {
        return account;
    }

    public void setAccount(AppUser account) {
        this.account = account;
    }
//
//    public AutorisationDocument getAutorisationDocument() {
//        return autorisationDocument;
//    }
//
//    public void setAutorisationDocument(AutorisationDocument autorisationDocument) {
//        this.autorisationDocument = autorisationDocument;
//    }
//
//    public BankAccount getBankAccount() {
//        return bankAccount;
//    }
//
//    public void setBankAccount(BankAccount bankAccount) {
//        this.bankAccount = bankAccount;
//    }
//
//    public EnterpriseDocument getEnterpriseDocument() {
//        return enterpriseDocument;
//    }
//
//    public void setEnterpriseDocument(EnterpriseDocument enterpriseDocument) {
//        this.enterpriseDocument = enterpriseDocument;
//    }
//
//    public IdentityDocument getIdentityDocument() {
//        return identityDocument;
//    }
//
//    public void setIdentityDocument(IdentityDocument identityDocument) {
//        this.identityDocument = identityDocument;
//    }
//
//    public VehicleDocument getVehicleDocument() {
//        return vehicleDocument;
//    }
//
//    public void setVehicleDocument(VehicleDocument vehicleDocument) {
//        this.vehicleDocument = vehicleDocument;
//    }
//
//    public List<Disponibility> getDisponibilityList() {
//        return disponibilityList;
//    }
//
//    public void setDisponibilityList(List<Disponibility> disponibilityList) {
//        this.disponibilityList = disponibilityList;
//    }
//
//    public List<Rendezvouslocation> getRendezvouslocationList() {
//        return rendezvouslocationList;
//    }
//
//    public void setRendezvouslocationList(List<Rendezvouslocation> rendezvouslocationList) {
//        this.rendezvouslocationList = rendezvouslocationList;
//    }
//
//    public List<CourseMaterial> getCourseMaterialList() {
//        return courseMaterialList;
//    }
//
//    public void setCourseMaterialList(List<CourseMaterial> courseMaterialList) {
//        this.courseMaterialList = courseMaterialList;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Monitor)) {
            return false;
        }
        Monitor other = (Monitor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.test.entities.Monitor[ id=" + id + " ]";
    }
    
}
