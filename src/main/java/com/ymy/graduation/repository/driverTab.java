package com.ymy.graduation.repository;

import com.ymy.graduation.domain.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

/**
 * @author ymyum
 * @date 2020/2/5 16:52
 * @project
 */
public interface driverTab extends JpaRepository<Driver,Integer> {
    /**查找*/
    @Query(value = "SELECT driver From Driver driver WHERE driver.dId=?1")
    Driver findByDriverId(Integer dId);


    /**修改*/
    @Transactional
    @Modifying
    @Query(value = "UPDATE Driver driver SET driver.dName=?1,driver.dPhone=?2,driver.dState=?3 WHERE driver.dId=?4")
    void UpdateDriver(String dName, String dPhone, String dState, Integer dId);

    /**批量修改状态 */
    @Transactional
    @Modifying
    @Query(value = "UPDATE Driver driver SET driver.dState=?1 WHERE driver.dId=?2")
    void updateState(String dState,Integer dId);
}
