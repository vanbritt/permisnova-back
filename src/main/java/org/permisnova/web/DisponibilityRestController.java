/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.permisnova.web;

import java.util.List;
import org.permisnova.entities.Disponibility;
import org.permisnova.sevice.DisponibilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/disponibility")
public class DisponibilityRestController {
    
    @Autowired
    private DisponibilityService disponibilityService;
    
    @GetMapping
    public List<Disponibility> findAll(){
        return disponibilityService.findAll();
    }
    
    @PostMapping
    public Disponibility save(@RequestBody Disponibility disponibility){
        return disponibilityService.save(disponibility);
    }
    
}
