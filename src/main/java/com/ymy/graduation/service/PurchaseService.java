package com.ymy.graduation.service;

import com.ymy.graduation.domain.Finance;
import com.ymy.graduation.domain.Purchase;
import com.ymy.graduation.domain.PurchaseDetail;

import java.util.Date;
import java.util.List;

/**
 * @author ymyum
 * @date 2020/1/31 23:32
 * @project
 */
public interface PurchaseService {
    /**创建*/
    Purchase CreatePurchase(Purchase purchase);
    Boolean CreatePurchaseDetail(PurchaseDetail purchaseDetail);
    /**删除*/
    Boolean DeletePurchase(Long buyId);
    Boolean DeletePurchaseDetail(Long orderBuyId);
    /**查找*/
    Purchase findByBuyId(Long buyId);
    List<Purchase> findAllPurchase();
    List<Purchase> findByFinance(Finance finance);
    /**详情表查找*/
    PurchaseDetail findByOrderBuyId(Long orderBuyId);
    List<PurchaseDetail> findByPurchaseDetail(Purchase purchase);
    /**更改*/
    Boolean UpdatePurchase(String buyType, String buyer, Double buyFee, String buyLister, String buyState, Date buyDate, Long buyId);
    Boolean UpdateState(String buyState,Long buyId);
    Boolean UpdateFinance(Finance finance,Long buyId);
}