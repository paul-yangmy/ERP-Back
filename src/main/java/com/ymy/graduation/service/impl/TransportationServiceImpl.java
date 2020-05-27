package com.ymy.graduation.service.impl;

import com.ymy.graduation.domain.Driver;
import com.ymy.graduation.domain.Transportation;
import com.ymy.graduation.repository.transportationTab;
import com.ymy.graduation.service.TransportationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author ymyum
 * @date 2020/2/5 17:07
 * @project
 */
@Service
public class TransportationServiceImpl implements TransportationService {
    @Autowired
    private transportationTab transportationTab;
    @Override
    public Transportation CreateTransportation(Transportation transportation) {
        Transportation transportation1= transportationTab.findByTransId(transportation.getTransId());
        if(transportation1!=null){
            return null;//存在同名
        }
        else {

            try {
                return transportationTab.save(transportation);

            } catch (Exception e) {
                System.out.println(e);
                return null;
            }

        }
    }

    @Override
    public String DeleteTransportation(Integer transId) {
        Transportation transportation= transportationTab.findByTransId(transId);
        if(transportation==null){
            return "null";
        }
        else
        {
            boolean flag=false;
            try {
                transportationTab.delete(transportation);
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
    public Transportation findByTransId(Integer transId) {
        return transportationTab.findByTransId(transId);
    }
    @Override
    public List<Transportation> findByTransDate(Date transDate) {
        return transportationTab.findByTransDate(transDate);
    }
    @Override
    public List<Transportation> findByLine(String transName) {
        return transportationTab.findByLine(transName);
    }

    @Override
    public List<Transportation> findAllTransportation() {
        return transportationTab.findAll();
    }

    @Override
    public Boolean UpdateTransportation(Date transDate,String transName,Integer transId) {
        Transportation transportation=transportationTab.findByTransId(transId);
        if(transportation==null){
            return null;
        }
        else {
            boolean flag=false;
            try {
                transportationTab.UpdateTransportation(  transDate, transName, transId);
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
    public Boolean UpdateDriver(Driver driver, Integer transId) {

            boolean flag=false;
            try {
                transportationTab.UpdateDriver(  driver, transId);
                flag=true;
            }
            catch (Exception e){
                System.out.println(e);
            }
            if(flag){return true;}
            else {return false;}
        }

}

