package com.ymy.graduation.service;

import com.ymy.graduation.domain.Commodity;
import com.ymy.graduation.domain.Finance;
import com.ymy.graduation.domain.Indent;
import com.ymy.graduation.domain.OrderDetail;

import java.util.List;

/**
 * @author ymyum
 * @date 2020/2/13 17:38
 * @project
 */
public interface IndentService {
    /**创建*/
    Boolean CreateIndentDetail(OrderDetail orderDetail);
    Indent CreateIndent(Indent indent);
    /**查找*/
    Indent findByIndentId(Integer indId);
    List<Indent> findAllIndent();
    List<Indent> findByFinance(Finance finance);
    Commodity findByName(String name);
    /**详情表查找*/
    OrderDetail findByOrderId(Long orderId);
    List<OrderDetail> findByIndentDetailId(Indent indent_indId);
    OrderDetail findByCommodity(Commodity commodity);
    /**更改*/
    Boolean UpdateIndent(String indState,Integer indId);
    Boolean UpdateFinance(Finance finance,Integer indId);
}
