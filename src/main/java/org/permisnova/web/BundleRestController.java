/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.permisnova.web;

import java.util.List;
import org.permisnova.entities.Bundle;
import org.permisnova.sevice.BundleService;
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
@RequestMapping("/bundle")
public class BundleRestController {
    
    @Autowired
    private BundleService bundleService;
    
    @PostMapping
    public Bundle save(@RequestBody Bundle bundle){
        if(bundle!=null && bundle.getCode() != null){
           return bundleService.save(bundle);
        }
        return null;
    }
    
    
    @GetMapping
    public List<Bundle> findAll(){
        return bundleService.findAll();
    }
    
}
