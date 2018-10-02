/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.permisnova.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Basic;
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
import javax.persistence.Table;

/**
 *
 * @author vanbritt
 */
@Entity
@Table(name = "my_bundle")
public class MyBundle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "total_credit")
    private Integer totalCredit;
    @Column(name = "remaining_credit")
    private Integer remainingCredit;
     @Column(name = "use_credit")
    private Integer useCredit;
    @Column(name = "status")
    private Boolean status;
    @JoinColumn(name = "bundle", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
     private Bundle bundle;
    
    @JoinColumn(name = "student", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AppUser student;

    public MyBundle() {
    }

    public MyBundle(Integer id) {
        this.id = id;
    }

    public Integer getUseCredit() {
        return useCredit;
    }

    public void setUseCredit(Integer useCredit) {
        this.useCredit = useCredit;
    }
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTotalCredit() {
        return totalCredit;
    }

    public void setTotalCredit(Integer totalCredit) {
        this.totalCredit = totalCredit;
    }

    public Integer getRemainingCredit() {
        return remainingCredit;
    }

    public void setRemainingCredit(Integer remainingCredit) {
        this.remainingCredit = remainingCredit;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
@JsonIgnore
    public Bundle getBundle() {
        return bundle;
    }

    
    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }
@JsonIgnore
    public AppUser getAppUser() {
        return student;
    }

    public void setAppUser(AppUser student) {
        this.student = student;
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
        if (!(object instanceof MyBundle)) {
            return false;
        }
        MyBundle other = (MyBundle) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.test.entities.MyBundle[ id=" + id + " ]";
    }
    
}
