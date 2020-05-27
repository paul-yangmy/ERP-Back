package com.ymy.graduation.service;

import com.ymy.graduation.domain.Driver;


import java.util.List;

/**
 * @author ymyum
 * @date 2020/2/5 17:15
 * @project
 */
public interface DriverService {
    /**创建*/
    String CreateDriver(Driver driver);
    /**删除*/
    String DeleteDriver(Integer dId);
    /**查找*/
    Driver findByDriverId(Integer dId);
    List<Driver> findAllDriver();
    /**更改*/
    Boolean UpdateDriver(String dName, String dPhone, String dState, Integer dId);
    Boolean UpdateState(String dState,Integer dId);
}
