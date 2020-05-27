package com.ymy.graduation.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author ymyum
 * @date 2020/1/20 9:58
 * @project 检测报告
 */

@Data
@Entity//指定实体类 ;必须指定@Id
@Table(name = "TestReportTab")
public class TestReport {
    @GeneratedValue
    @Id
    private Integer testId;//用户号

//    /**一个检测报告对应多个商品*/
//    @OneToMany(mappedBy = "testReport",cascade = {CascadeType.ALL})
//    //拥有mappedBy注解的实体类为关系被维护端
//    //mappedBy="testReport"中的testReport是Commodity中的testReport属性
//    private List<Commodity> commodityList;
//
//    @JsonBackReference(value = "j8")
//    public TestReport setcommodityList(List<Commodity> commodityList) {
//        if (this.commodityList == null) {
//            this.commodityList = commodityList;
//            return this;
//        }
//        this.commodityList.clear();
//        this.commodityList.addAll(commodityList);
//        return this;
//    }
//    public List<Commodity> getCommodityList() {
//        return commodityList;
//    }

    @Column(nullable = false)
    private String testFacility;//检测机构
    @Column(nullable = false)
    private Date testDate;//检测时间
    @Column(nullable = false)
    private String testMan;//检测员
    @Column(nullable = false)
    private  String testSupplier;//供应商
    @Column
    private  Integer testΔA0;//对照值
    @Column
    private  Integer testΔA1;//测定值
    @Column
    private  Integer testInhibition;//抑制率

    public Integer getTestId() {
        return testId;
    }


    public String getTestFacility() {
        return testFacility;
    }

    public Date getTestDate() {
        return testDate;
    }

    public String getTestMan() {
        return testMan;
    }

    public String getTestSupplier() {
        return testSupplier;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }


    public void setTestFacility(String testFacility) {
        this.testFacility = testFacility;
    }

    public void setTestDate(Date testDate) {
        this.testDate = testDate;
    }

    public void setTestMan(String testMan) {
        this.testMan = testMan;
    }

    public void setTestSupplier(String testSupplier) {
        this.testSupplier = testSupplier;
    }

    public Integer getTestΔA0() {
        return testΔA0;
    }

    public Integer getTestΔA1() {
        return testΔA1;
    }

    public Integer getTestInhibition() {
        return testInhibition;
    }

    public void setTestΔA0(Integer testΔA0) {
        this.testΔA0 = testΔA0;
    }

    public void setTestΔA1(Integer testΔA1) {
        this.testΔA1 = testΔA1;
    }

    public void setTestInhibition(Integer testInhibition) {
        this.testInhibition = testInhibition;
    }
}
