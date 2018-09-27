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
public interface BundleService {

    List<Bundle> findAll();

    void delete(Integer id);

    Bundle save(Bundle location);
    
     Bundle finById(Integer id);
     

    Bundle update(Bundle location);

}
