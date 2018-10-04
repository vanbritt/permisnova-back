/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.permisnova.dao;

import java.util.List;
import org.permisnova.entities.AppUser;
import org.permisnova.entities.CourseMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author lenovo
 */
public interface CourseMaterialRepository extends JpaRepository<CourseMaterial, Integer> {
    CourseMaterial findByFileName(String fileName);
        List <CourseMaterial> findByMonitor(AppUser monitor);

}
