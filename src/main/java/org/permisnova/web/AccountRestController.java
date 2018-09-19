/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.permisnova.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import org.permisnova.entities.AppUser;
import org.permisnova.entities.HTMLMail;
import org.permisnova.sevice.AccountService;
import org.permisnova.sevice.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author vanbritt
 */
@CrossOrigin(origins = "*")
@RestController
public class AccountRestController {

    @Autowired
    private AccountService accountService;
    @Autowired 
    private MailSenderService mailSenderService;

    @PostMapping("/register/student")
    public AppUser registerStudent(@RequestBody RegisterForm registerForm) {
        AppUser user = new AppUser();

        AppUser exist = accountService.findUserByEmail(registerForm.getEmail());
        
        if (exist != null) {
            throw new RuntimeException("User with username: " + registerForm.getEmail()+ " already exists");
        }

//        user.setUsername(registerForm.getUsername());
//        user.setPassword(registerForm.getPassword());
        user.setFirstname(registerForm.getFirstname());
        user.setLastname(registerForm.getLastname());
        user.setEmail(registerForm.getEmail());
        user.setPhone(registerForm.getPhone());
        user.setDepartment(registerForm.getDepartment());
        user.setStatus(true);
        //password generation
        Date date= new Date();
        DateFormat dateFormat= new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate= user.getEmail() + dateFormat.format(date).trim();
        
        user.setPassword(strDate.replaceAll("-", "").replaceAll("/", ""));
        
        try {
            mailSenderService.sendHTMLMailAttachment(new HTMLMail(user.getEmail()),user.getFirstname(),user.getLastname(), strDate);
        } catch (MessagingException ex) {
            Logger.getLogger(AccountRestController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error while sending password to email");
        }
        
        accountService.saveUser(user);
        accountService.addRoleToUser(registerForm.getEmail(), "STUDENT");
        
        return user;
    }
    
      @PostMapping("/register/monitor")
    public AppUser registerMonitor(@RequestBody RegisterForm registerForm) {
        AppUser user = new AppUser();
//
//        if (!registerForm.getPassword().equals(registerForm.getRpassword())) {
//            throw new RuntimeException("Password validation incorrect");
//        }
        AppUser exist = accountService.findUserByEmail(registerForm.getEmail());
        
        if (exist != null) {
            throw new RuntimeException("User with email: " + registerForm.getEmail() + " already exists");
        }

//        user.setUsername(registerForm.getUsername());
        user.setFirstname(registerForm.getFirstname());
        user.setLastname(registerForm.getLastname());
        user.setEmail(registerForm.getEmail());
        user.setPhone(registerForm.getPhone());
        user.setDepartment(registerForm.getDepartment());
        user.setStatus(true);
        
           //password generation
        Date date= new Date();
        DateFormat dateFormat= new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate= user.getEmail() + dateFormat.format(date).trim();
        
        user.setPassword(strDate);
        
        try {
            mailSenderService.sendHTMLMailAttachment(new HTMLMail(user.getEmail()),user.getFirstname(),user.getLastname(), strDate);
        } catch (MessagingException ex) {
            Logger.getLogger(AccountRestController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error while sending password to email");
        }

        accountService.saveUser(user);
        accountService.addRoleToUser(registerForm.getEmail(), "MONITOR");
        
        return user;
    }

}
