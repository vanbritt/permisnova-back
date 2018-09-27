/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.permisnova.sevice;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author vanbritt
 */
public interface StorageService {

    void store(MultipartFile file);

    Resource loadFile(String name);

    void deleteAll();
    
    void init();

}
