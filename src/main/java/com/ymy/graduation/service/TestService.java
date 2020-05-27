package com.ymy.graduation.service;

import com.ymy.graduation.domain.TestReport;

import java.util.List;

/**
 * Created by ymyum on 2020/1/16
 */
public interface TestService {
    String CreateTestReport(TestReport testReport);
//    String DeleteTestReport(Integer testId);

    TestReport findByTestId(Integer testId);
    List<TestReport> findAllTestReport();

//    String UpdateTestReport(String testFacility, Date testDate, String testMan, String testSupplier, Integer testId);
}
