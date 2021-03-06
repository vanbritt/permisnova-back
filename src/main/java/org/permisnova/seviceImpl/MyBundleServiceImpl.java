/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.permisnova.seviceImpl;

import java.util.List;
import java.util.Optional;
import org.permisnova.dao.BundleRepository;
import org.permisnova.dao.MyBundleRepository;
import org.permisnova.entities.AppUser;
import org.permisnova.entities.Bundle;
import org.permisnova.entities.MyBundle;
import org.permisnova.sevice.MyBundleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author vanbritt
 */

@Service
public class MyBundleServiceImpl implements MyBundleService {

    @Autowired
    private MyBundleRepository myBundleRepository;

      @Autowired
    private BundleRepository BundleRepository;
      
    @Override
    public void addBundleToUser(Integer id, AppUser user) {
       Optional <Bundle>bundle1= BundleRepository.findById(id);
        
        Bundle bundle= bundle1.get();
        MyBundle myBundle= myBundleRepository.findMyBundleByBundle(bundle);
        
        if(myBundle== null){
            myBundle= new MyBundle();
            
        myBundle.setAppUser(user);
        myBundle.setBundle(bundle);
        myBundle.setStatus(Boolean.TRUE);
        myBundle.setTotalCredit(bundle.getCredit());
        myBundle.setRemainingCredit(bundle.getCredit());
        myBundle.setUseCredit(0);

        myBundleRepository.save(myBundle);
        
        }else{
        
                myBundle.setTotalCredit(myBundle.getTotalCredit()+bundle.getCredit());
              myBundleRepository.save(myBundle);
        }

    }

    @Override
    public void removeCreditFromUserBundle(Bundle bundle, AppUser user, Integer credit) {
        MyBundle myBundle = myBundleRepository.findMyBundleByBundleAndStudentAndStatus(bundle, user, Boolean.TRUE);

        if (myBundle != null && myBundle.getRemainingCredit() > 0) {
            myBundle.setUseCredit(myBundle.getUseCredit() + credit);
            myBundle.setRemainingCredit(myBundle.getRemainingCredit() - credit);

            myBundleRepository.save(myBundle);

        }

    }

    @Override
    public MyBundle userBundle(AppUser user, String code) {
        
                Bundle bundle= BundleRepository.findBundleByCode(code);
                
        
      return myBundleRepository.findMyBundleByBundleAndStudent(bundle,user);
    }
    
    
     @Override
    public MyBundle userBundle(AppUser user) {
        
                
        
      return myBundleRepository.findMyBundleByStudent(user);
    }

    @Override
    public boolean checkBundle(Bundle bundle, AppUser user) {
        MyBundle myBundle= myBundleRepository.findMyBundleByBundle(bundle);
        
        System.out.println(myBundle);
        
        if(myBundle==null){
            return false;
        }else if(myBundle.getRemainingCredit() < 0){
            return false;
        }else{
            return true;
        }

        
       }

    @Override
    public MyBundle save(MyBundle myBundle) {

        return myBundleRepository.save(myBundle);

    }

    @Override
    public void dlete(int delete) {
        myBundleRepository.deleteById(delete);
    }

}
