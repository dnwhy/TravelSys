package com.etc.travelsys.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.Date;


@Data //相当于@Setter + Getter + @ToString + @EqualsAndHashCode
@NoArgsConstructor  //自动生成无参数构造函数。
@AllArgsConstructor  //自动生成全参数构造函数。
public class UserEntity {
    private int userid;  //用户id
    private String userName; //用户名
    private String password; //密码
    private String email; //邮箱
    private String sex; //性别  1-男  2-女
    private int status; //状态 1-账户正常 2-账户异常

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday; //出生日期

    private String location; //地址
    private String QQ; //qq号
    private String mobile; //手机号
    private String hobby; //爱好
    private String introduction;  //个人介绍
    private String headimg;  //头像地址
    private Timestamp registertime; //注册时间
    private int role; //角色 1-普通用户 2-管理员
    private int isFace; //是否人脸注册   0-未注册  1-已注册
}
