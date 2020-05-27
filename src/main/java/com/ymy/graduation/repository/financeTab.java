package com.ymy.graduation.repository;

import com.ymy.graduation.domain.Finance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface financeTab extends JpaRepository<Finance,Integer> {
    /**查找*/
    @Query(value = "SELECT finance From Finance finance WHERE finance.financeId=?1")
    Finance findByFinanceId(Long financeId);
    @Query(value = "SELECT finance From Finance finance WHERE finance.submitTime=?1")
    List<Finance> findByDate(Date submitTime);
    /**查找开支*/
    @Query("select DISTINCT purchase from Purchase purchase left join purchase.finance as p  where p.financeId = ?1")
    public List<Finance> findOutFinanceByFinanceId(Long financeId);
    /**查找入账*/
    @Query("select DISTINCT indent from Indent indent left join indent.finance as p  where p.financeId = ?1")
    public List<Finance> findInFinanceByFinanceId(Long financeId);
    /**批量修改状态 */
    @Transactional
    @Modifying
    @Query(value = "UPDATE Finance finance SET finance.finState=?1 WHERE finance.financeId=?2")
    void updateState(String finState,Long financeId);
}
