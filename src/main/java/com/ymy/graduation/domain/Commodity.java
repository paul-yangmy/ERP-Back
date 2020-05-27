package com.ymy.graduation.domain;

import lombok.Data;

import javax.persistence.*;
//import java.util.List;

/**
 * @author ymyum
 * @date 2020/1/20 9:58
 * @project 商品
 */
@Data
@Entity//指定实体类 ;必须指定@Id
@Table(name = "CommodityTable")

public class Commodity {
    @GeneratedValue
    @Id
    private Integer itemId;

    @ManyToOne
    /**多对一，多个商品对应一批检测报告*/

    @JoinColumn(name = "testReport_testId")
    private TestReport testReport;

    @ManyToOne
    /**多对一，多个商品对应一个仓库*/
    @JoinColumn(name = "repository_repoId")
    private Repository repository;

    @Column(nullable = false)
    private String graph;//图片
    @Column(nullable = false)
    private String classes;//商品分类
    @Column(nullable = false)
    private String name;//商品名称
    @Column
    private String unit;//单位
    @Column(nullable = false)
    private Double price;//价格
    @Column(nullable = false)
    private String purchases;//供应类型(采购单采购类型)
    @Column(nullable = false)
    private String buyer;//供应员
    @Column(nullable = false)
    private String state;//状态(已上架、已下架)

    public Integer getItemId() {
        return itemId;
    }

    public String getGraph() {
        return graph;
    }

    public String getClasses() {
        return classes;
    }

    public String getName() {
        return name;
    }

    public String getUnit() {
        return unit;
    }

    public TestReport getTestReport() {
        return testReport;
    }

    public void setTestReport(TestReport testReport) {
        this.testReport = testReport;
    }

    public Double getPrice() {
        return price;
    }

    public String getPurchases() {
        return purchases;
    }

    public String getBuyer() {
        return buyer;
    }

    public String getState() {
        return state;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public void setGraph(String graph) {
        this.graph = graph;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setPurchases(String purchases) {
        this.purchases = purchases;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public void setState(String state) {
        this.state = state;
    }
}
