package com.ymy.graduation.service.impl;

import com.ymy.graduation.domain.Finance;
import com.ymy.graduation.domain.Purchase;
import com.ymy.graduation.domain.PurchaseDetail;
import com.ymy.graduation.repository.buyDetailTab;
import com.ymy.graduation.repository.purchaseTab;
import com.ymy.graduation.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author ymyum
 * @date 2020/1/31 23:37
 * @project
 */
@Service
public class PurchaseServiceImpl implements PurchaseService {
    @Autowired
    private purchaseTab purchaseTab;
    @Autowired
    private buyDetailTab buyDetailTab;

    @Override
    public Purchase CreatePurchase(Purchase purchase) {
        try {
            return purchaseTab.save(purchase);
        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public Boolean CreatePurchaseDetail(PurchaseDetail pd) {
        boolean flag=false;
        try {
            buyDetailTab.save(pd);
            flag=true;
        }
        catch (Exception e){
            System.out.println(e);
        }
        return flag;
    }

    @Override
    public Boolean DeletePurchase(Long buyId) {
        Purchase purchase= purchaseTab.findByBuyId(buyId);
        if(purchase==null){
            return null;
        }
        else
        {
            boolean flag=false;
            try {
                purchaseTab.delete(purchase);
                flag=true;
            }
            catch (Exception e){
                System.out.println(e);
            }
            return flag;
        }
    }

    @Override
    public Boolean DeletePurchaseDetail(Long orderBuyId) {
        PurchaseDetail pd= buyDetailTab.findByOrderBuyId(orderBuyId);
        if(pd==null){
            return null;
        }
        else
        {
            boolean flag=false;
            try {
                buyDetailTab.delete(pd);
                flag=true;
            }
            catch (Exception e){
                System.out.println(e);
            }
            return flag;
        }
    }

    @Override
    public Purchase findByBuyId(Long buyId) {
        Purchase purchase= purchaseTab.findByBuyId(buyId);
        if(purchase==null){
            return null;
        }
        else {
            return purchase;
        }
    }

    @Override
    public List<Purchase> findAllPurchase() {
        return purchaseTab.findAll();
    }

    @Override
    public List<Purchase> findByFinance(Finance finance) {
        return purchaseTab.findByFinance(finance);
    }

    @Override
    public PurchaseDetail findByOrderBuyId(Long orderBuyId) {
        return buyDetailTab.findByOrderBuyId(orderBuyId);
    }
    @Override
    public List<PurchaseDetail> findByPurchaseDetail(Purchase purchase) {
        return buyDetailTab.findByPurchaseDetailId(purchase);
    }

    @Override
    public Boolean UpdatePurchase(String buyType, String buyer, Double buyFee, String buyLister, String buyState, Date buyDate, Long buyId) {
        Purchase purchase=purchaseTab.findByBuyId(buyId);
        if(purchase==null){
            return null;
        }
        else {
            boolean flag=false;
            try {
                purchaseTab.UpdatePurchase(buyType, buyer, buyFee, buyLister, buyState,buyDate, buyId);
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
    public Boolean UpdateState(String buyState, Long buyId){
        boolean flag=false;
        try {
            purchaseTab.updateState(  buyState, buyId);
            flag=true;
        }
        catch (Exception e){
            System.out.println(e);
        }
        if(flag){return true;}
        else {return false;}
    }
    @Override
    public Boolean UpdateFinance(Finance finance, Long buyId){
        boolean flag=false;
        try {
            purchaseTab.updateFinance(  finance, buyId);
            flag=true;
        }
        catch (Exception e){
            System.out.println(e);
        }
        if(flag){return true;}
        else {return false;}
    }
}

