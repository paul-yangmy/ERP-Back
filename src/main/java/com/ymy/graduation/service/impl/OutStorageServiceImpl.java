package com.ymy.graduation.service.impl;

import com.ymy.graduation.domain.OutStorage;
import com.ymy.graduation.repository.outStorageTab;
import com.ymy.graduation.service.OutStorageService;
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
public class OutStorageServiceImpl implements OutStorageService {
    @Autowired
    private outStorageTab outStorageTab;
    @Override
    public String CreateOutStorage(OutStorage outStorage) {
        OutStorage outStorage1= outStorageTab.findByOutId(outStorage.getOutId());
        if(outStorage1!=null){
            return "Exists";//存在同名
        }
        else
        {
            boolean flag=false;
            try {
                outStorageTab.save(outStorage);
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
    public String DeleteOutStorage(Long outId) {
        OutStorage outStorage= outStorageTab.findByOutId(outId);
        if(outStorage==null){
            return "null";
        }
        else
        {
            boolean flag=false;
            try {
                outStorageTab.delete(outStorage);
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
    public OutStorage findByOutId(Long outId) {
        return outStorageTab.findByOutId(outId);
    }

    @Override
    public List<OutStorage> findAllOutStorage() {
        return outStorageTab.findAll();
    }

    @Override
    public Boolean UpdateOutStorage( Date outDate, String storageNam, String lister, String outState,String customer,String outName,Long outId) {
        OutStorage outStorage=outStorageTab.findByOutId(outId);
        if(outStorage==null){
            return null;
        }
        else {
            boolean flag=false;
            try {
                outStorageTab.UpdateOutStorage(  outDate,  storageNam,  lister,  outState, customer,outName, outId);
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
    public Boolean UpdateState(String outState, Long outId){
        boolean flag=false;
        try {
            outStorageTab.updateState(  outState, outId);
            flag=true;
        }
        catch (Exception e){
            System.out.println(e);
        }
        if(flag){return true;}
        else {return false;}
    }
}
