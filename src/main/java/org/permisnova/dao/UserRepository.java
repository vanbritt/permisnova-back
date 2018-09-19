/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.permisnova.dao;

import org.permisnova.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author vanbritt
 */
public interface UserRepository extends JpaRepository<AppUser, Long> {
    
    AppUser findByEmailAndStatus(String email, boolean status);
    AppUser findByEmail(String email);
    
}
