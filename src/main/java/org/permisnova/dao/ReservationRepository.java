/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.permisnova.dao;

import org.permisnova.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author vanbritt
 */
public interface ReservationRepository extends JpaRepository<Reservation, Long>{
    
}
