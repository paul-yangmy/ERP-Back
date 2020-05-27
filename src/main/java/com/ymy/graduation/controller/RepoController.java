package com.ymy.graduation.controller;

import com.alibaba.fastjson.JSONObject;
import com.ymy.graduation.domain.*;
import com.ymy.graduation.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author ymyum
 * @date 2020/2/4 16:48
 * @project
 */
@RestController
@RequestMapping(value = "/repo",produces="application/json;charset=utf-8")
public class RepoController {
    /**
     * 入库操作
     */
    @Autowired
    private InStorageServiceImpl inStorageService;
    @Autowired
    private PurchaseServiceImpl purchaseService;
    @Autowired
    private CommodityServiceImpl commodityService;
    @Autowired
    private RepositoryServiceImpl repositoryService;

    @PostMapping("/InStorage/CreateInStorage")
    public String CreateInStorage(@RequestBody InStorage inStorage) {
        return inStorageService.CreateInStorage(inStorage);
    }
    @GetMapping("/InStorage/DeleteInStorage")
    public String DeleteInStorage(HttpServletRequest request) {
        Long inId = Long.parseLong(request.getParameter("inId"));
        System.out.println(inId);
        return inStorageService.DeleteInStorage(inId);
    }
    @GetMapping("/InStorage/findByInId")
    public InStorage findByInId(HttpServletRequest request) {
        System.out.println(request);
        Long inId = Long.parseLong(request.getParameter("inId"));
        System.out.println(inId);
        return inStorageService.findByInId(inId);
    }
    @GetMapping("/InStorage/findAllInStorage")
    public List<InStorage> findAllInStorage() throws ParseException {
        List<Purchase> purchasesList=purchaseService.findAllPurchase();
        List<InStorage> inStorageList=inStorageService.findAllInStorage();

        for (Purchase p:purchasesList){
            Boolean flag=false;
            InStorage in=new InStorage();
            for (InStorage inStorage:inStorageList) {
                if (inStorage.getPurchase()==p){
                    flag=true;
                }
            }
            if (!flag){
                if(p.getBuyState().equals("验收合格")){
                    in.setPurchase(p);
                    in.setInDate(p.getBuyDate());
                    in.setInFee(p.getBuyFee());
                    in.setStorageNam("生鲜");
                    in.setInId(p.getBuyId());
                    in.setLister("admin");
                    in.setInState("采购完成");
                    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
                    String date=sdf2.format(p.getBuyDate());
                    in.setInIdName("RK"+date+String.valueOf(new Random().nextInt(10) + 1));
                    if(inStorageService.CreateInStorage(in).equals("true")){
                        inStorageList.add(in);
                    }
                }
            }
        }

        return inStorageList;
    }
    @PostMapping("/InStorage/UpdateInStorage")
    public Boolean UpdateInStorage(@RequestBody String resBody) throws ParseException {
        org.json.JSONObject obj = new org.json.JSONObject(resBody);
//        System.out.println(resBody);
//        Double inFee = Double.parseDouble(obj.get("inFee").toString());
        String storageNam = obj.get("storageNam").toString();
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        Date inDate = sdf2.parse(obj.get("inDate").toString());
        String lister = obj.get("lister").toString();
        String inState = obj.get("inState").toString();
        Long inId = Long.parseLong(obj.get("inId").toString());
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMdd");
        String date=sdf3.format(inDate);
        String inIdName="RK"+date+String.valueOf(new Random().nextInt(10) + 1);
        Boolean success = inStorageService.UpdateInStorage( inDate, storageNam, lister, inState,inIdName, inId);
        System.out.println(success);
        InStorage inStorage = new InStorage();
        inStorage.setInState(inState);
        inStorage.setInDate(inDate);
//        inStorage.setInFee(inFee);
        inStorage.setInId(inId);
        inStorage.setLister(lister);
        inStorage.setStorageNam(storageNam);
        System.out.println(inState);
        if(inState.equals("分拣完成")){
            Long buyId=inStorageService.findByInId(inId).getPurchase().getBuyId();
            List<PurchaseDetail> detailList=purchaseService.findByPurchaseDetail(purchaseService.findByBuyId(buyId));
            for(PurchaseDetail detail:detailList ){
                System.out.println(detail.getOrderBuyId());
                Integer itemId=detail.getCommodity().getItemId();
                Integer num=detail.getBuyNum();
                String name=commodityService.findByItemId(itemId).getName();
                System.out.println(name);
                List<Repository> repositoryList=repositoryService.findAllRepository();
                for (Repository repo:repositoryList){
                    System.out.println(repo.getRepoItem());
                    if(name.equals(repo.getRepoItem())){
                        Integer numAdd =repo.getRepoNum()+num;
                        System.out.println(numAdd);
                        Boolean res=repositoryService.UpdateRepositoryNum(numAdd,repo.getRepoId());
                        System.out.println(res);
                        if (repositoryService.UpdateRepositoryNum(numAdd,repo.getRepoId())){
                            repo.setRepoNum(numAdd);
                        }

                    }
                }
            }
        }

        System.out.println(success);
        return success;
    }
    @PostMapping("/InStorage/UpdateInStorageStatus")
    public Boolean ShelvesInBulk(@RequestBody List<InStorage> resBody) {
        if (!CollectionUtils.isEmpty(resBody)) {
            Boolean returnInfo = true;
            for (InStorage inStorageItem : resBody) {
                System.out.println(inStorageItem.getInId());
                Boolean success = inStorageService.UpdateState(inStorageItem.getInState(), inStorageItem.getInId());
                if (success.equals("true")) {
                    InStorage inStorage = new InStorage();
                    inStorage.setInState(inStorageItem.getInState());
                    if(inStorageItem.getInState().equals("分拣完成")){
                        Long buyId=inStorageService.findByInId(inStorageItem.getInId()).getPurchase().getBuyId();
                        List<PurchaseDetail> detailList=purchaseService.findByPurchaseDetail(purchaseService.findByBuyId(buyId));
                        for(PurchaseDetail detail:detailList ){
                            System.out.println(detail.getOrderBuyId());
                            Integer itemId=detail.getCommodity().getItemId();
                            Integer num=detail.getBuyNum();
                            String name=commodityService.findByItemId(itemId).getName();
                            List<Repository> repositoryList=repositoryService.findAllRepository();
                            for (Repository repo:repositoryList){
                                if(name.equals(repo.getRepoItem())){
                                    Integer numAdd =repo.getRepoNum()+num;
                                    if (repositoryService.UpdateRepositoryNum(numAdd,repo.getRepoId())){
                                        repo.setRepoNum(numAdd);
                                    }
                                }
                            }
                        }
                    }
                }
                returnInfo = returnInfo && success;
            }
            return returnInfo;
        }
        return null;
 }

