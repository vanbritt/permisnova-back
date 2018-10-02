/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.permisnova.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import org.permisnova.entities.AppUser;
import org.permisnova.entities.Disponibility;
import org.permisnova.entities.Rendezvouslocation;
import org.permisnova.sevice.AccountService;
import org.permisnova.sevice.DisponibilityService;
import org.permisnova.sevice.LocationService;
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
    
    
    @GetMapping
    public List<Disponibility> findAll() {
        return disponibilityService.findAll();
    }
    
    @GetMapping("/monitor/{id}")
    public  List<Disponibility> findByMonitor(@PathVariable Long id){
            
            AppUser monitor= accountService.findByIdAndStatus(id, true);
            
            return disponibilityService.findByAppUser(monitor, true);
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
    public  boolean delete(@PathVariable int id ){
         System.out.println("good");
        disponibilityService.delete(id);
        return true;
    }
    
     @GetMapping("/reserve/{id}")
    public  boolean reserve(@PathVariable int id ){
        
         Authentication auth = SecurityContextHolder.getContext().getAuthentication();

         AppUser appUser = accountService.findUserByEmailAndStatus(auth.getName(), true);
         
         boolean check= myBundleService.
       
        disponibilityService.delete(id);
        return true;
    }
    
    @GetMapping("/monitor")
    public List<Disponibility> findByAppUser() {
//        return disponibilityService.findByAppUser(monitor, true);
        return disponibilityService.findAll();
    }
    
}
