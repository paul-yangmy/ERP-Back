package com.ymy.graduation.repository;

import com.ymy.graduation.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

//Respository为一个接口规范,有不同的子接口继承,每个子接口除了继承父接口所有功能外还会添加额外的方法,用于不同的实现。

public interface userTab extends JpaRepository <User,Integer> {
    @Query(value = "SELECT user From User user WHERE user.uId = ?1")
    User findByUId(Integer uId);

    @Query(value = "SELECT user From User user WHERE user.uName = ?1")
    User findByUName(String uName);

    @Query(value = "SELECT user From User user WHERE user.uGroup = ?1")
    List<User> findByUGroup(String uGroup);

    //修改密码
    @Transactional
    @Modifying//增删改
    @Query(value = "UPDATE User user SET user.uPwd=?1 WHERE user.uId=?2")
    void UpdatePwd(String uPwd,Integer uId);

    //修改个人信息
    @Transactional
    @Modifying
    @Query(value = "UPDATE User user SET user.uName=?1,user.uGroup=?2,user.uSex=?3,user.uTitle=?4,user.uPhone=?5,user.uEmail=?6 WHERE user.uId=?7")
    void UpdateUser(String uName,String uGroup,String uSex,String uTitle,String uPhone,String uEmail,Integer uId);

}

