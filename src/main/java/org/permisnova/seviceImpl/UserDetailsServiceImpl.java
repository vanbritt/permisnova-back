/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.permisnova.seviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import org.permisnova.entities.AppUser;
import org.permisnova.sevice.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author vanbritt
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountService accountService;

    /*
        cette method appartient a SpringSecurity et sert a recuperer les information du users
        fait un mapping utilisateur d l'appli utilisateur spring;
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser user = accountService.findUserByEmailAndStatus(email,true);

        if (user == null) {
            throw new UsernameNotFoundException(email);
        }
        //creation de l'objet qui va recuperer tout les role utilisateur

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        //ajout des role utilisateur a simplegrantedAutorithies
        user.getRoles().forEach(r -> {
            authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
        });
        //retourne l'objet user d spring security pour l'authentification
        return new User(user.getEmail(), user.getPassword(),authorities);
    }

}
