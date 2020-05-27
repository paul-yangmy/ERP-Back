package com.ymy.graduation.repository;

import com.ymy.graduation.domain.InStorage;
import com.ymy.graduation.domain.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;

/**
 * @author ymyum
 * @date 2020/2/4 15:54
 * @project
 */
public interface inStorageTab extends JpaRepository<InStorage,Integer> {
    /**查找*/
    @Query(value = "SELECT instorage From InStorage instorage WHERE instorage.inId=?1")
    InStorage findByInId(Long inId);
    @Query(value = "SELECT instorage From InStorage instorage WHERE instorage.purchase=?1")
    InStorage findByPurchase(Purchase p);


    /**修改*/
    @Transactional
    @Modifying
    @Query(value = "UPDATE InStorage instorage SET instorage.inDate=?1,instorage.storageNam=?2,instorage.lister=?3,instorage.inState=?4,instorage.inIdName=?5 WHERE instorage.inId=?6")
    void UpdateInStorage(Date inDate,String storageNam,String lister,String inState,String inIdName,Long inId);

    /**批量修改状态*/
    @Transactional
    @Modifying
    @Query(value = "UPDATE InStorage instorage SET instorage.inState=?1 WHERE instorage.inId=?2")
    void updateState(String inState,Long inId);
}
