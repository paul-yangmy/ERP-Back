package com.ymy.graduation.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author ymyum
 * @date 2020/1/20 14:32
 * @project 入库
 */
@Data
@Entity
@Table(name = "inStorageTab")
public class InStorage {
    @GeneratedValue
    @Id
    private Long inId;//入库单号(与采购单号对应)

    @Column(nullable = false)
    private String inIdName;//入库单名
    @Column(nullable = false)
    private Double inFee;//单据金额(购买单号)
    @Column(nullable = false)
    private Date inDate;//入库时间
    @Column(nullable = false)
    private String storageNam;//仓库类型
    @Column
    private String lister;//制表人
    @Column
    private String inState;//入库状态(已入库、入库中、采购中)
    @OneToOne
    /**单向连接采购表，修改入库中有入库状态*/
    @JoinColumn(name = "Purchase_buyId")
    private Purchase purchase;

    public String getInIdName() {
        return inIdName;
    }

    public void setInIdName(String inIdName) {
        this.inIdName = inIdName;
    }

    public Long getInId() {
        return inId;
    }

    public Double getInFee() {
        return inFee;
    }

    public Date getInDate() {
        return inDate;
    }

    public String getStorageNam() {
        return storageNam;
    }

    public String getLister() {
        return lister;
    }

    public String getInState() {
        return inState;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setInId(Long inId) {
        this.inId = inId;
    }

    public void setInFee(Double inFee) {
        this.inFee = inFee;
    }

    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    public void setStorageNam(String storageNam) {
        this.storageNam = storageNam;
    }

    public void setLister(String lister) {
        this.lister = lister;
    }

    public void setInState(String inState) {
        this.inState = inState;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }
}
