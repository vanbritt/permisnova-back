/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.permisnova.web;

import java.util.List;
import org.permisnova.entities.AppUser;
import org.permisnova.entities.Bundle;
import org.permisnova.entities.MyBundle;
import org.permisnova.sevice.AccountService;
import org.permisnova.sevice.BundleService;
import org.permisnova.sevice.MyBundleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @Autowired
    private MyBundleService myBundleService;

    @Autowired
    private AccountService accountService;

    @PostMapping
    public Bundle save(@RequestBody Bundle bundle) {
        if (bundle != null && bundle.getCode() != null) {
            return bundleService.save(bundle);
        }
        return null;
    }

    @GetMapping("/buy/{id}")
    public Bundle buy(@PathVariable("id") Integer bundle) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        AppUser appUser = accountService.findUserByEmailAndStatus(auth.getName(), true);

        myBundleService.addBundleToUser(bundle, appUser);
        return null;
        
    }

    @GetMapping
    public List<Bundle> findAll() {
        return bundleService.findAll();
    }

    @GetMapping("/mybundle")
    public List<MyBundle> findMyBundle() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        AppUser appUser = accountService.findUserByEmailAndStatus(auth.getName(), true);

        return myBundleService.userBundle(appUser);

    }
    
   @GetMapping("/check/{code}")
   public boolean checkBundle(@PathVariable("code") String code){
        
            Bundle bundle = bundleService.findByCode(code);
            if(bundle  != null){
                     Authentication auth = SecurityContextHolder.getContext().getAuthentication();

                    AppUser appUser = accountService.findUserByEmailAndStatus(auth.getName(), true);
                    return myBundleService.checkBundle(bundle, appUser);
                }
            return false;
   }

}
