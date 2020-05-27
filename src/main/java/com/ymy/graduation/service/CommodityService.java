package com.ymy.graduation.service;

import com.ymy.graduation.domain.Commodity;

import java.util.List;

/**
 * Created by ymyum on 2020/1/16
 */
public interface CommodityService {
    /**创建*/
    String CreateCommodity(Commodity commodity);
    /**删除*/
    String DeleteCommodity(Integer itemId);
    /**查找*/
    Commodity findByItemId(Integer itemId);
    Commodity findByName(String name);
    List<Commodity> findAllCommodity();
    /**更改*/
    Boolean UpdateCommodity(String graph,String classes,String name,String unit, Double price,String purchases,String buyer,String state,Integer itemId);
    Boolean UpdateState(String state,Integer itemId);
}
