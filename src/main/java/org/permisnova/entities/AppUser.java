/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.permisnova.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author vanbritt
 */
@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore//use to avoid the recuperation of the password by json
    private String password;

    private String firstname;
    private boolean status;
    
    private String lastname;
    private String phone;
    @Column(unique = true)
    private String email;
    
    private byte[] profile;
    private String profileName;
    private String department;

    @Column(name = "register_date")
    @Temporal(TemporalType.DATE)
    private Date registerDate;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<AppRole> roles = new ArrayList<>();

 
  
       
    //monitor documents
       
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "monitor", fetch = FetchType.LAZY)
    private List<Rendezvouslocation> rendezvouslocationList;
    
       @OneToMany(cascade = CascadeType.ALL, mappedBy = "student", fetch = FetchType.LAZY)
    private List<MyBundle> myBundleList;
       
    @Column(name = "partnership_contract")
    private Short partnershipContract;
    @JoinColumn(name = "autorisation_document", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AutorisationDocument autorisationDocument;
    @JoinColumn(name = "bank_account", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private BankAccount bankAccount;
    @JoinColumn(name = "enterprise_document", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private EnterpriseDocument enterpriseDocument;
    @JoinColumn(name = "identity_document", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private IdentityDocument identityDocument;
    @JoinColumn(name = "vehicle_document", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private VehicleDocument vehicleDocument;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "monitor", fetch = FetchType.LAZY)
    private List<Disponibility> disponibilityList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "monitor", fetch = FetchType.LAZY)
    private List<CourseMaterial> courseMaterialList;
    
    
    //AppUser Information
    
   @Column(name = "formationcontract")
    private Short formationcontract;
   
   @OneToMany(cascade = CascadeType.ALL, mappedBy = "student", fetch = FetchType.LAZY)
    private List<MyCourseMaterial> myCourseMaterialList;
   
    @JoinColumn(name = "learning_booklet", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private LearningBooklet learningBooklet;
    
    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<Reservation> reservationList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student", fetch = FetchType.LAZY)
    private List<MyWebpack> myWebpackList;


    public AppUser() {

    }

    public AppUser(String email, String password) {
        this.email = email;
        this.password = password;
        this.status=true;
    }

    public byte[] getProfile() {
        return profile;
    }

    public void setProfile(byte[] profile) {
        this.profile = profile;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @JsonSetter
    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<AppRole> getRoles() {
        return roles;
    }

    public void setRoles(Collection<AppRole> roles) {
        this.roles = roles;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public List<Rendezvouslocation> getRendezvouslocationList() {
        return rendezvouslocationList;
    }

    public void setRendezvouslocationList(List<Rendezvouslocation> rendezvouslocationList) {
        this.rendezvouslocationList = rendezvouslocationList;
    }
    
    

}
