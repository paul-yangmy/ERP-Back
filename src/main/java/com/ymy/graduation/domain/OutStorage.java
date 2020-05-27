package com.ymy.graduation.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author ymyum
 * @date 2020/1/20 14:32
 * @project 出库
 */
@Data
@Entity
@Table(name = "outStorageTab")
public class OutStorage {
    @GeneratedValue
    @Id
    private Long outId;//出库单号(与配送单号对应)

    @Column(nullable = false)
    private String outName;//出库单号
    @Column(nullable = true)
    private Double outFee;//单据金额(订单单号)
    @Column(nullable = false)
    private Date outDate;//出库时间
    @Column(nullable = false)
    private String storageNam;//仓库类型
    @Column
    private String customer;//客户名
    @Column
    private String lister;//制表人
    @Column
    private String outState;//出库状态(已出库、出库中)

//    /**单向连接配送表，修改配送表中有配送商品*/
//    @OneToMany(mappedBy = "outStorage",cascade = {CascadeType.ALL})
//    private List<Transportation> transportationList;
//
//    @JsonBackReference(value = "j1")
//    public OutStorage setTransportationList(List<Transportation> transportationList) {
//        if (this.transportationList == null) {
//            this.transportationList = transportationList;
//            return this;
//        }
//        this.transportationList.clear();
//        this.transportationList.addAll(transportationList);
//        return this;
//    }
//    public List<Transportation> getTransportationList() {
//        return transportationList;
//    }

    @OneToOne
    /**单向连接订单*/
    @JoinColumn(name = "Indent_indId")
    private Indent indent;

    public Indent getIndent() {
        return indent;
    }

    public void setIndent(Indent indent) {
        this.indent = indent;
    }

    public Long getOutId() {
        return outId;
    }

    public Double getOutFee() {
        return outFee;
    }

    public Date getOutDate() {
        return outDate;
    }

    public String getStorageNam() {
        return storageNam;
    }

    public String getCustomer() {
        return customer;
    }

    public String getLister() {
        return lister;
    }

    public String getOutState() {
        return outState;
    }


    public void setOutId(Long outId) {
        this.outId = outId;
    }

    public void setOutFee(Double outFee) {
        this.outFee = outFee;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }

    public void setStorageNam(String storageNam) {
        this.storageNam = storageNam;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public void setLister(String lister) {
        this.lister = lister;
    }

    public void setOutState(String outState) {
        this.outState = outState;
    }

    public String getOutName() {
        return outName;
    }

    public void setOutName(String outName) {
        this.outName = outName;
    }
}

