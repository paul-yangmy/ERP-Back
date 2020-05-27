package com.ymy.graduation.service.impl;

import com.ymy.graduation.domain.Commodity;
import com.ymy.graduation.repository.commodityTab;
import com.ymy.graduation.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ymyum on 2020/1/16
 * @author ymyum
 */
@Service
public class CommodityServiceImpl implements CommodityService {
    @Autowired
    private commodityTab commodityTab;
    @Override
    public String CreateCommodity(Commodity commodity) {
        Commodity commodity1= commodityTab.findByItemId(commodity.getItemId());
        if(commodity1!=null){
            return "Exists";//存在同名
        }
        else
        {
            boolean flag=false;
            try {
                commodityTab.save(commodity);
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
    public String DeleteCommodity(Integer itemId) {
        Commodity commodity= commodityTab.findByItemId(itemId);
        if(commodity==null){
            return "null";
        }
        else
        {
            boolean flag=false;
            try {
                commodityTab.delete(commodity);
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
    public Commodity findByItemId(Integer itemId) {
        return commodityTab.findByItemId(itemId);
    }

    @Override
    public Commodity findByName(String name) {
        return commodityTab.findByName(name);
    }

    @Override
    public List<Commodity> findAllCommodity() {
        return commodityTab.findAll();
    }

    @Override
    public Boolean UpdateCommodity( String graph,String classes, String name, String unit, Double price, String purchases, String buyer, String state, Integer itemId) {
        Commodity commodity=commodityTab.findByItemId(itemId);
        if(commodity==null){
            return null;
        }
        else {
            boolean flag=false;
            try {
                commodityTab.UpdateCommodity( graph, classes, name, unit,  price, purchases, buyer, state, itemId);
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
    public Boolean UpdateState(String state, Integer itemId){
        boolean flag=false;
        try {
            commodityTab.updateState(  state, itemId);
            flag=true;
        }
        catch (Exception e){
            System.out.println(e);
        }
        if(flag){return true;}
        else {return false;}
    }
}
