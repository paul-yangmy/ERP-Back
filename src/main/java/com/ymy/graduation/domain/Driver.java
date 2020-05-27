package com.ymy.graduation.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @author ymyum
 * @date 2020/1/20 14:45
 * @project 司机
 */
@Data
@Entity
@Table(name = "DriverTab")
public class Driver {
    @GeneratedValue
    @Id
    private Integer dId;//司机号

    @Column(nullable = false)
    private String dName;//司机名
    @Column(nullable = false)
    private String dPhone;//司机电话
    @Column(nullable = false)
    private String dState;//司机状态(默认 带班)

//    /**单向连接配送表，修改配送表中有配送任务的司机*/
//    @OneToMany(mappedBy = "driver",cascade = {CascadeType.ALL})
//    private List<Transportation> transportationList;
//
//    @JsonBackReference(value = "j2")
//    public Driver settransportationList(List<Transportation> transportationList) {
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

    public Integer getdId() {
        return dId;
    }

    public String getdName() {
        return dName;
    }

    public String getdPhone() {
        return dPhone;
    }

    public String getdState() {
        return dState;
    }

    public void setdId(Integer dId) {
        this.dId = dId;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public void setdPhone(String dPhone) {
        this.dPhone = dPhone;
    }

    public void setdState(String dState) {
        this.dState = dState;
    }
}
