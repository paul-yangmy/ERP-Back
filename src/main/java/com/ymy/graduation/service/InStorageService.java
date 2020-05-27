package com.ymy.graduation.service;

import com.ymy.graduation.domain.InStorage;
import com.ymy.graduation.domain.Purchase;

import java.util.Date;
import java.util.List;

/**
 * @author ymyum
 * @date 2020/2/4 16:29
 * @project
 */
public interface InStorageService {
    /**创建*/
    String CreateInStorage(InStorage inStorage);
    /**删除*/
    String DeleteInStorage(Long inId);
    /**查找*/
    InStorage findByInId(Long inId);
    InStorage findByPurchase(Purchase purchase);
    List<InStorage> findAllInStorage();
    /**更改*/
    Boolean UpdateInStorage( Date inDate, String storageNam, String lister, String inState,String inIdName, Long inId);
    Boolean UpdateState(String state,Long inId);
}
