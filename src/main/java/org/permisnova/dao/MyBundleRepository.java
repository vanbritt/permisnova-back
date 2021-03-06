/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.permisnova.dao;

import java.util.List;
import org.permisnova.entities.AppUser;
import org.permisnova.entities.Bundle;
import org.permisnova.entities.MyBundle;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author vanbritt
 */
public interface MyBundleRepository extends JpaRepository<MyBundle, Integer> {
    
    MyBundle findMyBundleByBundleAndStudentAndStatus(Bundle bundle,AppUser user, Boolean Status);
    
    MyBundle findMyBundleByStudent(AppUser student);
    
    MyBundle findMyBundleByBundle(Bundle bundle);
    MyBundle findMyBundleByBundleAndStudent(Bundle bundle, AppUser student);

}
