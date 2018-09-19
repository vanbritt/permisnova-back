/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.permisnova.web;

/**
 *
 * @author vanbritt
 */
public class RegisterForm {
    
//   private String username;
   private String firstname;
   private String lastname;
   private String phone;
   private String department;
//   private String password;
//   private String rpassword;
private String email;

    public RegisterForm() {
    }

   
    public RegisterForm(String email) {
       this.email=email;
    }

    public RegisterForm(String firstname, String lastname, String phone, String department) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
   
    
   
    
}
