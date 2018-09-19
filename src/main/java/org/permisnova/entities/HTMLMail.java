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
public class HTMLMail {

    private final String to;
    private final String header="<html>  <body>";
    private final String footer ="</body> </html>";
    private String content;

    public HTMLMail(String to) {
        this.to = to;
    }

    public String getTo() {
        return this.to;
    }

    public String getSubject() {
        return "Permis Nova";
    }

    
    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public String getHeader() {
        return header;
    }

    public String getFooter() {
        return footer;
    }
}