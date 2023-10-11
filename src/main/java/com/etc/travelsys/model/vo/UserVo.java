package com.etc.travelsys.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {
    private int userid;  //用户id
    private String userName; //用户名
    private String sex; //性别  1-男  2-女

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday; //出生日期

    private String location; //地址
    private String QQ; //qq号
    private String mobile; //手机号
    private String hobby; //爱好
    private String introduction;  //个人介绍
    private String headimg;  //头像地址
    private String isAttention; //是否关注
}
