/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.permisnova.sevice;

import java.util.List;
import org.permisnova.entities.AppUser;

/**
 *
 * @author vanbritt
 */
public interface MonitorService {

    List<AppUser> findAll();

    AppUser save(AppUser monitor);

    void delete(Integer id);
    
    AppUser update(AppUser monitor);

}
