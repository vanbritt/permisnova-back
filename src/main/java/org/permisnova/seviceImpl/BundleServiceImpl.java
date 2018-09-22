/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.permisnova.seviceImpl;

import java.util.List;
import org.permisnova.dao.BundleRepository;
import org.permisnova.entities.Bundle;
import org.permisnova.sevice.BundleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author vanbritt
 */
@Service
public class BundleServiceImpl  implements BundleService{
    
    @Autowired 
    private BundleRepository bundleRepository;

    @Override
    public List<Bundle> findAll() {
            return bundleRepository.findAll();
    }

    @Override
    public void delete(Integer id) {
            bundleRepository.deleteById(id);
    }

    @Override
    public Bundle save(Bundle bundle) {
            return bundleRepository.save(bundle);
    }

    @Override
    public Bundle update(Bundle bundle) {
            return bundleRepository.save(bundle);
            
    }
    
}
