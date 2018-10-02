/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.permisnova.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author vanbritt
 */
@Entity
@Table(name = "disponibility")
public class Disponibility implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "start_time")
    @Temporal(TemporalType.TIME)
    private Date startTime;
    @Column(name = "end_time")
    @Temporal(TemporalType.TIME)
    private Date endTime;
    @Column(name = "number_hours")
    private Integer numberHours;
    @Column(name = "status")
    private Boolean status;

    @JoinColumn(name = "monitor", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private AppUser monitor;
    
    @JoinColumn(name = "location", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Rendezvouslocation location;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "disponibility", fetch = FetchType.LAZY)
//    private List<Reservation> reservationList;

    public Disponibility() {
    }

    public Disponibility(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public AppUser getMonitor() {
        return monitor;
    }

    public void setMonitor(AppUser monitor) {
        this.monitor = monitor;
    }

    public Rendezvouslocation getLocation() {
        return location;
    }

    public void setLocation(Rendezvouslocation location) {
        this.location = location;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    
    public void setDate(Date date) {
        this.date = date;
    }

    public Date getStartTime() {
        return startTime;
    }
    @JsonFormat(pattern="HH:mm")    
//    @JsonFormat(pattern="h:mm a")

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }
    @JsonFormat(pattern="HH:mm")
    public void setEndTime(Date endTime) {
       
        this.endTime = endTime;
    }

    public Integer getNumberHours() {
        return numberHours;
    }
    @JsonIgnore
    public void setNumberHours(Integer numberHours) {
        this.numberHours = numberHours;
    }

    public Boolean getStatus() {
        return status;
    }

    @JsonIgnore
    public void setStatus(Boolean status) {
        this.status = status;
    }

    @JsonProperty
    public AppUser  getAppUser() {
        return monitor;
    }
    
    @JsonIgnore
    public void setAppUser(AppUser monitor) {
        this.monitor = monitor;
    }

    
    public Rendezvouslocation getRendezvousLocation() {
        return location;
    }

    @JsonIgnore
    public void setRendezvousLocation(Rendezvouslocation rendezvousLocation) {
        this.location = rendezvousLocation;
    }
//
//    public List<Reservation> getReservationList() {
//        return reservationList;
//    }
//
//    public void setReservationList(List<Reservation> reservationList) {
//        this.reservationList = reservationList;
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
        if (!(object instanceof Disponibility)) {
            return false;
        }
        Disponibility other = (Disponibility) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.test.entities.Disponibility[ id=" + id + " ]";
    }
    
}
