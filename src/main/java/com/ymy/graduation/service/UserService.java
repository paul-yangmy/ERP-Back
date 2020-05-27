package com.ymy.graduation.service;

import com.ymy.graduation.domain.User;
import org.json.JSONObject;

import java.util.List;

public interface UserService {
    String CreateUser(User user);
    String DeleteUser(Integer uId);

    User findByUId(Integer uId);
    User findByUName(String uName);
    List<User> findAll();
    List<User> findByGroup(String uGroup);

    JSONObject LoginUser(String uName, String uPwd);

    String UpdateUser(String uName,String uGroup,String uSex,String uTitle,String uPhone,String uEmail,Integer uId);
    String UpdatePwd(String old_pwd,String new_pwd,Integer uId);
}
