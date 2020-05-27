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
@Table(name = "purchaseDetailTab")
public class PurchaseDetail {
    @GeneratedValue
    @Id
    private Long orderBuyId;

    @ManyToOne
    /**多对一，多个商品对应一个订单*/
    @JoinColumn(name = "purchase_buyId")
    private Purchase purchase;

    @OneToOne
    /**一对一，一个商品对应一个菜单上一个商品*/
    @JoinColumn(name = "commodity")
    private Commodity commodity;

    @Column(nullable = false)
    private Integer buyNum;//采购数量
    @Column(nullable = false)
    private Double buyPrice;//采购单价
    @Column(nullable = true)
    private Double buyTotal;//采购总额
    @Column
    private String buyComment;//备注

    public Long getOrderBuyId() {
        return orderBuyId;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public Commodity getCommodity() {
        return commodity;
    }

    public Integer getBuyNum() {
        return buyNum;
    }

    public Double getBuyPrice() {
        return buyPrice;
    }

    public Double getBuyTotal() {
        return buyTotal;
    }

    public String getBuyComment() {
        return buyComment;
    }

    public void setOrderBuyId(Long orderBuyId) {
        this.orderBuyId = orderBuyId;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    public void setBuyNum(Integer buyNum) {
        this.buyNum = buyNum;
    }

    public void setBuyPrice(Double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public void setBuyTotal(Double buyTotal) {
        this.buyTotal = buyTotal;
    }

    public void setBuyComment(String buyComment) {
        this.buyComment = buyComment;
    }
}
