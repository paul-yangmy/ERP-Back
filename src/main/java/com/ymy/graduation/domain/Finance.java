package com.ymy.graduation.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity//指定实体类 ;必须指定@Id
@Table(name = "FinanceTable")
public class Finance {
    @GeneratedValue
    @Id
    private Long financeId;

//    @OneToMany(mappedBy = "finance", cascade=CascadeType.ALL)
//    private List<Indent> indentList;
//    @JsonBackReference(value = "j5")
//    public Finance setIndentList(List<Indent> indentList) {
//        if (this.indentList == null) {
//            this.indentList = indentList;
//            return this;
//        }
//        this.indentList.clear();
//        this.indentList.addAll(indentList);
//        return this;
//    }
//    public List<Indent> getIndentList() {
//        return indentList;
//    }
//
//    @OneToMany(mappedBy = "finance", cascade=CascadeType.ALL)
//    private List<Purchase> purchaseList;
//    @JsonBackReference(value = "j9")
//    public Finance setPurchaseList(List<Purchase> purchaseList) {
//        if (this.purchaseList == null) {
//            this.purchaseList = purchaseList;
//            return this;
//        }
//        this.purchaseList.clear();
//        this.purchaseList.addAll(purchaseList);
//        return this;
//    }
//    public List<Purchase> getPurchaseList() {
//        return purchaseList;
//    }

    public Long getFinanceId() {
        return financeId;
    }

    public void setFinanceId(Long financeId) {
        this.financeId = financeId;
    }
    @Column(nullable = false)
    private String finName;//报表名称

    public String getFinName() {
        return finName;
    }

    public void setFinName(String finName) {
        this.finName = finName;
    }

    @Column(nullable = false)
    private String finState;//报表审核状态
    @Column(nullable = false)
    private Date submitTime;//提交时间
    @Column(nullable = false)
    private Double finFee;//报单

    public Double getFinFee() {
        return finFee;
    }

    public void setFinFee(Double finFee) {
        this.finFee = finFee;
    }

    public String getFinState() {
        return finState;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setFinState(String finState) {
        this.finState = finState;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }
}
