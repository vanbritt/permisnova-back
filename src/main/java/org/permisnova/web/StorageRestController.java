/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.permisnova.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.permisnova.entities.AppUser;
import org.permisnova.entities.CourseMaterial;
import org.permisnova.sevice.AccountService;
import org.permisnova.sevice.CourseMaterialService;
import org.permisnova.sevice.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

/**
 *
 * @author vanbritt
 */
@RestController
public class StorageRestController {

    @Autowired
    StorageService storageService;

    @Autowired
    AccountService accountService;

    @Autowired
    CourseMaterialService courseMaterialService;

    List<String> files = new ArrayList<String>();

    @PostMapping("/coursematerial")
    public CourseMaterial handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("coursematerial") String courseMaterial) throws IOException {
        CourseMaterial cm = new ObjectMapper().readValue(courseMaterial, CourseMaterial.class);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AppUser appUser = accountService.findUserByEmailAndStatus(auth.getName(), true);

        String message = "";
        try {
            storageService.store(file);

            files.add(file.getOriginalFilename());
            cm.setFileName(file.getOriginalFilename());
            cm.setMonitor(appUser);
            cm.setUploadDate(new Date());

            return courseMaterialService.save(cm);

        } catch (Exception e) {
            message = "FAIL to upload " + file.getOriginalFilename() + "!";
            return null;
        }
    }
    
    @GetMapping("/coursematerial/monitor")
    public List<CourseMaterial> getInstructorFiles() {
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        AppUser appUser = accountService.findUserByEmailAndStatus(auth.getName(), true);
        List<CourseMaterial> coursematerials = courseMaterialService.findByMonitor(appUser);

        if(coursematerials!=null){
        
            return coursematerials;
        

         }
                return null;
    }
    
    
    
    //function to upload a coursematerial
    
    @GetMapping("/coursematerial/monitor/{filename:.+}")
    @ResponseBody
    public byte[] getFile(@PathVariable String filename) throws IOException {
        System.out.println("good");
        Resource file = storageService.loadFile(filename);
        byte[] files= Files.readAllBytes(Paths.get(file.getURL().getPath()));
        
        System.out.println(file.getURL());
                
        return files;
    }
    

 
    @PostMapping("/post")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            storageService.store(file);
            files.add(file.getOriginalFilename());

            message = "You successfully uploaded " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            message = "FAIL to upload " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }
    

    @GetMapping("/getallfiles")
    public ResponseEntity<List<String>> getListFiles(Model model) {
        List<String> fileNames = files
                .stream().map(fileName -> MvcUriComponentsBuilder
                .fromMethodName(StorageRestController.class, "getFile", fileName).build().toString())
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(fileNames);
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFiles (@PathVariable String filename) {
        Resource file = storageService.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }
}
