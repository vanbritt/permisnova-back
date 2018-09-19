/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.permisnova.sevice;

import java.util.List;
import org.permisnova.entities.Disponibility;

/**
 *
 * @author vanbritt
 */
public interface DisponibilityService {

    List<Disponibility> findAll();

    Disponibility save(Disponibility disponibility);

    void delete(Integer id);
    
    Disponibility update(Disponibility disponibility);

}
