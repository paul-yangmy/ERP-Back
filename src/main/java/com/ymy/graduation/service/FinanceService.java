package com.ymy.graduation.service;

import com.ymy.graduation.domain.Finance;

import java.util.Date;
import java.util.List;

/**
 * @author ymyum
 * @date 2020/2/5 17:15
 * @project
 */
public interface FinanceService {
    /**创建*/
    Finance CreateFinance(Finance finance);
    /**查找*/
    Finance findByFinanceId(Long financeId);
    List<Finance> findAllFinance();
    List<Finance> findInFinance(Long financeId);
    List<Finance> findOutFinance(Long financeId);
    List<Finance> findByDate(Date submitTime);
    /**更改*/
    Boolean UpdateFinanceState(String finState, Long financeId);
}
