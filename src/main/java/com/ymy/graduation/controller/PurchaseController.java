package com.ymy.graduation.controller;

import com.ymy.graduation.domain.*;
import com.ymy.graduation.service.impl.CommodityServiceImpl;
import com.ymy.graduation.service.impl.IndentServiceImpl;
import com.ymy.graduation.service.impl.PurchaseServiceImpl;
import com.ymy.graduation.service.impl.RepositoryServiceImpl;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author ymyum
 * @date 2020/2/15 18:01
 * @project
 */
@RestController
@RequestMapping(value = "/buy",produces="application/json;charset=utf-8")
public class PurchaseController {
    /**
     * 采购操作
     */
    @Autowired
    private PurchaseServiceImpl purchaseService;
    @Autowired
    private CommodityServiceImpl commodityService;

    @PostMapping("/CreatePurchase")
    public Boolean CreatePurchase(@RequestBody String resBody) throws ParseException {
        JSONObject obj = new JSONObject(resBody);
        System.out.println(resBody);
        Purchase purchase=new Purchase();
        PurchaseDetail pd=new PurchaseDetail();

        String buyId=obj.get("buyId").toString();
        String randomNum = String.valueOf(new Random().nextInt(10) + 1);
        purchase.setBuyId(Long.parseLong(buyId));
        Long orderBuyId=Long.parseLong(buyId+randomNum);
//        System.out.println(orderBuyId);
        pd.setOrderBuyId(orderBuyId);

        String buyType=obj.get("buyType").toString();
        String buyer=obj.get("buyer").toString();
        Double buyFee=Double.parseDouble(obj.get("buyFee").toString());
        Integer buyNum=Integer.parseInt(obj.get("buyNum").toString());
        String buyState=obj.get("buyState").toString();
        String buyComment=obj.get("buyComment").toString();
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        Date buyDate = sdf2.parse(sdf2.format(System.currentTimeMillis()).toString());
        System.out.println((buyFee*buyNum));
        Double buyTotal=buyFee*buyNum;
        purchase.setBuyFee(buyTotal);
        purchase.setBuyState(buyState);
        purchase.setBuyer(buyer);
        purchase.setBuyLister("admin");
        purchase.setBuyType(buyType);
        purchase.setBuyDate(buyDate);
        pd.setBuyNum(buyNum);
        pd.setBuyPrice(buyFee);
        pd.setBuyTotal(buyTotal);
        pd.setBuyComment(buyComment);
        pd.setPurchase(null);
        System.out.println("1");
        String itemName=obj.get("name").toString();
        System.out.println(itemName);
        Commodity feedback=commodityService.findByName(itemName);
        System.out.println("2");
        pd.setCommodity(feedback);
        System.out.println("3");
//        pd.setPurchase(purchase);
        Purchase flag=purchaseService.CreatePurchase(purchase);
//        return flag;
        if(!flag.equals(null)){
            System.out.println("4");
            pd.setPurchase(flag);
            if(purchaseService.CreatePurchaseDetail(pd).equals(true)){
                return true;
            }
        }
        return false;
    }
    @GetMapping("/DeletePurchase")
    public Boolean DeletePurchase(HttpServletRequest request) {
        Long buyId =Long.parseLong(request.getParameter("buyId"));
        List<PurchaseDetail> pd=purchaseService.findByPurchaseDetail(purchaseService.findByBuyId(buyId));
//        System.out.println(buyId);
        Boolean flag=false;
        for (PurchaseDetail item:pd) {
            if(purchaseService.DeletePurchaseDetail(item.getOrderBuyId())){
                flag=true;
            }
            else {flag=false;}
        }
        if (flag){
            return purchaseService.DeletePurchase(buyId);
        }
        return flag;
    }
    @GetMapping("/findByBuyId")
    public Purchase findByBuyId(HttpServletRequest request){
//        System.out.println(request);
        Long buyId = Long.parseLong(request.getParameter("buyId"));
        return purchaseService.findByBuyId(buyId);
    }
    @GetMapping("/findAllPurchase")
    public List<Purchase> findAllPurchase(){
        List<Purchase> purchaseList=purchaseService.findAllPurchase();
        for(Purchase item : purchaseList) {
//            System.out.println(item.getBuyId());
            Purchase purchase =purchaseService.findByBuyId(item.getBuyId());
            List<PurchaseDetail> detailList = purchaseService.findByPurchaseDetail(purchase);

            Double total=0.0;
            for(PurchaseDetail detail : detailList){
//                System.out.println(detail.getOrderBuyId());
//                System.out.println(detail.getBuyNum());
//                System.out.println(detail.getBuyPrice());
                Double cost=detail.getBuyPrice();
                Integer num=detail.getBuyNum();
//                System.out.println(cost*num);
                detail.setBuyTotal(cost*num);
                total+=cost*num;
//                System.out.println(total);
            }
            item.setBuyFee(total);
//            System.out.println(item.getBuyFee());
        }
        return  purchaseList;
    }

