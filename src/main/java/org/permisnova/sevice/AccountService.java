/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.permisnova.sevice;

import java.util.List;
import org.permisnova.entities.AppRole;
import org.permisnova.entities.AppUser;

/**
 *
 * @author vanbritt
 */
public interface AccountService {

    AppUser saveUser(AppUser user);

    AppRole saveRole(AppRole role);

    void addRoleToUser(String username, String role);

    AppUser findUserByEmail(String email);
    
        AppUser findByIdAndStatus(Long id, boolean status );


    AppUser findUserByEmailAndStatus(String email, boolean status);
     
     List<AppUser> findUserByRole(String Role);




}
