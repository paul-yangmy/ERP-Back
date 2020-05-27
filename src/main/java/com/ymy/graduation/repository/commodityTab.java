package com.ymy.graduation.repository;

import com.ymy.graduation.domain.Commodity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

/**
 * Created by ymyum on 2020/1/16
 */
public interface commodityTab extends JpaRepository<Commodity,Integer> {
    /**查找*/
    @Query(value = "SELECT commodity From Commodity commodity WHERE commodity.itemId=?1")
    Commodity findByItemId(Integer itemId);
    @Query(value = "SELECT commodity From Commodity commodity WHERE commodity.name=?1")
    Commodity findByName(String name);

    /**修改*/
    @Transactional
    @Modifying
    @Query(value = "UPDATE Commodity commodity SET commodity.graph=?1,commodity.classes=?2,commodity.name=?3,commodity.unit=?4,commodity.price=?5,commodity.purchases=?6,commodity.buyer=?7,commodity.state=?8 WHERE commodity.itemId=?9")
    void UpdateCommodity(String graph,String classes,String name,String unit, Double price,String purchases,String buyer,String state,Integer itemId);
     /**批量上架下架*/
    @Transactional
    @Modifying
    @Query(value = "UPDATE Commodity commodity SET commodity.state=?1 WHERE commodity.itemId=?2")
    void updateState(String state,Integer itemId);
}