    /**
     * 出库操作
     */
    @Autowired
    private OutStorageServiceImpl outStorageService;
    @Autowired
    private IndentServiceImpl indentService;
    @PostMapping("/OutStorage/CreateOutStorage")
    public String CreateOutStorage(@RequestBody OutStorage outStorage) {
        return outStorageService.CreateOutStorage(outStorage);
    }
    @GetMapping("/OutStorage/DeleteOutStorage")
    public String DeleteOutStorage(HttpServletRequest request) {
        Long outId = Long.parseLong(request.getParameter("outId"));
        return outStorageService.DeleteOutStorage(outId);
    }
    @GetMapping("/OutStorage/findByOutId")
    public OutStorage findByOutId(HttpServletRequest request) {
        System.out.println(request);
        Long outId = Long.parseLong(request.getParameter("outId"));
        return outStorageService.findByOutId(outId);
    }
    @GetMapping("/OutStorage/findAllOutStorage")
    public List<OutStorage> findAllOutStorage() {
        List<OutStorage> outStorageList= outStorageService.findAllOutStorage();
        List<Indent> indentList=indentService.findAllIndent();

        for (Indent ind:indentList){
            Boolean flag=false;
            OutStorage out=new OutStorage();
            for (OutStorage outStorage:outStorageList) {
                if (outStorage.getIndent()==ind){
                    flag=true;
                }
            }
            if (!flag){
                if(ind.getIndState().equals("待发货")){
                    out.setIndent(ind);
                    out.setOutDate(new Date());
                    out.setOutState("分拣中");
                    out.setLister("admin");
                    out.setCustomer(ind.getCustomerName());
                    List<OrderDetail> orderDetailList=indentService.findByIndentDetailId(ind);
                    Double fee=0.0;
                    for (OrderDetail od:orderDetailList){
                        out.setStorageNam(od.getCommodity().getClasses());
                        fee+=od.getOrderNum()*od.getOrderCost();
                    }
                    out.setOutFee(0.02*fee+1);

                    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
                    String date=sdf2.format(new Date());
                    out.setOutName("CK"+date+String.valueOf(new Random().nextInt(10) + 1));
                    out.setOutId(Long.parseLong(date));
                    if(outStorageService.CreateOutStorage(out).equals("true")){
                        outStorageList.add(out);
                    }
                }
            }
        }

        return outStorageList;
    }
    @PostMapping("/OutStorage/UpdateOutStorage")
    public Boolean UpdateOutStorage(@RequestBody String resBody) throws ParseException {
        org.json.JSONObject obj = new org.json.JSONObject(resBody);
        System.out.println(resBody);
//        Double outFee = Double.parseDouble(obj.get("outFee").toString());
        String storageNam = obj.get("storageNam").toString();
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        Date outDate = sdf2.parse(obj.get("outDate").toString());
        String lister = obj.get("lister").toString();
        String outState = obj.get("outState").toString();
        String customer = obj.get("customer").toString();
        Long outId = Long.parseLong(obj.get("outId").toString());
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMdd");
        String date=sdf3.format(outDate);
        String outName="RK"+date+String.valueOf(new Random().nextInt(10) + 1);
        Boolean success = outStorageService.UpdateOutStorage(  outDate,  storageNam,  lister,  outState, customer,outName, outId);
        System.out.println(success);
        OutStorage outStorage = new OutStorage();
        outStorage.setOutState(outState);
        outStorage.setOutDate(outDate);
        outStorage.setOutId(outId);
        outStorage.setLister(lister);
        outStorage.setStorageNam(storageNam);
        outStorage.setCustomer(customer);
        if(outState.equals("预打包完成")){
            Integer indId=outStorageService.findByOutId(outId).getIndent().getIndId();
            List<OrderDetail> detailList=indentService.findByIndentDetailId(indentService.findByIndentId(indId));
            for(OrderDetail detail:detailList ){
                System.out.println(detail.getOrderId());
                Integer itemId=detail.getCommodity().getItemId();
                Integer num=detail.getOrderNum();
                String name=commodityService.findByItemId(itemId).getName();
                System.out.println(name);
                List<Repository> repositoryList=repositoryService.findAllRepository();
                for (Repository repo:repositoryList){
                    System.out.println(repo.getRepoItem());
                    if(name.equals(repo.getRepoItem())){
                        Integer numDelete =repo.getRepoNum()-num;
                        System.out.println(numDelete);
                        Boolean res=repositoryService.UpdateRepositoryNum(numDelete,repo.getRepoId());
                        System.out.println(res);
                        if (repositoryService.UpdateRepositoryNum(numDelete,repo.getRepoId())){
                            repo.setRepoNum(numDelete);
                        }

                    }
                }
            }
        }

        System.out.println(success);
        return success;
    }
    @PostMapping("/OutStorage/UpdateOutStorageStatus")
    public Boolean ShelvesOutBulk(@RequestBody List<OutStorage> resBody) {
        if (!CollectionUtils.isEmpty(resBody)) {
            Boolean returnInfo = true;
            for (OutStorage outStorageItem : resBody) {
                System.out.println(outStorageItem.getOutId());
                Boolean success = outStorageService.UpdateState(outStorageItem.getOutState(), outStorageItem.getOutId());
                if (success.equals("true")) {
                    OutStorage outStorage = new OutStorage();
                    outStorage.setOutState(outStorageItem.getOutState());
                    if(outStorage.getOutState().equals("预打包完成")){
                        Integer indId=outStorageService.findByOutId(outStorage.getOutId()).getIndent().getIndId();
                        List<OrderDetail> detailList=indentService.findByIndentDetailId(indentService.findByIndentId(indId));
                        for(OrderDetail detail:detailList ){
                            System.out.println(detail.getOrderId());
                            Integer itemId=detail.getCommodity().getItemId();
                            Integer num=detail.getOrderNum();
                            String name=commodityService.findByItemId(itemId).getName();
                            System.out.println(name);
                            List<Repository> repositoryList=repositoryService.findAllRepository();
                            for (Repository repo:repositoryList){
                                System.out.println(repo.getRepoItem());
                                if(name.equals(repo.getRepoItem())){
                                    Integer numAdd =repo.getRepoNum()+num;
                                    System.out.println(numAdd);
                                    Boolean res=repositoryService.UpdateRepositoryNum(numAdd,repo.getRepoId());
                                    System.out.println(res);
                                    if (repositoryService.UpdateRepositoryNum(numAdd,repo.getRepoId())){
                                        repo.setRepoNum(numAdd);
                                    }

                                }
                            }
                        }
                    }
                }
                returnInfo = returnInfo && success;
            }
            return returnInfo;
        }
        return null;
    }

