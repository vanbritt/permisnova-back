/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.permisnova.seviceImpl;

import java.util.List;
import java.util.Optional;
import org.permisnova.dao.CourseMaterialRepository;
import org.permisnova.entities.AppUser;
import org.permisnova.entities.CourseMaterial;
import org.permisnova.sevice.CourseMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lenovo
 */

@Service
public class CourseMaterialServiceImpl implements CourseMaterialService{

    @Autowired
    CourseMaterialRepository courseMaterialRepository;
    
    @Override
    public List<CourseMaterial> findAll() {
            return courseMaterialRepository.findAll();
    }

    @Override
    public CourseMaterial findByfileName(String name) {
        
        return courseMaterialRepository.findByFileName(name);
    
    }

    @Override
    public void delete(Integer id) {
            courseMaterialRepository.deleteById(id);
    }

    @Override
    public CourseMaterial save(CourseMaterial location) {
            return courseMaterialRepository.save(location);
    }

    @Override
    public CourseMaterial finById(Integer id) {
          Optional <CourseMaterial> cm= courseMaterialRepository.findById(id);
          return cm.get();
    }

    @Override
    public CourseMaterial update(CourseMaterial location) {
          return  courseMaterialRepository.save(location);
    }

    @Override
    public List<CourseMaterial> findByMonitor(AppUser monitor) {
                return courseMaterialRepository.findByMonitor(monitor);
    }
    
}
