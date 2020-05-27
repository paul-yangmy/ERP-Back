package com.ymy.graduation.service.impl;


import com.ymy.graduation.domain.Finance;
import com.ymy.graduation.repository.financeTab;
import com.ymy.graduation.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author ymyum
 * @date 2020/2/5 17:15
 * @project
 */
@Service
public class FinanceServiceImpl implements FinanceService {
    @Autowired
    private financeTab financeTab;

    @Override
    public Finance CreateFinance(Finance finance) {

        try {
            return financeTab.save(finance);

        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public Finance findByFinanceId(Long financeId) {
        return financeTab.findByFinanceId(financeId);
    }

    @Override
    public List<Finance> findAllFinance() {
        return financeTab.findAll();
    }

    @Override
    public Boolean UpdateFinanceState(String finState, Long financeId){
        boolean flag=false;
        try {
            financeTab.updateState( finState, financeId);
            flag=true;
        }
        catch (Exception e){
            System.out.println(e);
        }
        if(flag){return true;}
        else {return false;}
    }

    @Override
    public List<Finance> findInFinance(Long financeId) {
        return financeTab.findInFinanceByFinanceId(financeId);
    }

    @Override
    public List<Finance> findOutFinance(Long financeId) {
        return financeTab.findOutFinanceByFinanceId(financeId);
    }

    @Override
    public List<Finance> findByDate(Date submitTime) {
        return financeTab.findByDate(submitTime);
    }
}

