package com.ymy.graduation.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author ymyum
 * @date 2020/1/20 14:37
 * @project 配送
 */
@Data
@Entity
@Table(name = "transTab")
public class Transportation {
    @GeneratedValue
    @Id
    private Integer transId;//配送单号

    @Column(nullable = false)
    private Date transDate;//配送日期(按日期显示表)
    @Column(nullable = false)
    private String transName;//线路名称
    /** 多对一，司机（司机名称、电话）*/
    @ManyToOne
    @JoinColumn(name = "driver_dId")
    private Driver driver;

    /** 多对一，出库（订单数）*/
    @ManyToOne
    @JoinColumn(name = "outStorage_outId")
    private OutStorage outStorage;

    public Integer getTransId() {
        return transId;
    }

    public Date getTransDate() {
        return transDate;
    }


    public String getTransName() {
        return transName;
    }

    public Driver getDriver() {
        return driver;
    }

    public OutStorage getOutStorage() {
        return outStorage;
    }

    public void setTransId(Integer transId) {
        this.transId = transId;
    }

    public void setTransDate(Date transDate) {
        this.transDate = transDate;
    }

    public void setTransName(String transName) {
        this.transName = transName;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public void setOutStorage(OutStorage outStorage) {
        this.outStorage = outStorage;
    }
}