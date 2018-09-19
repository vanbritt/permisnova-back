/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.permisnova.seviceImpl;

import java.util.List;
import org.permisnova.dao.LocationRepository;
import org.permisnova.entities.Rendezvouslocation;
import org.permisnova.sevice.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author vanbritt
 */
@Service
public class LocationServiceImpl implements LocationService{
        @Autowired
        private LocationRepository locationRepository;
        
        
    
    @Override
    public List<Rendezvouslocation> findAll() {
        
        return locationRepository.findAll();
    }

    @Override
    public void delete(Integer id) {
        locationRepository.deleteById(id);
    }

    @Override
    public Rendezvouslocation save(Rendezvouslocation location) {
            return locationRepository.save(location);
    }

    @Override
    public Rendezvouslocation update(Rendezvouslocation location) {
            return locationRepository.save(location);
    }
    
}
