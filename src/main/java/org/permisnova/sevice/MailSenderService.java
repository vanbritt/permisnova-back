/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.permisnova.sevice;

import javax.mail.MessagingException;
import org.permisnova.entities.AppUser;
import org.permisnova.entities.HTMLMail;
import org.permisnova.entities.Reservation;
import org.permisnova.entities.SimpleMail;

/**
 *
 * @author vanbritt
 */
public interface MailSenderService {

    void sendSimpleMail(SimpleMail mail);

    void sendHTMLMail(HTMLMail mail);
    void sendHTMLMailAttachment(HTMLMail mail,String firstname, String lastname, String password) throws MessagingException;
    void sendHTMLMailAttachmentReservation(HTMLMail mail,AppUser user, Reservation reservation) throws MessagingException;

}
