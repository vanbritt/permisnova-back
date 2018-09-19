/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.permisnova.web;

import java.util.List;
import org.permisnova.entities.Rendezvouslocation;
import org.permisnova.sevice.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author vanbritt
 */
@RestController
@RequestMapping("/location")
public class LocationRestController {
    
    @Autowired 
    private LocationService locationService;
    
    
    @GetMapping
    List <Rendezvouslocation> findAll(){
        return locationService.findAll();
    }
    
    @PostMapping
    Rendezvouslocation save(@RequestBody Rendezvouslocation location){
            return locationService.save(location);
     }
    
}
