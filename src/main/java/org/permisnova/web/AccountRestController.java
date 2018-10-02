/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.permisnova.web;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import org.permisnova.entities.AppUser;
import org.permisnova.entities.HTMLMail;
import org.permisnova.sevice.AccountService;
import org.permisnova.sevice.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
        
        String  test=strDate.replaceAll("-", "").replaceAll(" ", "").replaceAll(":","").replaceAll(".com", "");
                user.setPassword(test);
        
        
        try {
            mailSenderService.sendHTMLMailAttachment(new HTMLMail(user.getEmail()),user.getFirstname(),user.getLastname(), test);
        } catch (MessagingException ex) {
            Logger.getLogger(AccountRestController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error while sending password to email");
                        user.setRegisterDate(new Date());

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
        String test=strDate.replaceAll("-", "").replaceAll(" ", "").replaceAll(":","").replaceAll(".com", "");
                user.setPassword(test);
                System.out.println(test);

                
        try {
            mailSenderService.sendHTMLMailAttachment(new HTMLMail(user.getEmail()),user.getFirstname(),user.getLastname(), test);
            user.setRegisterDate(new Date());
        } catch (MessagingException ex) {
            Logger.getLogger(AccountRestController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error while sending password to email");
        }
        
        accountService.saveUser(user);
        accountService.addRoleToUser(registerForm.getEmail(), "MONITOR");
        
        return user;
    }
    
    @PostMapping("/user/profile")
    public AppUser updateProfile(@RequestParam("file")MultipartFile file) throws IOException{
           Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
        
        AppUser appUser= accountService.findUserByEmailAndStatus(auth.getName(),true);  
        
        appUser.setProfile(file.getBytes());
        appUser.setProfileName(file.getOriginalFilename());
        
        accountService.saveUser(appUser);
        
        return appUser;
    }
    
      @GetMapping("/user/connected")
    public AppUser updateProfile() {
           Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
        
        AppUser appUser= accountService.findUserByEmailAndStatus(auth.getName(),true);  
        
          System.out.println(appUser);
        return appUser;
    }
    
    @PostMapping("/user/reset")
    public AppUser resetPassword(@RequestBody ResetPassword resetPassword){
        
          Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
             AppUser appUser= accountService.findUserByEmailAndStatus(auth.getName(),true);

                        
       if(BCrypt.checkpw(resetPassword.getOldpassword(), appUser.getPassword())){
        if((resetPassword.getNewpassword().equals(resetPassword.getRenewpassword()))){
            appUser.setPassword(resetPassword.getNewpassword());
            accountService.saveUser(appUser);
            System.out.println("cool ok");
             return appUser;    
        }
       }
        
        return null;
    }
    
    @GetMapping("/user/monitor")
    public List<AppUser> findMonitors(){
        
        return accountService.findUserByRole("MONITOR");
    }
    
     @GetMapping("/user/student")
    public List<AppUser> findStudents(){
       
        return accountService.findUserByRole("STUDENT");
    }

}
