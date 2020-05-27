package com.ymy.graduation.repository;

import com.ymy.graduation.domain.Purchase;
import com.ymy.graduation.domain.PurchaseDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author ymyum
 * @date 2020/2/15 14:51
 * @project
 */
public interface buyDetailTab extends JpaRepository<PurchaseDetail,Integer> {
    /**查找*/
    @Query(value = "SELECT detail From PurchaseDetail detail WHERE detail.orderBuyId=?1")
    PurchaseDetail findByOrderBuyId(Long orderBuyId);

    @Query(value = "SELECT detail From PurchaseDetail detail WHERE detail.purchase=?1")
    List<PurchaseDetail> findByPurchaseDetailId(Purchase purchase);

}
