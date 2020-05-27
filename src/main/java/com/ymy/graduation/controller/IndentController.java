package com.ymy.graduation.controller;

import com.ymy.graduation.domain.Commodity;
import com.ymy.graduation.domain.Indent;
import com.ymy.graduation.domain.OrderDetail;
import com.ymy.graduation.service.impl.CommodityServiceImpl;
import com.ymy.graduation.service.impl.IndentServiceImpl;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author ymyum
 * @date 2020/2/13 18:01
 * @project
 */
@RestController
@RequestMapping(value = "/indent",produces="application/json;charset=utf-8")
public class IndentController {
    /**
     * 订单操作
     */
    @Autowired
    private IndentServiceImpl indentService;
    @Autowired
    private CommodityServiceImpl commodityService;

    @PostMapping("/CreateIndent")
    public Boolean CreateIndent(@RequestBody String resBody) throws ParseException {
        JSONObject obj = new JSONObject(resBody);
        System.out.println(resBody);
        Indent indent=new Indent();

        String customerName=obj.get("customerName").toString();
        String indSource=obj.get("indSource").toString();
        String indState=obj.get("indState").toString();
//        System.out.println(commodity);
        Integer indId=Integer.parseInt(obj.get("indId").toString());
        String[] indNum=(obj.get("indNum").toString()).replaceAll("[\\[|\\]|\"]", "").split(",");
        Integer customerNum=Integer.parseInt(obj.get("customerNum").toString());
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        Date submitTime = sdf2.parse(obj.get("submitTime").toString());
//        System.out.println(submitTime);


        indent.setIndId(indId);
        indent.setCustomerName(customerName);
        indent.setCustomerNum(customerNum);
        indent.setIndSource(indSource);
        indent.setSubmitTime(submitTime);
        indent.setIndState("待发货");

        Indent flag=indentService.CreateIndent(indent);

        String[] commodity=(obj.get("commodity").toString()).replaceAll("[\\[|\\]|\"]", "").split(",");
        Boolean tf=false;
        if (!CollectionUtils.isEmpty(Collections.singleton(commodity))) {
            Integer i =0;
            for (String itemName : commodity) {
                String randomNum = String.valueOf(new Random().nextInt(10) + 1);
                OrderDetail od=new OrderDetail();
                Commodity detail = indentService.findByName(itemName);
                SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
                od.setOrderId(Long.parseLong(df.format(System.currentTimeMillis()).toString()+randomNum));
                od.setCommodity(detail);
                od.setOrderCost(detail.getPrice());
                od.setIndent(flag);
                od.setOrderNum(Integer.parseInt(indNum[i]));
                i++;
                if(indentService.CreateIndentDetail(od).equals(true)){
                    tf=true;
                }
                else {
                    tf=false;
                }
            }
        }

        return tf;
    }

    @GetMapping("/findByIndentId")
    public Indent findByIndentId(HttpServletRequest request) {
        System.out.println(request);
        Integer indId = Integer.parseInt(request.getParameter("indId"));
        System.out.println(indId);
        return indentService.findByIndentId(indId);
    }
    @GetMapping("/findAllIndent")
    public List<Indent> findAllIndent() {
        List<Indent> indentList=indentService.findAllIndent();
        for(Indent item : indentList) {
            System.out.println(item.getIndId());
            Indent indent =indentService.findByIndentId(item.getIndId());
            List<OrderDetail> detailList = indentService.findByIndentDetailId(indent);

            Double total=0.0;
            for(OrderDetail detail : detailList){
                System.out.println(detail.getOrderId());
//                System.out.println(detail.getOrderNum());
//                System.out.println(detail.getOrderCost());
                Double cost=detail.getOrderCost();
                Integer num=detail.getOrderNum();
//                System.out.println(cost*num);
                detail.setOrderTotalCost(cost*num);
                total+=cost*num;
//                System.out.println(total);
            }
            item.setTotalAmount(total);
            System.out.println(item.getTotalAmount());
        }
        return  indentList;
    }
    @GetMapping("/findByDetail")
    public List<OrderDetail> findByDetail(HttpServletRequest request) {
        Integer indId = Integer.parseInt(request.getParameter("indId"));
        Indent indent =indentService.findByIndentId(indId);
//        System.out.println(indent.getOrderDetailList());
        return indentService.findByIndentDetailId(indent);

    }
    @PostMapping("/UpdateIndent")
    public Boolean UpdateIndent(@RequestBody String resBody) throws ParseException {
        JSONObject obj = new JSONObject(resBody);
//        System.out.println(resBody);

        String indState=obj.get("indState").toString();
        Integer indId=Integer.parseInt(obj.get("indId").toString());
        Boolean success= indentService.UpdateIndent(indState,indId);
//        System.out.println(success);
        Indent indent=new Indent();
        indent.setIndId(indId);
        indent.setIndState(indState);


//        System.out.println(success);
        return success;
    }
}
