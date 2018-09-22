/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.permisnova.dao;

import org.permisnova.entities.AppUser;
import org.permisnova.entities.Bundle;
import org.permisnova.entities.MyBundle;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author vanbritt
 */
public interface MyBundleRepository extends JpaRepository<MyBundle, Integer> {
    
    MyBundle findMyBundleByBundleAndUserAndStatus(Bundle bundle,AppUser user, Boolean Status);
    
}
