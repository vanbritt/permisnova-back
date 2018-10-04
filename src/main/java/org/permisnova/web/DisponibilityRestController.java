/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.permisnova.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import org.permisnova.entities.AppUser;
import org.permisnova.entities.Bundle;
import org.permisnova.entities.Disponibility;
import org.permisnova.entities.HTMLMail;
import org.permisnova.entities.MyBundle;
import org.permisnova.entities.Rendezvouslocation;
import org.permisnova.entities.Reservation;
import org.permisnova.sevice.AccountService;
import org.permisnova.sevice.BundleService;
import org.permisnova.sevice.DisponibilityService;
import org.permisnova.sevice.LocationService;
import org.permisnova.sevice.MailSenderService;
import org.permisnova.sevice.MyBundleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author vanbritt
 */
@RestController
@RequestMapping("/disponibility")
public class DisponibilityRestController {

    @Autowired
    private DisponibilityService disponibilityService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private LocationService locationService;
    @Autowired
    private MyBundleService myBundleService;

    @Autowired
    private BundleService bundleService;
    
        @Autowired
    private MailSenderService mailService;

    @GetMapping
    public List<Disponibility> findAll() {
        return disponibilityService.findAll();
    }

    @GetMapping("/monitor/{id}")
    public List<Disponibility> findByMonitor(@PathVariable Long id) {

        AppUser monitor = accountService.findByIdAndStatus(id, true);

        return disponibilityService.findDisponibilityByUser(monitor, true);
    }

    @PostMapping
    public Disponibility save(@RequestParam("disponibility") String disponibility, @RequestParam("location") String location) throws IOException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(location + " " + disponibility);

        Disponibility dispo = new ObjectMapper().readValue(disponibility, Disponibility.class);
        Rendezvouslocation loc = locationService.findByLocation(location);
        dispo.setLocation(loc);
        AppUser appUser = accountService.findUserByEmailAndStatus(auth.getName(), true);
        dispo.setAppUser(appUser);
        dispo.setStatus(true);
        return disponibilityService.save(dispo);
    }

    @GetMapping("/delete/{id}")
    public boolean delete(@PathVariable Integer id) {
        System.out.println("good");
        disponibilityService.delete(id);
        return true;
    }
    
    
    @GetMapping("/cancel/{id}")
    public  Reservation cancelReservation(@PathVariable int id) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        AppUser appUser = accountService.findUserByEmailAndStatus(auth.getName(), true);
                MyBundle myBundle = myBundleService.userBundle(appUser, "driving");
        Date today= new Date();
        
        
        Reservation reservation= disponibilityService.findReservationById(id);
        
        System.out.println(reservation);
        
        System.out.println(reservation.getDate());
        
        
        int diff= today.getHours()- reservation.getTime().getHours();

        int timediff= reservation.getDisponibility().getEndTime().getHours() - reservation.getDisponibility().getStartTime().getHours();
           


        if (diff < 48) {
            reservation.setState("cancel");
            myBundle.setRemainingCredit(myBundle.getRemainingCredit()+timediff);
            myBundle.setUseCredit(myBundle.getUseCredit()-timediff);
            reservation.getDisponibility().setStatus(Boolean.TRUE);
            disponibilityService.saveReservation(reservation);
            myBundleService.save(myBundle);

        }
        return reservation;
    }

    @GetMapping("/reserve/{id}")
    public MyBundle reserve(@PathVariable int id) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        AppUser appUser = accountService.findUserByEmailAndStatus(auth.getName(), true);
        Bundle bundle = bundleService.findByCode("driving");
        MyBundle myBundle = myBundleService.userBundle(appUser, "driving");

        boolean check = myBundleService.checkBundle(bundle, appUser);

        if (check) {
            Disponibility dispo = disponibilityService.findById(id);
            int difference = dispo.getEndTime().getHours() - dispo.getStartTime().getHours();

            myBundleService.removeCreditFromUserBundle(myBundle.getBundle(), appUser, difference);
            Reservation reservation = new Reservation();

            reservation.setAppUser(appUser);
            reservation.setDisponibility(dispo);
            reservation.setState("confirm");
            reservation.setDate(new Date());
            reservation.setTime(new Date());
            dispo.setStatus(false);
            HTMLMail  mail= new HTMLMail(dispo.getMonitor().getEmail());
            
            try {
                mailService.sendHTMLMailAttachmentReservation(mail, appUser, reservation);
            } catch (MessagingException ex) {
                    throw  new RuntimeException("error sending mail");
                    
            }
            disponibilityService.reserve(reservation);
            disponibilityService.save(dispo);

        }

        return myBundle;
    }
    
    //returns all the reservations belonging to a student
     @GetMapping("/reserve")
    public  List<Reservation>  reserve() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        AppUser appUser = accountService.findUserByEmailAndStatus(auth.getName(), true);
          
        return   disponibilityService.findByUser(appUser, "confirm");
        }
    
    

    //display all the monitors availability
    @GetMapping("/monitor")
    public List<Disponibility> findByAppUser() {
//        return disponibilityService.findByAppUser(monitor, true);
        return disponibilityService.findAll();
    }

}
