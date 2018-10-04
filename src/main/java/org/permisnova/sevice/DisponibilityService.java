/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.permisnova.sevice;

import java.util.List;
import org.permisnova.entities.AppUser;
import org.permisnova.entities.Disponibility;
import org.permisnova.entities.Reservation;

/**
 *
 * @author vanbritt
 */
public interface DisponibilityService {

    List<Disponibility> findAll();

    List<Disponibility> findDisponibilityByUser(AppUser monitor, Boolean status);

    List<Reservation> findByUser(AppUser student, String state);

    Reservation findReservationById(Integer id);

    Reservation saveReservation(Reservation reservation);

    Disponibility save(Disponibility disponibility);

    Disponibility findById(Integer id);

    void delete(Integer id);

    Disponibility update(Disponibility disponibility);

    Reservation reserve(Reservation reservation);

    boolean cancel(Long id);

}
