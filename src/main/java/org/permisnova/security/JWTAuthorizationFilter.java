/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.permisnova.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 * @author vanbritt
 */
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        //ajout des entete pour faciliter la communication entre le client et le serveur

        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers, Authorization"
        );

        response.addHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin, Access-Control-Allow-Credentials, Authorization");

        //PERMET de passer et set la valeur du response des options ki est blocker par le spring filter
        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {

            //recuperation du token dans la requete du client
            String jwtToken = request.getHeader(SecurityConstants.HEADER_STRING);

            //test pour savoir si cest un token d'authorization ou d'authentification
            if (jwtToken == null || !jwtToken.startsWith(SecurityConstants.TOKEN_PREFIX)) {
                filterChain.doFilter(request, response);//pass au prochain filtre JwtAuthenticationFilter
                return;
            }

            //ok c'est un token d'authorization : recuperation du corps et contenue du token
            Claims claims = Jwts.parser()
                    .setSigningKey(SecurityConstants.SECRET)
                    .parseClaimsJws(jwtToken.replace(SecurityConstants.TOKEN_PREFIX, ""))//suppression du prefix
                    .getBody();

            String username = claims.getSubject();//recuperation de l'utilisateur
            //get the list of roles of the user;
            ArrayList<Map<String, String>> roles = (ArrayList<Map<String, String>>) claims.get("roles");

            //Getting all the roles of the user form and storing it
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            roles.forEach(r -> {
                authorities.add(new SimpleGrantedAuthority(r.get("authority")));
            });

            //creation d'un username password authentication token le password est a null pckon n'en a pa besoin
            // on verifie juste si le token est valid et on charge ses roles dans le context de securiter
            UsernamePasswordAuthenticationToken authenticatedUser = new UsernamePasswordAuthenticationToken(username, null, authorities);
            //chargement du user et de ses roles dans le context de securite Spring
            SecurityContextHolder.getContext().setAuthentication(authenticatedUser);

            filterChain.doFilter(request, response);//pass la main au prochain filtre SpringFilter

        }

    }

}
