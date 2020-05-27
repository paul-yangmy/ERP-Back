package com.ymy.graduation.repository;

import com.ymy.graduation.domain.OutStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;

/**
 * @author ymyum
 * @date 2020/2/4 15:55
 * @project
 */
public interface outStorageTab extends JpaRepository<OutStorage,Integer> {
    /**查找*/
    @Query(value = "SELECT outstorage From OutStorage outstorage WHERE outstorage.outId=?1")
    OutStorage findByOutId(Long outId);


    /**修改*/
    @Transactional
    @Modifying
    @Query(value = "UPDATE OutStorage outstorage SET outstorage.outDate=?1,outstorage.storageNam=?2,outstorage.lister=?3,outstorage.outState=?4,outstorage.customer=?5,outstorage.outName=?6 WHERE outstorage.outId=?7")
    void UpdateOutStorage( Date outDate, String storageNam, String lister, String outState,String customer,String outName,Long outId);

    /**批量修改状态*/
    @Transactional
    @Modifying
    @Query(value = "UPDATE OutStorage outstorage SET outstorage.outState=?1 WHERE outstorage.outId=?2")
    void updateState(String outState,Long outId);
}

