package com.ymy.graduation.controller;

import com.ymy.graduation.domain.Driver;
import com.ymy.graduation.domain.OutStorage;
import com.ymy.graduation.domain.Transportation;
import com.ymy.graduation.service.impl.DriverServiceImpl;
import com.ymy.graduation.service.impl.OutStorageServiceImpl;
import com.ymy.graduation.service.impl.TransportationServiceImpl;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ymyum
 * @date 2020/2/5 16:43
 * @project
 */
@RestController
@RequestMapping(value = "/delivery",produces="application/json;charset=utf-8")
public class DeliveryController {
    /**
     * 配送操作
     */
    @Autowired
    private TransportationServiceImpl transportationService;
    @Autowired
    private OutStorageServiceImpl outStorageService;


    @GetMapping("/Transportation/DeleteTransportation")
    public String DeleteTransportation(HttpServletRequest request) {
        Integer transId = Integer.parseInt(request.getParameter("transId"));
        System.out.println(transId);
        return transportationService.DeleteTransportation(transId);
    }
    @GetMapping("/Transportation/findByTransId")
    public Transportation findByTransId(HttpServletRequest request){
        System.out.println(request);
        Integer transId = Integer.parseInt(request.getParameter("transId"));
        System.out.println(transId);
        return transportationService.findByTransId(transId);
    }
    @GetMapping("/Transportation/findByTransDate")
    public List<Transportation> findByTransDate(HttpServletRequest request) throws ParseException {
//        System.out.println(request);
        String date = request.getParameter("date");
        com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(date);
//        System.out.println(jsonObject);
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        Date transDate = sdf2.parse(jsonObject.getString("pickDate"));
        System.out.println(transDate);
        List<Transportation> transportationList=transportationService.findAllTransportation();
        List<Transportation> transportationListByDate=new ArrayList<Transportation>();
        for(Transportation trans : transportationList) {
            if (sdf2.parse(sdf2.format(trans.getTransDate())).compareTo(transDate)==0){
                transportationListByDate.add(trans);
            }

        }
        return transportationListByDate;
    }
    @GetMapping("/Transportation/findByLine")
    public List<Transportation> findByLine(HttpServletRequest request){
        System.out.println(request);
        String transName = request.getParameter("transName");
        System.out.println(transName);
        return transportationService.findByLine(transName);
    }
    @GetMapping("/Transportation/findAllTransportation")
    public List<Transportation> findAllTransportation(){
        List<Transportation> transportationList=transportationService.findAllTransportation();
        List<OutStorage> outStorageList=outStorageService.findAllOutStorage();

        for (OutStorage outStorage:outStorageList){
            Boolean flag=false;
            Transportation trans=new Transportation();
            for (Transportation transportation:transportationList) {
                if (transportation.getOutStorage()==outStorage){
                    flag=true;
                }
            }
            if (!flag){
                if(outStorage.getOutState().equals("预打包完成")){
                    trans.setOutStorage(outStorage);
                    trans.setTransDate(new Date());
                    trans.setTransName("线路一");
                    SimpleDateFormat sdf2 = new SimpleDateFormat("yyMMdd");
                    String date=sdf2.format(new Date());
                    trans.setTransId(Integer.parseInt(date));
                    Transportation newTrans=transportationService.CreateTransportation(trans);
                    List<Driver> driverList=driverService.findAllDriver();
                    for (Driver driver:driverList){
                        if (driver.getdState().equals("待班")){
                            if (driverService.UpdateState("线路一",driver.getdId())){
                                if (transportationService.UpdateDriver(driver,newTrans.getTransId()))
                                    newTrans.setDriver(driver);
                                break;
                            }
                        }
                    }
                    if(newTrans!=null){
                        transportationList.add(newTrans);
                    }
                }
            }
        }
        return transportationList;
    }
    @PostMapping("/Transportation/UpdateTransportation")
    public Boolean UpdateTransportation(@RequestBody String resBody) throws ParseException {
        JSONObject obj = new JSONObject(resBody);
        System.out.println(resBody);
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        Date transDate = sdf2.parse(obj.get("transDate").toString());
        String transName=obj.get("transName").toString();
        Integer transId=Integer.parseInt(obj.get("transId").toString());

        Boolean success= transportationService.UpdateTransportation( transDate, transName, transId);
        System.out.println(success);
        Transportation transportation=new Transportation();
        transportation.setTransDate(transDate);
        transportation.setTransName(transName);
        transportation.setTransId(transId);

        System.out.println(success);
                return success;
                }

/**
 * 司机操作
 */
@Autowired
private DriverServiceImpl driverService;
@PostMapping("/Driver/CreateDriver")
public String CreateDriver(@RequestBody Driver driver){return driverService.CreateDriver(driver);}
@GetMapping("/Driver/DeleteDriver")
public String DeleteDriver(HttpServletRequest request) {
        Integer dId = Integer.parseInt(request.getParameter("dId"));
        System.out.println(dId);
        return driverService.DeleteDriver(dId);
        }
@GetMapping("/Driver/findByDriverId")
public Driver findByDriverId(HttpServletRequest request){
        System.out.println(request);
        Integer dId = Integer.parseInt(request.getParameter("dId"));
        System.out.println(dId);
        return driverService.findByDriverId(dId);
        }
@GetMapping("/Driver/findAllDriver")
public List<Driver> findAllDriver(){return driverService.findAllDriver();}
@PostMapping("/Driver/UpdateDriver")
public Boolean UpdateDriver(@RequestBody String resBody){
        JSONObject obj = new JSONObject(resBody);
        System.out.println(resBody);

        String dName=obj.get("dName").toString();
        String dPhone=obj.get("dPhone").toString();
        String dState= obj.get("dState").toString();
        Integer dId=Integer.parseInt(obj.get("dId").toString());

        Boolean success= driverService.UpdateDriver( dName, dPhone, dState, dId);
        System.out.println(success);
        Driver driver=new Driver();
        driver.setdId(dId);
        driver.setdName(dName);
        driver.setdPhone(dPhone);
        driver.setdState(dState);

        System.out.println(success);
        return success;
        }
@PostMapping("/Driver/UpdateDriverStatus")
public Boolean ShelvesInBulk(@RequestBody List<Driver> resBody ) {
        if (!CollectionUtils.isEmpty(resBody)) {
        Boolean returnInfo=true;
        for (Driver driverItem : resBody) {
        System.out.println(driverItem.getdId());
        Boolean success= driverService.UpdateState( driverItem.getdState(), driverItem.getdId());
        if(success.equals("true")) {
        Driver driver=new Driver();
        driver.setdState(driverItem.getdState());
        }
        returnInfo=returnInfo&&success;
        }
        return returnInfo;
        }
        return null ;
        }
}
