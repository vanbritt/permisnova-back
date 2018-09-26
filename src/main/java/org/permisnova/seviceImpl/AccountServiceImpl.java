/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.permisnova.seviceImpl;

import java.util.ArrayList;
import java.util.List;
import org.permisnova.dao.RoleRepository;
import org.permisnova.dao.UserRepository;
import org.permisnova.entities.AppRole;
import org.permisnova.entities.AppUser;
import org.permisnova.sevice.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vanbritt
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public AppUser saveUser(AppUser user) {
        String hashPw = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hashPw);
        return userRepository.save(user);
    }

    @Override
    public AppRole saveRole(AppRole role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String email, String rolename) {
            AppUser user= userRepository.findByEmail(email);
            AppRole role= roleRepository.findByRoleName(rolename);
            
           user.getRoles().add(role); //the role is persisted automatically due to @transaction
           

    }

    @Override
    public AppUser findUserByEmailAndStatus(String email,boolean status) {
        return userRepository.findByEmailAndStatus(email,status);
    }
  
  

    @Override
    public AppUser findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<AppUser> findUserByRole(String role) {
        List<AppUser> users= new ArrayList<>();
        
        AppRole r= roleRepository.findByRoleName(role);
         userRepository.findAll().forEach(u -> {
        
                if(u.getRoles().contains((AppRole)r)){
                    users.add(u);
                }
        } );
        
         return users;
        }
        

}
