package com.ymy.graduation.service.impl;

import com.ymy.graduation.domain.Driver;
import com.ymy.graduation.repository.driverTab;
import com.ymy.graduation.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ymyum
 * @date 2020/2/5 17:15
 * @project
 */
@Service
public class DriverServiceImpl implements DriverService {
    @Autowired
    private driverTab driverTab;
    @Override
    public String CreateDriver(Driver driver) {
        Driver driver1= driverTab.findByDriverId(driver.getdId());
        if(driver1!=null){
            return "Exists";
        }
        else
        {
            boolean flag=false;
            try {
                driverTab.save(driver);
                flag=true;
            }
            catch (Exception e){
                System.out.println(e);
            }
            if(flag){
                return "true";
            }
            else {
                return "false";
            }
        }
    }

    @Override
    public String DeleteDriver(Integer dId) {
        Driver driver= driverTab.findByDriverId(dId);
        if(driver==null){
            return "null";
        }
        else
        {
            boolean flag=false;
            try {
                driverTab.delete(driver);
                flag=true;
            }
            catch (Exception e){
                System.out.println(e);
            }
            if(flag){
                return "true";
            }
            else {
                return "false";
            }
        }
    }

    @Override
    public Driver findByDriverId(Integer dId) {
        return driverTab.findByDriverId(dId);
    }

    @Override
    public List<Driver> findAllDriver() {
        return driverTab.findAll();
    }

    @Override
    public Boolean UpdateDriver(String dName, String dPhone, String dState, Integer dId) {
        Driver driver=driverTab.findByDriverId(dId);
        if(driver==null){
            return null;
        }
        else {
            boolean flag=false;
            try {
                driverTab.UpdateDriver( dName, dPhone, dState, dId);
                flag=true;
            }
            catch (Exception e){
                System.out.println(e);
            }
            if(flag){return true;}
            else {return false;}
        }
    }

    @Override
    public Boolean UpdateState(String dState, Integer dId){
        boolean flag=false;
        try {
            driverTab.updateState( dState, dId);
            flag=true;
        }
        catch (Exception e){
            System.out.println(e);
        }
        if(flag){return true;}
        else {return false;}
    }
}