     /**
     * 库房操作
     */

    @PostMapping("/Repository/CreateRepository")
    public String CreateRepository(@RequestBody Repository repository) {
        return repositoryService.CreateRepository(repository);
    }
    @GetMapping("/Repository/DeleteRepository")
    public String DeleteRepository(HttpServletRequest request) {
        Long repoId = Long.parseLong(request.getParameter("repoId"));
        return repositoryService.DeleteRepository(repoId);
    }
    @GetMapping("/Repository/findByRepoId")
    public Repository findByRepoId(HttpServletRequest request) {
        System.out.println(request);
        Long repoId = Long.parseLong(request.getParameter("repoId"));
        return repositoryService.findByRepoId(repoId);
    }
    @GetMapping("/Repository/findAllRepository")
    public List<Repository> findAllRepository() {
        List<InStorage> inStorageList=inStorageService.findAllInStorage();
        List<OutStorage> outStorageList=outStorageService.findAllOutStorage();
        List<Repository> repositoryList=repositoryService.findAllRepository();

        for (InStorage inStorage:inStorageList){
            Boolean flag=false;
            for (Repository repo:repositoryList){
                if(repo.getInStorage()==inStorage){
                    flag=true;
                    repo.setRepoFee(repo.getRepoNum()*0.02);
                }
            }
            if (!flag){
                if (inStorage.getInState().equals("分拣完成")) {
                    Repository repository = new Repository();
                    List<PurchaseDetail> purchaseDetailList = purchaseService.findByPurchaseDetail(inStorage.getPurchase());
                    for (PurchaseDetail pd : purchaseDetailList) {
                        repository.setRepoNum(pd.getBuyNum());
                        repository.setRepoFee(pd.getBuyNum() * 0.02);
                        repository.setRepoItem(pd.getCommodity().getName());
                        repository.setRepoState(pd.getCommodity().getClasses());
                    }
                    repository.setRepoUpNum(9999);
                    repository.setInStorage(inStorage);
                    SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
                    String randomNum = String.valueOf(new Random().nextInt(10) + 1);
                    Long repoId = Long.parseLong(df.format(System.currentTimeMillis()).toString() + randomNum);
                    String repoName = "KC" + repoId.toString();
                    repository.setRepoId(repoId);
                    repository.setRepoName(repoName);
                    if (repositoryService.CreateRepository(repository).equals("true")) {
                        repositoryList.add(repository);
                    }
                }
            }
        }
        return repositoryList;
    }

