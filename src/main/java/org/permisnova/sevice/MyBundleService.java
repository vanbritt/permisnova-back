/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.permisnova.sevice;

import java.util.List;
import org.permisnova.entities.AppUser;
import org.permisnova.entities.Bundle;
import org.permisnova.entities.MyBundle;

/**
 *
 * @author vanbritt
 */
public interface MyBundleService {

    void addBundleToUser(Integer id, AppUser user);

    MyBundle save(MyBundle myBundle);

    void dlete(int delete);

    void removeCreditFromUserBundle(Bundle bundle, AppUser user, Integer credit);

    MyBundle userBundle(AppUser user, String code);

    MyBundle userBundle(AppUser user);

    boolean checkBundle(Bundle bundle, AppUser user);

}
