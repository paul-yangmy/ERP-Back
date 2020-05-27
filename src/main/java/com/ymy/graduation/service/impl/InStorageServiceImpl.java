package com.ymy.graduation.service.impl;

import com.ymy.graduation.domain.InStorage;
import com.ymy.graduation.domain.Purchase;
import com.ymy.graduation.repository.inStorageTab;
import com.ymy.graduation.service.InStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author ymyum
 * @date 2020/2/4 16:30
 * @project
 */
@Service
public class InStorageServiceImpl implements InStorageService {
    @Autowired
    private inStorageTab inStorageTab;
    @Override
    public String CreateInStorage(InStorage inStorage) {
        InStorage inStorage1= inStorageTab.findByInId(inStorage.getInId());
        if(inStorage1!=null){
            return "Exists";//存在同名
        }
        else
        {
            boolean flag=false;
            try {
                inStorageTab.save(inStorage);
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
    public String DeleteInStorage(Long inId) {
        InStorage inStorage= inStorageTab.findByInId(inId);
        if(inStorage==null){
            return "null";
        }
        else
        {
            boolean flag=false;
            try {
                inStorageTab.delete(inStorage);
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
    public InStorage findByInId(Long inId) {
        return inStorageTab.findByInId(inId);
    }

    @Override
    public InStorage findByPurchase(Purchase purchase) {
        return inStorageTab.findByPurchase(purchase);
    }

    @Override
    public List<InStorage> findAllInStorage() {
        return inStorageTab.findAll();
    }

    @Override
    public Boolean UpdateInStorage(Date inDate, String storageNam, String lister, String inState,String inIdName, Long inId) {
        InStorage inStorage=inStorageTab.findByInId(inId);
        if(inStorage==null){
            return null;
        }
        else {
            boolean flag=false;
            try {
                inStorageTab.UpdateInStorage(  inDate, storageNam, lister, inState,inIdName, inId);
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
    public Boolean UpdateState(String inState, Long inId){
        boolean flag=false;
        try {
            inStorageTab.updateState(  inState, inId);
            flag=true;
        }
        catch (Exception e){
            System.out.println(e);
        }
        if(flag){return true;}
        else {return false;}
    }
}

