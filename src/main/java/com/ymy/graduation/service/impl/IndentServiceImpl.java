package com.ymy.graduation.service.impl;


import com.ymy.graduation.domain.Commodity;
import com.ymy.graduation.domain.Finance;
import com.ymy.graduation.domain.Indent;
import com.ymy.graduation.domain.OrderDetail;
import com.ymy.graduation.repository.commodityTab;
import com.ymy.graduation.repository.detailTab;
import com.ymy.graduation.repository.indentTab;
import com.ymy.graduation.service.IndentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ymyum
 * @date 2020/2/13 17:54
 * @project
 */
@Service
public class IndentServiceImpl implements IndentService {
    @Autowired
    private indentTab indentTab;
    @Autowired
    private detailTab detailTab;
    @Autowired
    private commodityTab commodityTab;
    @Override
    public Indent findByIndentId(Integer indId) {
        return indentTab.findByIndentId(indId);
    }

    @Override
    public List<Indent> findAllIndent() {
        return indentTab.findAll();
    }
    @Override
    public Commodity findByName(String name) {
        return commodityTab.findByName(name);
    }
    @Override
    public List<Indent> findByFinance(Finance finance) {
        return indentTab.findByFinance(finance);
    }
    @Override
    public OrderDetail findByOrderId(Long orderId) {
        return detailTab.findByOrderId(orderId);
    }
    @Override
    public List<OrderDetail> findByIndentDetailId(Indent indent_indId) {
        return detailTab.findByIndentDetailId(indent_indId);
    }
    @Override
    public OrderDetail findByCommodity(Commodity commodity) {
        return detailTab.findByCommodity(commodity);
    }

    @Override
    public Indent CreateIndent(Indent indent) {
        try {
            return indentTab.save(indent);
        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public Boolean CreateIndentDetail(OrderDetail od) {
        boolean flag=false;
        try {
            detailTab.save(od);
            flag=true;
        }
        catch (Exception e){
            System.out.println(e);
        }
        return flag;
    }
    @Override
    public Boolean UpdateIndent(String indState,  Integer indId) {
        Indent indent=indentTab.findByIndentId(indId);
        if(indent==null){
            return null;
        }
        else {
            boolean flag=false;
            try {
                indentTab.UpdateIndent(indState, indId);
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
    public Boolean UpdateFinance(Finance finance,  Integer indId) {
        boolean flag=false;
        try {
            indentTab.UpdateFinance(  finance, indId);
            flag=true;
        }
        catch (Exception e){
            System.out.println(e);
        }
        if(flag){return true;}
        else {return false;}
    }
}
