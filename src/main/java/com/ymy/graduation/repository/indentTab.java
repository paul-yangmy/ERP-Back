package com.ymy.graduation.repository;

import com.ymy.graduation.domain.Finance;
import com.ymy.graduation.domain.Indent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author ymyum
 * @date 2020/2/13 17:34
 * @project
 */
public interface indentTab extends JpaRepository<Indent,Integer> {
    /**查找*/
    @Query(value = "SELECT indent From Indent indent WHERE indent.indId=?1")
    Indent findByIndentId(Integer indId);

    @Query(value = "SELECT indent From Indent indent WHERE indent.finance=?1")
    List<Indent> findByFinance(Finance finance);

    /**修改*/
    @Transactional
    @Modifying
    @Query(value = "UPDATE Indent indent SET indent.indState=?1 WHERE indent.indId=?2")
    void UpdateIndent(String indState, Integer indId);
    @Transactional
    @Modifying
    @Query(value = "UPDATE Indent indent SET indent.finance=?1 WHERE indent.indId=?2")
    void UpdateFinance(Finance finance, Integer indId);


}
