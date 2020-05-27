package com.ymy.graduation.controller;

import com.alibaba.fastjson.JSONObject;
import com.ymy.graduation.domain.*;
import com.ymy.graduation.service.impl.FinanceServiceImpl;
import com.ymy.graduation.service.impl.IndentServiceImpl;
import com.ymy.graduation.service.impl.PurchaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by ymyum on 2020/3/11
 */

@RestController
@RequestMapping(value = "/finance",produces="application/json;charset=utf-8")
public class FinanceController {
    @Autowired
    private FinanceServiceImpl financeService;
    @Autowired
    private IndentServiceImpl indentService;
    @Autowired
    private PurchaseServiceImpl purchaseService;

    @GetMapping("/findByFinanceId")
    public Finance findByFinanceId(HttpServletRequest request) {
        System.out.println(request);
        Long financeId = Long.parseLong(request.getParameter("financeId"));
        System.out.println(financeId);
        return financeService.findByFinanceId(financeId);
    }
    @GetMapping("/findAllFinance")
    public List<Finance> findAllFinance() {
        List<Finance> financeList=financeService.findAllFinance();
        List<Indent> indents=indentService.findAllIndent();
        List<Purchase> purchases=purchaseService.findAllPurchase();
        List<Date> dateTable=new ArrayList<>();

        for (Indent indent:indents){
            dateTable.add(indent.getSubmitTime());
        }
        for (Purchase purchase:purchases){
            dateTable.add(purchase.getBuyDate());
        }
        HashSet h = new HashSet(dateTable);
        dateTable.clear();
        dateTable.addAll(h);
        for(Date date:dateTable){
            List<Finance> finances=financeService.findByDate(date);
            Boolean flag=false;
            for (Finance f:finances){
                Calendar c = Calendar.getInstance();
                c.setTime(date);
                c.add(Calendar.DATE, 1);  // add 1 day
                Date tomorrow = c.getTime();
                c.setTime(date);
                c.add(Calendar.DATE, -1);  // - 1 day
                Date yesterday = c.getTime();
                if (f.getSubmitTime().after(yesterday)&&f.getSubmitTime().before(tomorrow)){
                    flag=true;
                }
            }
            if (!flag){
                Finance fin=new Finance();
                fin.setFinState("未审核");
                fin.setFinFee(0.0);
                fin.setSubmitTime(date);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                fin.setFinanceId(Long.parseLong(sdf.format(date)));
                fin.setFinName(sdf.format(date)+String.valueOf(new Random().nextInt(10) + 1));
                Finance newFinance= financeService.CreateFinance(fin);
                if (newFinance!=null){
                    financeList.add(newFinance);
                    Calendar c = Calendar.getInstance();
                    c.setTime(date);
                    c.add(Calendar.DATE, 1);  // add 1 day
                    Date tomorrow = c.getTime();
                    c.setTime(date);
                    c.add(Calendar.DATE, -1);  // - 1 day
                    Date yesterday = c.getTime();
                    for (Indent indent:indents){
                        if (indent.getSubmitTime().after(yesterday)&&indent.getSubmitTime().before(tomorrow)){
                            if(indentService.UpdateFinance(newFinance,indent.getIndId()))
                                indent.setFinance(newFinance);
                        };
                    }
                    for (Purchase purchase:purchases){
                        if (purchase.getBuyDate().after(yesterday)&&purchase.getBuyDate().before(tomorrow)){
                            if(purchaseService.UpdateFinance(newFinance,purchase.getBuyId()))
                                purchase.setFinance(newFinance);
                        };
                    }
                }
            }
        }

        for ( Finance item:financeList) {
            Double totalFee=0.0;
            List<Indent> indentList=indentService.findByFinance(item);
            List<Purchase> purchaseList=purchaseService.findByFinance(item);
            for (Indent indent:indentList) {
                if (indent.getFinance().getFinanceId().equals(item.getFinanceId())) {
                    List<Long> used = new ArrayList<Long>();
                    for (Purchase purchase : purchaseList) {
                        if (!used.contains(purchase.getBuyId())) {
                            if ((indent.getFinance().getFinanceId()).equals(purchase.getFinance().getFinanceId())) {
                                System.out.println(indent.getIndId());
                                System.out.println(purchase.getBuyId());
                                used.add(purchase.getBuyId());
                                Double addFee = 0.0;
                                List<OrderDetail> detailList = indentService.findByIndentDetailId(indent);
                                for (OrderDetail detail : detailList) {
                                    addFee = addFee + detail.getOrderNum() * detail.getOrderCost();
                                }
                                Double minusFee = 0.0;
                                List<PurchaseDetail> buyList = purchaseService.findByPurchaseDetail(purchase);
                                for (PurchaseDetail detail : buyList) {
                                    minusFee = minusFee + detail.getBuyPrice() * detail.getBuyNum();
                                }
                                totalFee = totalFee + addFee - minusFee;
                            }
                        } else {
                            if ((indent.getFinance().getFinanceId()).equals(purchase.getFinance().getFinanceId())) {
                                Double addFee = 0.0;
                                List<OrderDetail> detailList = indentService.findByIndentDetailId(indent);
                                for (OrderDetail detail : detailList) {
                                    addFee = addFee + detail.getOrderNum() * detail.getOrderCost();
                                }
                                totalFee = totalFee + addFee;
                            }
                        }
                    }
                }
            }
            item.setFinFee(totalFee);
        }
        return financeList;
    }

    @GetMapping("/findByDate")
    public List<Finance> findByDate(HttpServletRequest request) throws ParseException {
        System.out.println(request);
        String date = request.getParameter("date");
        JSONObject jsonObject = JSONObject.parseObject(date);
        JSONObject dateJson=JSONObject.parseObject(jsonObject.getString("values"));
        System.out.println(dateJson);
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = sdf2.parse(dateJson.getString("beginDate"));
        Date endDate = sdf2.parse(dateJson.getString("endDate"));
        System.out.println(beginDate);
        List<Finance> financeList=financeService.findAllFinance();
        List<Finance> financeListByDate=new ArrayList<Finance>();
        for(Finance finance : financeList) {
            System.out.println(finance.getSubmitTime());
            if(finance.getSubmitTime().after(beginDate)&&finance.getSubmitTime().before(endDate)) {
                financeListByDate.add(finance);
            }
        }
        return financeListByDate;
    }
}
