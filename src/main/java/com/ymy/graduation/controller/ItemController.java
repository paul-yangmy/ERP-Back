package com.ymy.graduation.controller;

import com.ymy.graduation.domain.Commodity;
import com.ymy.graduation.domain.TestReport;
import com.ymy.graduation.service.impl.CommodityServiceImpl;
import com.ymy.graduation.service.impl.TestServiceImpl;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by ymyum on 2020/1/16
 * @author ymyum
 */
@RestController
@RequestMapping(value = "/table",produces="application/json;charset=utf-8")
public class ItemController {
    /**
     * 检测报告操作
     */
    @Autowired
    /**@Autowired 注释，它可以对类成员变量、方法及构造函数进行标注，完成自动装配的工作。 通过 @Autowired的使用来消除 set ，get方法。*/
    private TestServiceImpl testService;
    @PostMapping("/TestReport/CreateTestReport")
    public String CreateTestReport(@RequestBody TestReport testReport){return testService.CreateTestReport(testReport);}
    @GetMapping("/TestReport/findAllTestReport")
    public List<TestReport> findAllTestReport(){return testService.findAllTestReport();}

    /**
     * 商品操作
     */
    @Autowired
    private CommodityServiceImpl commodityService;
    @PostMapping("/Commodity/CreateCommodity")
    public String CreateCommodity(@RequestBody Commodity commodity){return commodityService.CreateCommodity(commodity);}
    @GetMapping("/Commodity/DeleteCommodity")
    public String DeleteCommodity(HttpServletRequest request) {
        Integer itemId = Integer.parseInt(request.getParameter("itemId"));
        System.out.println(itemId);
        return commodityService.DeleteCommodity(itemId);
    }
    @GetMapping("/Commodity/findByItemId")
    public Commodity findByItemId(HttpServletRequest request){
        System.out.println("1");
        System.out.println(request);
        Integer itemId = Integer.parseInt(request.getParameter("itemId"));
        System.out.println(itemId);
        return commodityService.findByItemId(itemId);
    }
    @GetMapping("/Commodity/findAllCommodity")
    public List<Commodity> findAllCommodity(){return commodityService.findAllCommodity();}
    @PostMapping("/Commodity/UpdateCommodity")
    public Boolean UpdateCommodity(@RequestBody String resBody){
        JSONObject obj = new JSONObject(resBody);
        System.out.println(resBody);
        String graph=obj.get("graph").toString();
        String classes=obj.get("classes").toString();
        String name=obj.get("name").toString();
        String unit=obj.get("unit").toString();
        Double price=Double.parseDouble(obj.get("price").toString());
        String purchases=obj.get("purchases").toString();
        String buyer=obj.get("buyer").toString();
        String state=obj.get("state").toString();
        Integer itemId=Integer.parseInt(obj.get("itemId").toString());
        Boolean success= commodityService.UpdateCommodity( graph,classes, name, unit,  price, purchases, buyer, state, itemId);
        System.out.println(success);
        Commodity commodity=new Commodity();
        commodity.setBuyer(buyer);
        commodity.setClasses(classes);
        commodity.setGraph(graph);
        commodity.setItemId(itemId);
        commodity.setName(name);
        commodity.setPrice(price);
        commodity.setPurchases(purchases);
        commodity.setState(state);
        commodity.setUnit(unit);

        System.out.println(success);
        return success;
    }
    @PostMapping("/Commodity/UpdateCommodityStatus")
    public Boolean ShelvesInBulk(@RequestBody List<Commodity> resBody ) {
        if (!CollectionUtils.isEmpty(resBody)) {
            Boolean returnInfo=true;
            for (Commodity commodityItem : resBody) {
                System.out.println(commodityItem.getItemId());
                Boolean success= commodityService.UpdateState( commodityItem.getState(), commodityItem.getItemId());
                if(success.equals("true")) {
                    Commodity commodity=new Commodity();
                    commodity.setState(commodityItem.getState());
                }
                returnInfo=returnInfo&&success;
            }
            return returnInfo;
        }
        return null ;
    }

}
