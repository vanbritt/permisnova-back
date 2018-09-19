/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.permisnova.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.IOException;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.permisnova.entities.AppUser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * @author vanbritt
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    /*
        Etape 2: creation du JWTAuthenticationFilter
    */
    //nous avons besoin de la class authentication manager fourni par spring
    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super();
        this.authenticationManager = authenticationManager;
    }

    //nous devons recuperer le user name et password envoyer par spring security en format json
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        AppUser appUser = null;
        
        //si les donnee sont envoyer envoyer en format www url encode (key:value)
        /*
            String username=request.getParameter("username");
        */
        /*
            This filter need to be added to the security config class as a filter for the  authentication requests
        */

        try {
            //recuperation de l'objet json et la stocker dans java

            appUser = new ObjectMapper().readValue(request.getInputStream(), AppUser.class);
        
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
            System.out.println("***************************");
            System.out.println("username: "+ appUser.getEmail());
            System.out.println("password: "+ appUser.getPassword());
        
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(appUser.getEmail(), appUser.getPassword())); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response/*response that will be sent back to the client*/,
                                            FilterChain chain,
                                            Authentication authResult/*returned by attempt authentication*/) throws IOException, ServletException {
       //recuperation du user ki a ete authentifier et le transformer en objet spring user
      
       User springUser=(User) authResult.getPrincipal();
       
       //maitenant on proced a la generation du token
       
       String jwtToken= Jwts.builder()
                    .setSubject(springUser.getUsername())/*represent le username de springUser*/
                    .setExpiration(new Date(System.currentTimeMillis()+SecurityConstants.EXPIRATION_TIME))/*date d'expiration du token*/
                    .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET) //encodage du token
                    .claim("roles", springUser.getAuthorities()) /* recuperation des role du user*/
                    .compact();
       //apres la generation du token il faut le mettre dans response
       response.setHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX+jwtToken);
   
    }

}
