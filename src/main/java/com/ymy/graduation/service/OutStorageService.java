package com.ymy.graduation.service;

import com.ymy.graduation.domain.OutStorage;

import java.util.Date;
import java.util.List;

/**
 * @author ymyum
 * @date 2020/2/4 16:29
 * @project
 */
public interface OutStorageService {
    /**创建*/
    String CreateOutStorage(OutStorage outStorage);
    /**删除*/
    String DeleteOutStorage(Long outId);
    /**查找*/
    OutStorage findByOutId(Long outId);
    List<OutStorage> findAllOutStorage();
    /**更改*/
    Boolean UpdateOutStorage( Date outDate, String storageNam, String lister, String outState,String customer,String outName,Long outId);
    Boolean UpdateState(String state, Long outId);
}
