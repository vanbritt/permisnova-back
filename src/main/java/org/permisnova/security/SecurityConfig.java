/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.permisnova.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * @author vanbritt
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
/*
    Etape 1: configuration de spring security
    */
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;//instantiate it with bean in the main method
    //here is the method that will tell spring how to fetch users and role in the database

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /* method 1
        //inMemory authentication
       
        auth.inMemoryAuthentication()
             .withUser("admin").password("{noop}1234").roles("ADMIN","USER")
             .and()
             .withUser("user").password("{noop}1234").roles("USER");
         */

 /*     method 2: jdbcAuthentication with written queries
             auth.jdbcAuthentication()
           .usersByUsernameQuery("query")
           .authoritiesByUsernameQuery("query");
            
         */
        //method 3
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder);
        /*
            utilisation de userdetails service pour recuperer les infos concernant
            role et user du system ainsi que le cryptage du mdp avk Bcrypt 
         */

    }

    //it is here that we are defining the access rights
    //ie the routes that can can be access by each users and all the rest
    //
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       
       http.csrf().disable();//csrf found in default forms not in app form /disable crsf in order for third party apps to send data
       //http.formLogin();//besoin du formulaire d'authentication spring par defaut add 
        //http.formLogin().loginPage('/login') use to define login page path
        
        /*Il faut desactiver les session coter serveur par defaut*/
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        
        //donne access a n'importe kel requete sur login et register sans authentification
        http.authorizeRequests().antMatchers("/login/**", "/register/**").permitAll();

        //permet de specifier que rien que les admin on access pour ajouter une tache a /tasks
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/user/**").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/register/student/**").permitAll();
                http.authorizeRequests().antMatchers(HttpMethod.POST, "/register/monitor/**").permitAll();


        http.authorizeRequests().anyRequest().authenticated();//donne l'access a toute les resources pour les utilisateur authentifier

        //ajout du filtre JWTAuthenticationManager
        
        http.addFilter(new JWTAuthenticationFilter(authenticationManager()));

        //ajout du filtre d'authorization qui traite toute les requete avant le filtre d'authentication
        http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

}
