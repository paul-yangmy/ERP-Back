package com.ymy.graduation.repository;


import com.ymy.graduation.domain.TestReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by ymyum on 2020/1/16
 */
public interface testTab extends JpaRepository<TestReport,Integer> {
    //查找
    @Query(value = "SELECT test From TestReport test WHERE test.testId = ?1")
    TestReport findByTestId(Integer testId);

    //修改
//    @Transactional
//    @Modifying
//    @Query(value = "UPDATE TestReport test SET test.testFacility=?1,test.testDate=?2,test.testMan=?3,test.testSupplier=?4 WHERE test.testId=?5")
//    void UpdateTestReport(String testFacility,Date testDate,String testMan,String testSupplier,Integer testId);

}
