/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.permisnova.seviceImpl;

import java.util.List;
import org.permisnova.dao.MonitorRepository;
import org.permisnova.entities.Monitor;
import org.permisnova.sevice.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author vanbritt
 */
@Service
public class MonitorServiceImpl implements MonitorService {

    @Autowired
    private MonitorRepository monitorRepository;

    @Override
    public List<Monitor> findAll() {
        return monitorRepository.findAll();
    }

    @Override
    public Monitor save(Monitor monitor) {
        return monitorRepository.save(monitor);
    }

    @Override
    public void delete(Integer id) {
        monitorRepository.deleteById(id);
    }

    @Override
    public Monitor update(Monitor monitor) {
        return monitorRepository.save(monitor);
    }

}
