/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.permisnova.sevice;

import org.permisnova.entities.AppUser;
import org.permisnova.entities.Bundle;
import org.permisnova.entities.MyBundle;

/**
 *
 * @author vanbritt
 */
public interface MyBundleService {
    
    void addBundleToUser(Bundle bundle, AppUser user);
    
    void removeCreditFromUserBundle(Bundle bundle, AppUser user, Integer credit);
    
}
