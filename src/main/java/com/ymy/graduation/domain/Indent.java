package com.ymy.graduation.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author ymyum
 * @date 2020/1/20 9:58
 * @project 订单
 */

@Data
@Entity
@Table(name = "IndentTab")
public class Indent {
    @GeneratedValue
    @Id
    private Integer indId;//订单号

//    @OneToMany(mappedBy = "indent", cascade=CascadeType.ALL)
//    private List<OrderDetail> orderDetailList;
//    @JsonBackReference(value = "j4")
//    public Indent setOrderDetailList(List<OrderDetail> orderDetailList) {
//        if (this.orderDetailList == null) {
//            this.orderDetailList = orderDetailList;
//            return this;
//        }
//        this.orderDetailList.clear();
//        this.orderDetailList.addAll(orderDetailList);
//        return this;
//    }
//    public List<OrderDetail> getOrderDetailList() {
//        return orderDetailList;
//    }

    @ManyToOne
    /**多对一，多个订单对应一个报表*/
    @JoinColumn(name = "finance_financeId")
    private Finance finance;
    public Finance getFinance() {
        return finance;
    }

    public void setFinance(Finance finance) {
        this.finance = finance;
    }

    @Column(nullable = false)
    private String customerName;//客户名
    @Column
    private Date submitTime;//提交时间
    @Column(nullable = false)
    private String indState;//订单状态(待发货、配送中、已完成)
    @Column
    private String indSource;//订单来源
    @Column(nullable = false)
    private Integer customerNum;//客户编号
    @Column(nullable = true)
    private Double totalAmount;//下单总额
    @Column(nullable = true)
    private Double transFee;//运费
    @Column
    private String transLine;//线路
    @Column
    private Date transTime;//发货时间
    @Column
    private String transDriver;//司机
    @Column
    private Date arrTime;//收获时间

    public Integer getIndId() {
        return indId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public String getIndState() {
        return indState;
    }

    public String getIndSource() {
        return indSource;
    }

    public Integer getCustomerNum() {
        return customerNum;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public Double getTransFee() {
        return transFee;
    }

    public String getTransLine() {
        return transLine;
    }

    public Date getTransTime() {
        return transTime;
    }

    public String getTransDriver() {
        return transDriver;
    }

    public Date getArrTime() {
        return arrTime;
    }

    public void setIndId(Integer indId) {
        this.indId = indId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public void setIndState(String indState) {
        this.indState = indState;
    }

    public void setIndSource(String indSource) {
        this.indSource = indSource;
    }

    public void setCustomerNum(Integer customerNum) {
        this.customerNum = customerNum;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setTransFee(Double transFee) {
        this.transFee = transFee;
    }

    public void setTransLine(String transLine) {
        this.transLine = transLine;
    }

    public void setTransTime(Date transTime) {
        this.transTime = transTime;
    }

    public void setTransDriver(String transDriver) {
        this.transDriver = transDriver;
    }

    public void setArrTime(Date arrTime) {
        this.arrTime = arrTime;
    }
}
