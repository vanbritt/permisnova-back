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
    @Column(name = "files")
    private Short files;
    @Column(name = "upload_date")
    @Temporal(TemporalType.DATE)
    private Date uploadDate;
    @Column(name = "description")
    @Temporal(TemporalType.DATE)
    private Date description;
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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Short getFiles() {
        return files;
    }

    public void setFiles(Short files) {
        this.files = files;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public Date getDescription() {
        return description;
    }

    public void setDescription(Date description) {
        this.description = description;
    }

    public List<MyCourseMaterial> getMyCourseMaterialList() {
        return myCourseMaterialList;
    }

    public void setMyCourseMaterialList(List<MyCourseMaterial> myCourseMaterialList) {
        this.myCourseMaterialList = myCourseMaterialList;
    }

    public AppUser getAppUser() {
        return monitor;
    }

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
