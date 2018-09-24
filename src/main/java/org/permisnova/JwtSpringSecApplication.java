package org.permisnova;

import java.util.TimeZone;
import javax.annotation.PostConstruct;
import org.permisnova.entities.AppRole;
import org.permisnova.entities.AppUser;
//import org.permisnova.entities.Task;
import org.permisnova.sevice.AccountService;
import org.permisnova.sevice.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class JwtSpringSecApplication implements CommandLineRunner {

//    @Autowired
//    private TaskRepository taskRepository;
    
    @PostConstruct
    public void init(){
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }
    
    
    @Autowired
    private MailSenderService senderService;

    @Autowired
    private AccountService accountService;

    public static void main(String[] args) {
        SpringApplication.run(JwtSpringSecApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void run(String... args) throws Exception {
        
         // send a simple mail
//        senderService.sendSimpleMail(new SimpleMail("tangangbrice@gmail.com"));

        //send an HTML mail
//        senderService.sendHTMLMailAttachment(new HTMLMail("tangangbrice@gmail.com"), "1235489");
        
        accountService.saveUser(new AppUser("admin@gmail.com", "admin"));
                accountService.saveUser(new AppUser("monitor@gmail.com", "monitor"));

//        accountService.saveUser(new AppUser("user", "1234"));
        accountService.saveRole(new AppRole("ADMIN"));
        accountService.saveRole(new AppRole("STUDENT"));
                accountService.saveRole(new AppRole("MONITOR"));

        accountService.addRoleToUser("admin@gmail.com", "ADMIN");
                accountService.addRoleToUser("monitor@gmail.com", "MONITOR");

//        accountService.addRoleToUser("admin", "USER");
//        accountService.addRoleToUser("user", "USER");
//
//        Stream.of("T1", "T2", "T3").forEach(t -> {
//            taskRepository.save(new Task(t));ç
//        });
//
//        taskRepository.findAll().forEach(t -> {
//            System.out.println(t.getTaskName());
//        });
    }
}
