package com.ymy.graduation.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @author ymyum
 * @date 2020/1/20 10:46
 * @project 订单详情表
 */
@Data
@Entity
@Table(name = "orderDetailTab")
public class OrderDetail {
    @GeneratedValue
    @Id
    private Long orderId;

    @ManyToOne
    /**多对一，多个商品对应一个订单*/
    @JoinColumn(name = "indent_indId")
    private Indent indent;

    @OneToOne
    /**一对一，一个商品对应一个菜单上一个商品*/
    @JoinColumn(name = "commodity_itemId")
    private Commodity commodity;

    @Column(nullable = false)
    private Integer orderNum;//下单数量
    @Column(nullable = false)
    private Double orderCost;//下单单价
    @Column(nullable = true)
    private Double orderTotalCost;//总价=单价（由commodity提供）*数量
    @Column
    private String orderComment;//备注

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setIndent(Indent indent) {
        this.indent = indent;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public void setOrderCost(Double orderCost) {
        this.orderCost = orderCost;
    }

    public void setOrderComment(String orderComment) {
        this.orderComment = orderComment;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Indent getIndent() {
        return indent;
    }

    public Commodity getCommodity() {
        return commodity;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public Double getOrderCost() {
        return orderCost;
    }

    public String getOrderComment() {
        return orderComment;
    }

    public Double getOrderTotalCost() {
        return orderTotalCost;
    }

    public void setOrderTotalCost(Double orderTotalCost) {
        this.orderTotalCost = orderTotalCost;
    }
}
