package com.ymy.graduation.service;

import com.ymy.graduation.domain.Driver;
import com.ymy.graduation.domain.Transportation;

import java.util.Date;
import java.util.List;

/**
 * @author ymyum
 * @date 2020/2/5 17:06
 * @project
 */
public interface TransportationService {
    /**创建*/
    Transportation CreateTransportation(Transportation transportation);
    /**删除*/
    String DeleteTransportation(Integer transId);
    /**查找*/
    Transportation findByTransId(Integer transId);
    List<Transportation> findByTransDate(Date transDate);
    List<Transportation> findByLine(String transName);
    List<Transportation> findAllTransportation();
    /**更改*/
    Boolean UpdateTransportation(Date transDate,String transName,Integer transId);
    Boolean UpdateDriver(Driver driver, Integer transId);

}
