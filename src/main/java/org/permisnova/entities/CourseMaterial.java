/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.permisnova.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
import javax.validation.constraints.Size;

/**
 *
 * @author vanbritt
 */
@Entity
@Table(name = "course_material")
public class CourseMaterial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Size(max = 254)
    @Column(name = "label")
    private String label;
    
     @Size(max = 254)
    @Column(name = "category")
    private String category;

    @Size(max = 254)
    @Column(name = "filename")
    private String fileName;

    @Column(name = "upload_date")
    @Temporal(TemporalType.DATE)
    private Date uploadDate;

    @Column(name = "description")
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "courseMaterial", fetch = FetchType.LAZY)
    private List<MyCourseMaterial> myCourseMaterialList;
    @JoinColumn(name = "monitor", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AppUser monitor;

    public CourseMaterial() {
    }

    public CourseMaterial(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
 
    public AppUser getMonitor() {
        return monitor;
    }

    public void setMonitor(AppUser monitor) {
        this.monitor = monitor;
    }
    
    

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @JsonProperty
    public Date getUploadDate() {
        return uploadDate;
    }

    @JsonIgnore
    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    @JsonIgnore
    public List<MyCourseMaterial> getMyCourseMaterialList() {
        return myCourseMaterialList;
    }

    @JsonIgnore
    public void setMyCourseMaterialList(List<MyCourseMaterial> myCourseMaterialList) {
        this.myCourseMaterialList = myCourseMaterialList;
    }

    
    @JsonProperty
    public AppUser getAppUser() {
        return monitor;
    }
    
    
    @JsonIgnore
    public void setAppUser(AppUser monitor) {
        this.monitor = monitor;
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
        if (!(object instanceof CourseMaterial)) {
            return false;
        }
        CourseMaterial other = (CourseMaterial) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.test.entities.CourseMaterial[ id=" + id + " ]";
    }

}
