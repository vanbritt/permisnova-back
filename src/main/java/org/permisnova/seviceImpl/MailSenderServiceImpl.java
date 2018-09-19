/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.permisnova.seviceImpl;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.permisnova.entities.HTMLMail;
import org.permisnova.entities.SimpleMail;
import org.permisnova.sevice.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 *
 * @author vanbritt
 */
@Service
public class MailSenderServiceImpl implements MailSenderService{
    
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendSimpleMail(SimpleMail mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        
        message.setTo(mail.getTo());
        message.setSubject(mail.getSubject());;
        message.setText(mail.getContent());
        
        mailSender.send(message);
    }

    @Override
    public void sendHTMLMail(HTMLMail mail) {
            MimeMessage message = mailSender.createMimeMessage();
            
            MimeMessageHelper helper= new MimeMessageHelper(message, "utf-8");
            
        try {
            helper.setTo(mail.getTo());
            helper.setSubject(mail.getSubject());
            message.setContent(mail.getContent(),"text/html");
            
            mailSender.send(message);
            
        } catch (MessagingException ex) {
            Logger.getLogger(MailSenderServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
    
    @Override
    public void sendHTMLMailAttachment(HTMLMail mail,String firstname,String lastname, String password) throws MessagingException {
            MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        
            
            
        try {
          
        String inlineImage = "<img align='center' src='cid:picture'><br/>";
        
        mail.setContent("<h2> Welcome to, <strong>Nova Victoria</strong> </h2>"
                + "<h3> Your Online Driving PlatForm</h3><br/>"
                 + "Mr/Mrs. "+ lastname +" "+firstname+ " <br/>"
                + "<p>Here is your generated  password : <strong>" + password +"</strong></p><br/>"
                        + "<h5> NB: you are advice to update your password before using</h5>");

        helper.setText(mail.getHeader()+ inlineImage + mail.getContent() + mail.getFooter(), true);
        helper.setSubject(mail.getSubject());
        helper.setTo(mail.getTo());
        helper.addInline( "picture",  new FileSystemResource(new File("src/main/resources/logo2.png")));

            
            mailSender.send(message);
            
        } catch (MessagingException ex) {
            Logger.getLogger(MailSenderServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
}
