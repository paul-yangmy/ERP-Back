package com.ymy.graduation.repository;

import com.ymy.graduation.domain.Commodity;
import com.ymy.graduation.domain.Indent;
import com.ymy.graduation.domain.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author ymyum
 * @date 2020/2/14 14:51
 * @project
 */
public interface detailTab extends JpaRepository<OrderDetail,Integer> {
    /**查找*/
    @Query(value = "SELECT detail From OrderDetail detail WHERE detail.orderId=?1")
    OrderDetail findByOrderId(Long orderId);

    @Query(value = "SELECT detail From OrderDetail detail WHERE detail.indent=?1")
    List<OrderDetail> findByIndentDetailId(Indent indent);
    @Query(value = "SELECT detail From OrderDetail detail WHERE detail.commodity=?1")
    OrderDetail findByCommodity(Commodity commodity);

}