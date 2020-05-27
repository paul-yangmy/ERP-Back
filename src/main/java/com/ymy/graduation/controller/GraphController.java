package com.ymy.graduation.controller;

import com.ymy.graduation.domain.User;
import com.ymy.graduation.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * Created by ymyum on 2020/1/6
 */

@RestController
@RequestMapping(value = "/uploadGraph",produces="application/json;charset=utf-8")
public class GraphController {
    @Autowired//@Autowired 注释，它可以对类成员变量、方法及构造函数进行标注，完成自动装配的工作。 通过 @Autowired的使用来消除 set ，get方法。
    private UserServiceImpl userService;

    private User currentUser=new User();//当前用户保存

    @PostMapping("/UpdateAvatar")
    public void UpdateAvatar(@RequestParam("file") MultipartFile[] multipartFiles, HttpServletResponse resp){
        String filePath="D://temp-rainy//";
        MultipartFile multipartFile = multipartFiles[0];
        String filename=currentUser.getuName()+"_"+".jpg";
        File dest=new File(filePath + filename);
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdirs();
        }
        try{
            multipartFile.transferTo(dest);
        //  resp.sendRedirect("http://localhost:8000/settings");
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
