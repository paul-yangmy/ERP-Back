package com.ymy.graduation.repository;

import com.ymy.graduation.domain.Driver;
import com.ymy.graduation.domain.Transportation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * @author ymyum
 * @date 2020/2/5 16:51
 * @project
 */
public interface transportationTab extends JpaRepository<Transportation,Integer> {
    /**查找*/
    @Query(value = "SELECT transportation From Transportation transportation WHERE transportation.transId=?1")
    Transportation findByTransId(Integer transId);
    @Query(value = "SELECT transportation From Transportation transportation WHERE transportation.transDate=?1")
    List<Transportation> findByTransDate(Date transDate);
    @Query(value = "SELECT transportation From Transportation transportation WHERE transportation.transName=?1")
    List<Transportation> findByLine(String transName);


    /**修改*/
    @Transactional
    @Modifying
    @Query(value = "UPDATE Transportation transportation SET transportation.transDate=?1,transportation.transName=?2 WHERE transportation.transId=?3")
    void UpdateTransportation(Date transDate,String transName,Integer transId);
    @Transactional
    @Modifying
    @Query(value = "UPDATE Transportation transportation SET transportation.driver=?1 WHERE transportation.transId=?2")
    void UpdateDriver(Driver driver, Integer transId);


}
