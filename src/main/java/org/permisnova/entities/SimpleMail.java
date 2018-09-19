/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.permisnova.entities;

/**
 *
 * @author vanbritt
 */
public class SimpleMail {

    private final String to;

    public SimpleMail(String to) {
        this.to = to;
    }

    public String getTo() {
        return this.to;
    }

    public String getSubject() {
        return "Simple Email Subject";
    }

    public String getContent() {
        return "Hello client,\n This a simple email content !";
    }
}