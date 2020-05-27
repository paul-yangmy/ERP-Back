package com.ymy.graduation.repository;

import com.ymy.graduation.domain.Finance;
import com.ymy.graduation.domain.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * @author ymyum
 * @date 2020/1/31 23:22
 * @project
 */
public interface purchaseTab extends JpaRepository<Purchase,Integer> {
    /**查找*/
    @Query(value = "SELECT purchase From Purchase purchase WHERE purchase.buyId=?1")
    Purchase findByBuyId(Long buyId);
    @Query(value = "SELECT purchase From Purchase purchase WHERE purchase.finance=?1")
    List<Purchase> findByFinance(Finance finance);
    /**修改*/
    @Transactional
    @Modifying
    @Query(value = "UPDATE Purchase purchase SET purchase.buyType=?1,purchase.buyer=?2,purchase.buyFee=?3,purchase.buyLister=?4,purchase.buyState=?5,purchase.buyDate=?6 WHERE purchase.buyId=?7")
    void UpdatePurchase(String buyType, String buyer, Double buyFee, String buyLister, String buyState, Date buyDate, Long buyId);
    @Transactional
    @Modifying
    @Query(value = "UPDATE Purchase purchase SET purchase.finance=?1 WHERE purchase.buyId=?2")
    void updateFinance(Finance finance,Long buyId);
    /**批量修改状态*/
    @Transactional
    @Modifying
    @Query(value = "UPDATE Purchase purchase SET purchase.buyState=?1 WHERE purchase.buyId=?2")
    void updateState(String buyState,Long buyId);

}