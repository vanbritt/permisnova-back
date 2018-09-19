/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.permisnova.security;

/**
 *
 * @author vanbritt
 */
public class SecurityConstants {

    public static final String SECRET = "permisnova@gmail.com";//secret use to encode token
    public static final long EXPIRATION_TIME = 86_400;//10days use to set token expiration time
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

}
