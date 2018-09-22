/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.permisnova.sevice;

import java.util.List;
import org.permisnova.entities.Bundle;

/**
 *
 * @author vanbritt
 */
public interface BundleService {

    List<Bundle> findAll();

    void delete(Integer id);

    Bundle save(Bundle location);

    Bundle update(Bundle location);

}
