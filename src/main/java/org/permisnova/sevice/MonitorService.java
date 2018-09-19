/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.permisnova.sevice;

import java.util.List;
import org.permisnova.entities.Monitor;

/**
 *
 * @author vanbritt
 */
public interface MonitorService {

    List<Monitor> findAll();

    Monitor save(Monitor monitor);

    void delete(Integer id);
    
    Monitor update(Monitor monitor);

}
