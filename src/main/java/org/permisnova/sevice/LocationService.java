/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.permisnova.sevice;

import java.util.List;
import org.permisnova.entities.Rendezvouslocation;

/**
 *
 * @author vanbritt
 */
public interface LocationService {

    List<Rendezvouslocation> findAll();

    void delete(Integer id);

    Rendezvouslocation save(Rendezvouslocation location);

    Rendezvouslocation update(Rendezvouslocation location);
    Rendezvouslocation findByLocation(String location);
}
