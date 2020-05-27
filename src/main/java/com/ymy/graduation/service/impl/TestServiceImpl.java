package com.ymy.graduation.service.impl;

import com.ymy.graduation.domain.TestReport;
import com.ymy.graduation.repository.testTab;
import com.ymy.graduation.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ymyum on 2020/1/16
 */
@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private testTab testTab;

    @Override
    public String CreateTestReport(TestReport testReport) {
        TestReport test1= testTab.findByTestId(testReport.getTestId());
        if(test1!=null){
            return "Exists";//存在同名
        }
        else
        {
            boolean flag=false;
            try {
                testTab.save(testReport);
                flag=true;
            }
            catch (Exception e){
                System.out.println(e);
            }
            if(flag){
                return "true";
            }
            else {
                return "false";
            }
        }
    }

//    @Override
//    public String DeleteTestReport(Integer testId) {
//        TestReport test= testTab.findByTestId(testId);
//        if(test==null){
//            return "null";//无效删除对象
//        }
//        else
//        {
//            boolean flag=false;
//            try {
//                testTab.save(test);
//                flag=true;
//            }
//            catch (Exception e){
//                System.out.println(e);
//            }
//            if(flag){
//                return "true";
//            }
//            else {
//                return "false";
//            }
//        }
//    }

    @Override
    public TestReport findByTestId(Integer testId) {
        TestReport test= testTab.findByTestId(testId);
        if(test==null){
            return null;
        }
        else {
            return test;
        }
    }

    @Override
    public List<TestReport> findAllTestReport() {
        return testTab.findAll();
    }

//    @Override
//    public String UpdateTestReport(String testFacility, Date testDate, String testMan, String testSupplier, Integer testId) {
//        TestReport test=testTab.findByTestId(testId);
//        System.out.println(test);
//        if(test==null){
//            return null;
//        }
//        else {
//            boolean flag=false;
//            try {
//                testTab.UpdateTestReport(testFacility, testDate, testMan, testSupplier,testId);
//                flag=true;
//            }
//            catch (Exception e){
//                System.out.println(e);
//            }
//            if(flag){return "true";}
//            else {return "false";}
//        }
//    }
}
