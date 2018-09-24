/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.permisnova.seviceImpl;

import org.permisnova.dao.MyBundleRepository;
import org.permisnova.entities.AppUser;
import org.permisnova.entities.Bundle;
import org.permisnova.entities.MyBundle;
import org.permisnova.sevice.MyBundleService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author vanbritt
 */
public class MyBundleServiceImpl implements MyBundleService {

    @Autowired
    private MyBundleRepository myBundleRepository;

    @Override
    public void addBundleToUser(Bundle bundle, AppUser user) {
        MyBundle myBundle = new MyBundle();

        myBundle.setStudent(user);
        myBundle.setBundle(bundle);
        myBundle.setStatus(Boolean.TRUE);
        myBundle.setTotalCredit(bundle.getCredit());
        myBundle.setRemainingCredit(bundle.getCredit());
        myBundle.setUseCredit(0);

        myBundleRepository.save(myBundle);

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

}
