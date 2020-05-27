package com.ymy.graduation.domain;
import lombok.Data;

import javax.persistence.*;

/**
 * @author ymyum
 * @date 2020/1/20 9:58
 * @project 用户
 */

@Data
@Entity//指定实体类 ;必须指定@Id
@Table(name = "UserTable")

public class User {
    @GeneratedValue //@GeneratedValue 用于标注主键的生成策略，通过strategy 属性指定。默认情况下，JPA 自动选择一个最适合底层数据库的主键生成策略：SqlServer对应identity，MySQL 对应 auto increment。
//    @GeneratedValue注解用于配置主键相关信息,generator属性用于配置生成策略有以下几种枚举值:
//            　　1、auto - 主键由程序控制 。
//            　　2、IDENTITY - 由数据库自动生成。
//            　　3、enerator -指定生成主键使用的生成器 。
//              　4、SEQUENCE - 根据底层数据库的序列来生成主键 。
//            　　5、TABLE - 使用一个特定的数据库表来保存主键。
//            　　6、system-uuid 代表使用系统生成的uuid进行配。
    @Id  /**@Id 标注用于声明一个实体类的属性映射为数据库的主键列。*/
    private Integer uId;//用户号
/**    @Column用于配置列相关信息的注解
    　 　1、name字段用于指定映射到表结构的映射字段。
         2、length代表此字段的长度约束,可以省略。
         3、unique属性代表此字段是否开启唯一性约束，默认为false,唯一则为true 。
         4、nullable代表此字段是否可以为空,默认为true 。 false代表不能为空 。*/
    @Column(nullable = false)
    private String uName;//昵称
    @Column(nullable = false)
    private String uPwd;//密码
    @Column
    private String uAvatar;//头像url
    @Column(nullable = false)
    private String uPhone;//联系电话
    @Column(nullable = false)
    private String uEmail;//联系邮件
    @Column
    private String uSex;//性别
    @Column
    private String uTitle;//标签
    @Column(nullable = false)
    private String uGroup;//权限组别

    public Integer getuId() {
        return uId;
    }

    public String getuName() {
        return uName;
    }

    public String getuPwd() {
        return uPwd;
    }

    public String getuAvatar() {
        return uAvatar;
    }

    public String getuPhone() {
        return uPhone;
    }

    public String getuEmail() {
        return uEmail;
    }

    public String getuSex() {
        return uSex;
    }

    public String getuTitle() {
        return uTitle;
    }

    public String getuGroup() {
        return uGroup;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public void setuPwd(String uPwd) {
        this.uPwd = uPwd;
    }

    public void setuAvatar(String uAvatar) {
        this.uAvatar = uAvatar;
    }

    public void setuPhone(String uPhone) {
        this.uPhone = uPhone;
    }

    public void setuEmail(String uEmail) {
        this.uEmail = uEmail;
    }

    public void setuSex(String uSex) {
        this.uSex = uSex;
    }

    public void setuTitle(String uTitle) {
        this.uTitle = uTitle;
    }

    public void setuGroup(String uGroup) {
        this.uGroup = uGroup;
    }
}
