package com.ymy.graduation.service.impl;

import com.ymy.graduation.domain.User;
import com.ymy.graduation.repository.userTab;
import com.ymy.graduation.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private userTab userTab;

    @Override
    public String CreateUser(User user) {
        User user1=userTab.findByUName(user.getuName());
        if(user1!=null){
            return "Exists";//存在同名用户
        }
        else
        {
            boolean flag=false;
           // user.setuAvatar("\\static\\portrait\\init.jpg");//为新用户设置初始头像
            try {
                userTab.save(user);
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

    @Override
    public String DeleteUser(Integer uId) {
        User user=userTab.findByUId(uId);
        if(user==null)
        {
            return "null";
        }
        else
        {
            boolean flag=false;
            try {
                userTab.delete(user);
                flag=true;
            }
            catch (Exception e){
                System.out.println(e);
            }
            if(flag){return "true";}
            else{return "false";}
        }
    }

    @Override
    public User findByUId(Integer uId) {
        User user=userTab.findByUId(uId);
        if(user==null){
            return null;
        }
        else {
            return user;
        }
    }

    @Override
    public User findByUName(String uName) {
        return userTab.findByUName(uName);
    }

    @Override
    public List<User> findAll() {
        return userTab.findAll();
    }

    @Override
    public List<User> findByGroup(String uGroup) { return userTab.findByUGroup(uGroup); }

    @Override
    public JSONObject LoginUser(String uName, String uPwd) {
        User user=userTab.findByUName(uName);
        JSONObject resJson = new JSONObject();
        if(user==null)
        {
            return resJson.put("status","");
        }
        else {
            boolean flag=false;
            if(user.getuPwd().equals(uPwd))
            {
                flag=true;
            }
            if(flag){
                resJson.put("status",true);
                resJson.put("currentAuthority",user.getuGroup());
                resJson.put("type",user.getuGroup());
                resJson.put("uName",user.getuName());
                return resJson;}
            else {
                return  resJson.put("status",false);
            }
        }
    }

    @Override
    public String UpdatePwd(String old_pwd, String new_pwd, Integer uId) {
        User user=userTab.findByUId(uId);
        System.out.println(user.getuPwd());
        if(user==null){
            return null;
        }
        else {
            boolean flag=false;
            if(old_pwd.equals(user.getuPwd())){
                try {
                    userTab.UpdatePwd(new_pwd,uId);
                    flag=true;
                }
                catch (Exception e){
                    System.out.println(e);
                }
            }
            if(flag){return "true";}
            else {return "false";}
        }
    }

    @Override
    public String UpdateUser(String uName,String uGroup,String uSex,String uTitle,String uPhone,String uEmail,Integer uId) {
        User user=userTab.findByUId(uId);
        System.out.println(user);
        if(user==null){
            return null;
        }
        else {
            boolean flag=false;
            try {
                userTab.UpdateUser(uName,uGroup,uSex,uTitle,uPhone,uEmail,uId);
                flag=true;
            }
            catch (Exception e){
                System.out.println(e);
            }
            if(flag){return "true";}
            else {return "false";}
        }
    }

}

