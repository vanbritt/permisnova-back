/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.permisnova.seviceImpl;

import java.util.List;
import java.util.Optional;
import org.permisnova.dao.DisponibilityRepository;
import org.permisnova.dao.ReservationRepository;
import org.permisnova.entities.AppUser;
import org.permisnova.entities.Disponibility;
import org.permisnova.entities.Reservation;
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
        @Autowired
     private ReservationRepository reservationRepository;
    
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
    public List<Disponibility> findByAppUser(AppUser monitor, boolean status) {
                            return disponibilityRepository.findByMonitorAndStatus(monitor, status);
    }

    @Override
    public Disponibility findById(Integer id) {

            Optional<Disponibility> disponibility= disponibilityRepository.findById(id);
            
            return disponibility.get();
    }

    @Override
    public Reservation reserve(Reservation reservation) {
            
            return reservationRepository.save(reservation);
    }

    @Override
    public boolean cancel(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
