package com.ymy.graduation.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author ymyum
 * @date 2020/1/20 9:58
 * @project 采购单
 */

@Data
@Entity
@Table(name = "PurchaseTab")
public class Purchase {
    @GeneratedValue
    @Id
    private Long buyId;//采购单号

//    @OneToMany(mappedBy = "purchase", cascade=CascadeType.ALL)
//    private List<PurchaseDetail> purchaseDetailList;
//    @JsonBackReference(value = "j6")
//    public Purchase setpurchaseDetailList(List<PurchaseDetail> purchaseDetailList) {
//        if (this.purchaseDetailList == null) {
//            this.purchaseDetailList = purchaseDetailList;
//            return this;
//        }
//        this.purchaseDetailList.clear();
//        this.purchaseDetailList.addAll(purchaseDetailList);
//        return this;
//    }
//    public List<PurchaseDetail> getPurchaseDetailList() {
//        return purchaseDetailList;
//    }

    @ManyToOne
    /**多对一，多个采购单对应一个报表*/
    @JoinColumn(name = "finance_financeId")
    private Finance finance;

    public Finance getFinance() {
        return finance;
    }

    public void setFinance(Finance finance) {
        this.finance = finance;
    }

    @Column(nullable = false)
    private String buyType;//采购类型（供应商、市场自采)
    @Column(nullable = false)
    private String buyer;//供应商、采购员
    @Column(nullable = true)
    private Double buyFee;//采购总额
    @Column(nullable = false)
    private String buyLister;//制单人
    @Column(nullable = false)
    private Date buyDate;//采购时间
    @Column(nullable = false)
    private String buyState;//采购状态（采购中、待采购、已收货）

    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    public Long getBuyId() {
        return buyId;
    }

    public String getBuyType() {
        return buyType;
    }

    public String getBuyer() {
        return buyer;
    }

    public Double getBuyFee() {
        return buyFee;
    }

    public String getBuyLister() {
        return buyLister;
    }

    public String getBuyState() {
        return buyState;
    }

    public void setBuyId(Long buyId) {
        this.buyId = buyId;
    }

    public void setBuyType(String buyType) {
        this.buyType = buyType;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public void setBuyFee(Double buyFee) {
        this.buyFee = buyFee;
    }

    public void setBuyLister(String buyLister) {
        this.buyLister = buyLister;
    }

    public void setBuyState(String buyState) {
        this.buyState = buyState;
    }
}
