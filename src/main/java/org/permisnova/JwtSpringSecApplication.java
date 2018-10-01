package org.permisnova;

import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import org.permisnova.entities.AppRole;
import org.permisnova.entities.AppUser;
import org.permisnova.entities.Bundle;
import org.permisnova.entities.Disponibility;
import org.permisnova.entities.Rendezvouslocation;
//import org.permisnova.entities.Task;
import org.permisnova.sevice.AccountService;
import org.permisnova.sevice.BundleService;
import org.permisnova.sevice.DisponibilityService;
import org.permisnova.sevice.LocationService;
import org.permisnova.sevice.MailSenderService;
import org.permisnova.sevice.StorageService;
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
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    @Autowired
    private MailSenderService senderService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private StorageService storageService;

    @Autowired
    private BundleService bundleService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private DisponibilityService disponibilityService;

    @Autowired
    ServletContext servletContext;

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
        byte[] bFile = Files.readAllBytes(new File("src/main/resources/logo2.png").toPath());

        //monitor creation
        AppUser monitor = new AppUser("monitor@gmail.com", "monitor");

        monitor.setProfile(Files.readAllBytes(new File("src/main/resources/instructor.jpg").toPath()));
        monitor.setFirstname("Mr Bommeu");
        monitor.setLastname("Gervais");
        monitor.setDepartment("Center");
        monitor.setRegisterDate(new Date());

        accountService.saveUser(monitor);

        //saving admin
        accountService.saveUser(new AppUser("admin@gmail.com", "admin"));

        //saving student
        AppUser student = new AppUser("student@gmail.com", "student");

        student.setProfile(Files.readAllBytes(new File("src/main/resources/student.jpg").toPath()));
        student.setFirstname("Mr Tangang");
        student.setLastname("Brice");
        student.setDepartment("Center");
        student.setRegisterDate(new Date());

        accountService.saveUser(student);
        //role creation
//        accountService.saveUser(new AppUser("user", "1234"));
        accountService.saveRole(new AppRole("ADMIN"));
        accountService.saveRole(new AppRole("STUDENT"));
        accountService.saveRole(new AppRole("MONITOR"));

        //or this
        accountService.addRoleToUser("admin@gmail.com", "ADMIN");
        accountService.addRoleToUser("monitor@gmail.com", "MONITOR");

        accountService.addRoleToUser("student@gmail.com", "STUDENT");

        //bundle creation
        Bundle bundle = new Bundle();
        bundle.setCode("driving");
        bundle.setCredit(20);
        bundle.setHours(20);
        bundle.setDescription("Obtaining your driving license at low cost");
        bundle.setLabel("Driving Bundle");
        bundle.setPrice(5000);

        bundleService.save(bundle);

        //adding a location
        Rendezvouslocation location = new Rendezvouslocation();

        location.setAppUser(monitor);
        location.setLocation("Yaounde");
        Rendezvouslocation location1 = new Rendezvouslocation();

        location1.setAppUser(monitor);
        location1.setLocation("Yaounde");

        locationService.save(location);
        locationService.save(location1);

        //adding a disponibility;
        for(int i=0; i<3; i++){
        Disponibility disponibility = new Disponibility();

        disponibility.setAppUser(monitor);
        disponibility.setNumberHours(2);
        if(i==0){
        disponibility.setRendezvousLocation(location);
        }else{
           disponibility.setRendezvousLocation(location1);

        }
        disponibility.setStatus(Boolean.TRUE);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        if(i==1){
         date = format.parse("2018-10-12");
        }else{
           date = format.parse("2018-11-16");

        }

        disponibility.setDate(date);

        SimpleDateFormat format1 = new SimpleDateFormat("HH:mm");
        Date date1 = format1.parse("14:00");
        disponibility.setStartTime(date1);

        date1 = format1.parse("16:00");
        disponibility.setEndTime(date1);
        disponibilityService.save(disponibility);
        
        }
        
       


            storageService.init();

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
