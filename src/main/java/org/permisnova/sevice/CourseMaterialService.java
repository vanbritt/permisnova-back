/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.permisnova.sevice;

import java.util.List;
import org.permisnova.entities.AppUser;
import org.permisnova.entities.CourseMaterial;

/**
 *
 * @author lenovo
 */
public interface CourseMaterialService {

    List<CourseMaterial> findAll();

    List<CourseMaterial> findByMonitor(AppUser monitor);

    CourseMaterial findByfileName(String name);

    void delete(Integer id);

    CourseMaterial save(CourseMaterial location);

    CourseMaterial finById(Integer id);

    CourseMaterial update(CourseMaterial location);

}
