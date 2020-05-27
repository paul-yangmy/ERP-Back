package com.ymy.graduation.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @author ymyum
 * @date 2020/1/20 14:14
 * @project 仓库
 */
@Data
@Entity
@Table(name = "repoTab")

public class Repository {
    @GeneratedValue
    @Id
    private Long repoId;

    @Column(nullable = false)
    private String repoState;//库房类型(按库房类型显示),与commodity的分类挂钩

    @Column(nullable = false)
    private String repoName;//库房名称

    @Column(nullable = false)
    private String repoItem;//库存商品名称

//    @OneToMany(mappedBy = "repository",cascade=CascadeType.ALL)
//    private List<Commodity> commodityList;
//
//    @JsonBackReference(value = "j7")
//    public void setCommodityList(List<Commodity> commodityList) {
//        this.commodityList = commodityList;
//    }
//    public List<Commodity> getCommodityList() {
//        return commodityList;
//    }

    @OneToOne
    /**单向连接入库表，修改入库中有入库状态*/
    @JoinColumn(name = "InStorage")
    private InStorage inStorage;
    @OneToOne
    /**单向连接出库表，修改出库中有出库状态*/
    @JoinColumn(name = "OutStorage")
    private OutStorage outStorage;

    public void setInStorage(InStorage inStorage) {
        this.inStorage = inStorage;
    }

    public void setOutStorage(OutStorage outStorage) {
        this.outStorage = outStorage;
    }

    public InStorage getInStorage() {
        return inStorage;
    }

    public OutStorage getOutStorage() {
        return outStorage;
    }

    @Column(nullable = false)
    private Integer repoNum;//库存数量

    @Column(nullable = false)
    private Integer repoUpNum;//库存上限

    @Column(nullable = false)
    private Double repoFee;//库存维护费

    public Long getRepoId() {
        return repoId;
    }

    public String getRepoState() {
        return repoState;
    }

    public String getRepoItem() {
        return repoItem;
    }

    public Integer getRepoNum() {
        return repoNum;
    }

    public Integer getRepoUpNum() {
        return repoUpNum;
    }

    public Double getRepoFee() {
        return repoFee;
    }

    public void setRepoId(Long repoId) {
        this.repoId = repoId;
    }

    public void setRepoState(String repoState) {
        this.repoState = repoState;
    }

    public void setRepoItem(String repoItem) {
        this.repoItem = repoItem;
    }

    public void setRepoNum(Integer repoNum) {
        this.repoNum = repoNum;
    }

    public void setRepoUpNum(Integer repoUpNum) {
        this.repoUpNum = repoUpNum;
    }

    public void setRepoFee(Double repoFee) {
        this.repoFee = repoFee;
    }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }
}