    @GetMapping("/Repository/findByDate")
    public List<Repository> findByDate(HttpServletRequest request) throws ParseException {
        System.out.println(request);
        String date = request.getParameter("date");
        JSONObject jsonObject = JSONObject.parseObject(date);
        JSONObject dateJson=JSONObject.parseObject(jsonObject.getString("values"));
        System.out.println(dateJson);
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = sdf2.parse(dateJson.getString("beginDate"));
        Date endDate = sdf2.parse(dateJson.getString("endDate"));
        System.out.println(beginDate);
        List<Repository> repositoryList=repositoryService.findAllRepository();
        List<Repository> repositoryListByDate=new ArrayList<Repository>();
        for(Repository repo : repositoryList) {
            System.out.println(repo.getInStorage().getInDate());
            if(repo.getInStorage().getInDate().after(beginDate)&&repo.getInStorage().getInDate().before(endDate)) {
//            Repository repository = indentService.findByIndentId(item.getIndId());
//            List<OrderDetail> detailList = indentService.findByIndentDetailId(indent);
                repositoryListByDate.add(repo);
            }
        }
        return repositoryListByDate;
    }
    @PostMapping("/OutStorage/UpdateRepository")
    public Boolean UpdateRepository(@RequestBody String resBody) throws ParseException {
        JSONObject obj = new JSONObject(Boolean.parseBoolean(resBody));
        System.out.println(resBody);

        String repoState = obj.get("repoState").toString();
        String repoItem = obj.get("repoItem").toString();
        Integer repoNum = Integer.parseInt(obj.get("repoNum").toString());
        Integer repoUpNum = Integer.parseInt(obj.get("repoUpNum").toString());
        Double repoFee = Double.parseDouble(obj.get("repoFee").toString());
        Long repoId = Long.parseLong(obj.get("repoId").toString());
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        String randomNum = String.valueOf(new Random().nextInt(10) + 1);
        String repoName="KC"+df.format(System.currentTimeMillis()).toString()+randomNum;


        Boolean success = repositoryService.UpdateRepository(repoState,  repoItem,  repoNum,  repoUpNum,  repoFee, repoName, repoId);
        System.out.println(success);
        Repository repository = new Repository();
        repository.setRepoState(repoState);
        repository.setRepoFee(repoFee);
        repository.setRepoId(repoId);
        repository.setRepoItem(repoItem);
        repository.setRepoNum(repoNum);
        repository.setRepoUpNum(repoUpNum);
        repository.setRepoName(repoName);

        System.out.println(success);
        return success;
    }

}
