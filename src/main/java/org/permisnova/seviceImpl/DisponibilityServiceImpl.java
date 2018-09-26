/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.permisnova.seviceImpl;

import java.util.List;
import org.permisnova.dao.DisponibilityRepository;
import org.permisnova.entities.AppUser;
import org.permisnova.entities.Disponibility;
import org.permisnova.sevice.DisponibilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author vanbritt
 */

@Service
public class DisponibilityServiceImpl  implements DisponibilityService{
    @Autowired
     private DisponibilityRepository disponibilityRepository;
    
    
    @Override
    public List<Disponibility> findAll() {
        return disponibilityRepository.findAll();
    }

    @Override
    public Disponibility save(Disponibility disponibility) {
          return disponibilityRepository.save(disponibility);
    }

    @Override
    public void delete(Integer id) {
           disponibilityRepository.deleteById(id);
    }

    @Override
    public Disponibility update(Disponibility disponibility) {
          return  disponibilityRepository.save(disponibility);
    }

    @Override
    public List<Disponibility> findByMonitor(AppUser monitor, boolean status) {
                            return disponibilityRepository.findByMonitorAndStatus(monitor, status);
    }
    
}
