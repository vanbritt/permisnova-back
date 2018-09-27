/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.permisnova.entities;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "enterprise_document")
public class EnterpriseDocument implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 254)
    @Column(name = "kbis")
    private String kbis;
    @Size(max = 254)
    @Column(name = "social_denomination")
    private String socialDenomination;
    @Size(max = 254)
    @Column(name = "company_addresse")
    private String companyAddresse;
    @Column(name = "vat_number")
    private BigInteger vatNumber;
    @Size(max = 254)
    @Column(name = "legal_structure")
    private String legalStructure;
    @Size(max = 254)
    @Column(name = "vigilance_attestation")
    private String vigilanceAttestation;
    @OneToMany(mappedBy = "enterpriseDocument", fetch = FetchType.LAZY)
    private List<AppUser> monitorList;

    public EnterpriseDocument() {
    }

    public EnterpriseDocument(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKbis() {
        return kbis;
    }

    public void setKbis(String kbis) {
        this.kbis = kbis;
    }

    public String getSocialDenomination() {
        return socialDenomination;
    }

    public void setSocialDenomination(String socialDenomination) {
        this.socialDenomination = socialDenomination;
    }

    public String getCompanyAddresse() {
        return companyAddresse;
    }

    public void setCompanyAddresse(String companyAddresse) {
        this.companyAddresse = companyAddresse;
    }

    public BigInteger getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(BigInteger vatNumber) {
        this.vatNumber = vatNumber;
    }

    public String getLegalStructure() {
        return legalStructure;
    }

    public void setLegalStructure(String legalStructure) {
        this.legalStructure = legalStructure;
    }

    public String getVigilanceAttestation() {
        return vigilanceAttestation;
    }

    public void setVigilanceAttestation(String vigilanceAttestation) {
        this.vigilanceAttestation = vigilanceAttestation;
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
        if (!(object instanceof EnterpriseDocument)) {
            return false;
        }
        EnterpriseDocument other = (EnterpriseDocument) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.test.entities.EnterpriseDocument[ id=" + id + " ]";
    }
    
}
