package com.ymy.graduation.controller;

import com.ymy.graduation.domain.User;
import com.ymy.graduation.service.impl.UserServiceImpl;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author ymyum
 */
@RestController
@RequestMapping(value = "/userController",produces="application/json;charset=utf-8")
/**@RequestMapping定义其类映射的url,此处我们接受的数据为普通String类型,如果需要接受Json类型,则需 @RequestBody String id 如此配置接受请求参数。*/
public class UserController {
    @Autowired
    /**@Autowired 注释，它可以对类成员变量、方法及构造函数进行标注，完成自动装配的工作。 通过 @Autowired的使用来消除 set ，get方法。*/
    private UserServiceImpl userService;

    private User currentUser=new User();
//    private Integer n=0;//计数头像

    @PostMapping("/CreateUser")
    public String CreateUser(@RequestBody User user){return userService.CreateUser(user);}

    @GetMapping("/DeleteUser")
    public String DeleteUser(HttpServletRequest request){
        System.out.println(request);
        Integer uId=Integer.parseInt(request.getParameter("uId"));
        System.out.println(uId);
        return userService.DeleteUser(uId);
    }

    @GetMapping("/getCurrentUser")
    public User getCurrentUser(HttpServletRequest request){
        System.out.println(request);
        String uName=request.getParameter("uName");
        return userService.findByUName(uName);
    }//返回当前用户

    @GetMapping("/findByUId/uId={uId}")
    public User findByUId(@PathVariable("uId") Integer uId){return userService.findByUId(uId);}

    @GetMapping("/findAll")
    public List<User> findAll(){return userService.findAll();}


    @GetMapping("/findByGroup")
    public List<User> findByGroup(HttpServletRequest request){
        String uGroup=request.getParameter("uGroup");
        if (uGroup.equals("admin")){return userService.findAll();}
        else{ return userService.findByGroup(uGroup);}
    }

    @PostMapping("/LoginUser")
    @ResponseBody
/**   public String LoginUser(HttpServletRequest req){*/
    public String LoginUser(@RequestBody String resBody){
        JSONObject obj = new JSONObject(resBody);

        System.out.println(resBody);
        String uName=obj.get("userName").toString();
        String uPwd=obj.get("password").toString();
        System.out.println(uName);
        System.out.println(uPwd);
        JSONObject success=userService.LoginUser(uName,uPwd);
        if(success.get("status").equals(true)){
            currentUser=userService.findByUName(uName);
        }
        System.out.println(success);
        return success.toString();
    }

    @GetMapping("/LogoutUser")
    public void LogoutUser(){
        currentUser=null;
    }

    @PostMapping("/UpdateUser")
    public String UpdateUser(@RequestBody String resBody){
        JSONObject obj = new JSONObject(resBody);
        System.out.println(resBody);
        String uName=obj.get("uName").toString();
        String uGroup=obj.get("uGroup").toString();
        String uSex=obj.get("uSex").toString();
        String uTitle=obj.get("uTitle").toString();
        String uPhone=obj.get("uPhone").toString();
        String uEmail=obj.get("uEmail").toString();
        Integer uId=Integer.parseInt(obj.get("uId").toString());
        String success= userService.UpdateUser(uName,uGroup,uSex,uTitle,uPhone,uEmail,uId);
        System.out.println(success);
        currentUser.setuName(uName);
        currentUser.setuSex(uSex);
        currentUser.setuTitle(uTitle);
        currentUser.setuGroup(uGroup);
        currentUser.setuPhone(uPhone);
        currentUser.setuEmail(uEmail);
        System.out.println(success);
        return success;
    }

    @PostMapping("/UpdatePwd")
    public String UpdatePwd(@RequestBody String resBody){
        JSONObject obj = new JSONObject(resBody);
        String old_pwd=obj.get("oldPassword").toString();
        String new_pwd=obj.get("newPassword").toString();
        Integer uId=Integer.parseInt(obj.get("uId").toString());
        String success=userService.UpdatePwd(old_pwd, new_pwd, uId);
        System.out.println(success);
        if(success.equals("true")){
            currentUser.setuPwd(new_pwd);
            //currentUser=userService.findByUName(currentUser.getuName());
        }
        //若返回false，即原密码错误，则不同步信息
        return success;
    }


}