    @GetMapping("/findByDetail")
    public List<PurchaseDetail> findByDetail(HttpServletRequest request) {
        Long buyId = Long.parseLong(request.getParameter("buyId"));
        Purchase purchase =purchaseService.findByBuyId(buyId);
//        System.out.println(indent.getOrderDetailList());
        return purchaseService.findByPurchaseDetail(purchase);
    }
    @PostMapping("/UpdatePurchase")
    public Boolean UpdatePurchase(@RequestBody String resBody) throws ParseException {
        JSONObject obj = new JSONObject(resBody);
//        System.out.println(resBody);
        String buyType=obj.get("buyType").toString();
        String buyer=obj.get("buyer").toString();
        String buyLister=obj.get("buyLister").toString();
        Double buyFee=Double.parseDouble(obj.get("buyFee").toString());
        String buyState=obj.get("buyState").toString();
        Long buyId=Long.parseLong(obj.get("buyId").toString());
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        Date buyDate = sdf2.parse(obj.get("buyDate").toString());
        Boolean success= purchaseService.UpdatePurchase(buyType, buyer, buyFee, buyLister, buyState, buyDate,buyId);
//        System.out.println(success);
        Purchase purchase=new Purchase();
        purchase.setBuyer(buyer);
        purchase.setBuyType(buyType);
        purchase.setBuyFee(buyFee);
        purchase.setBuyId(buyId);
        purchase.setBuyState(buyState);
        purchase.setBuyLister(buyLister);

//        System.out.println(success);
        return success;
    }
    @PostMapping("/UpdatePurchaseStatus")
    public Boolean ShelvesInBulk(@RequestBody List<Purchase> resBody ) {
        if (!CollectionUtils.isEmpty(resBody)) {
            Boolean returnInfo=true;
            for (Purchase buyItem : resBody) {
//                System.out.println(buyItem.getBuyId());
                Boolean success= purchaseService.UpdateState( buyItem.getBuyState(), buyItem.getBuyId());
                if(success.equals("true")) {
                    Purchase purchase=new Purchase();
                    purchase.setBuyState(buyItem.getBuyState());
                }
                returnInfo=returnInfo&&success;
            }
            return returnInfo;
        }
        return null ;
    }

    @Autowired
    private RepositoryServiceImpl repositoryService;
    @Autowired
    private IndentServiceImpl indentService;

    @GetMapping("/findAllOnDemandy")
    public List<PurchaseDetail> findAllOnDemandy(){
        List<Repository> repositoryList=repositoryService.findAllRepository();
        List<Indent> indentList=indentService.findAllIndent();
        List<OrderDetail> detailList=new ArrayList<OrderDetail>();
        List<PurchaseDetail> buyDetailList=new ArrayList<PurchaseDetail>();
        for(Indent item : indentList) {
//            System.out.println(item.getIndId());
            Indent indent =indentService.findByIndentId(item.getIndId());
            List<OrderDetail> detailListZero = indentService.findByIndentDetailId(indent);
            for(OrderDetail od:detailListZero){
                detailList.add(od);
            }
        }
        List<Integer> flag=new ArrayList<Integer>();
        for(OrderDetail detail:detailList){
            Integer itemId=detail.getCommodity().getItemId();
            Integer itemNum=detail.getOrderNum();
            System.out.println(itemId);
            for(Repository repo:repositoryList){
                System.out.println(repo.getRepoItem());
                Integer repoItemId=(commodityService.findByName(repo.getRepoItem())).getItemId();
                Integer repoItemNum=repo.getRepoNum();
                System.out.println(repoItemId);
                if (repoItemId.equals(itemId)){
                    PurchaseDetail pd=new PurchaseDetail();
                    if (itemNum>repoItemNum){
                        System.out.println("a");
                        pd.setCommodity(detail.getCommodity());
                        System.out.println("c");
                        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
                        String randomNum = String.valueOf(new Random().nextInt(10) + 1);
                        pd.setOrderBuyId(Long.parseLong(df.format(System.currentTimeMillis()).toString()+randomNum));
                        System.out.println("d");
                        pd.setBuyNum(itemNum-repoItemNum);
                        pd.setBuyComment("临时采购");
                        buyDetailList.add(pd);
                    }
                    System.out.println("b");
                    Iterator<Integer> iterator = flag.iterator();
                    while (iterator.hasNext()) {
                        Integer next = iterator.next();
                        System.out.println(next);
                        if (itemId.equals(next)) {
                            iterator.remove();
                        }
                    }
                    break;
                }
                else{
                    flag.add(itemId);
                    System.out.println(flag);
                }
            }
        }
        HashSet h = new HashSet(flag);
        flag.clear();
        flag.addAll(h);
        for (Integer itemId:flag) {
            PurchaseDetail pd=new PurchaseDetail();
            pd.setCommodity(commodityService.findByItemId(itemId));
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            String randomNum = String.valueOf(new Random().nextInt(10) + 1);
            pd.setOrderBuyId(Long.parseLong(df.format(System.currentTimeMillis()).toString()+randomNum));
            pd.setBuyNum(indentService.findByCommodity(commodityService.findByItemId(itemId)).getOrderNum());
            pd.setBuyComment("临时采购");
            buyDetailList.add(pd);
        }
        return buyDetailList;
    }
}
