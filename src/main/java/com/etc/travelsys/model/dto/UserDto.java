package com.etc.travelsys.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    //字符串不能null,错误信息：用户名不能null
    @NotBlank(message = "用户名不能为空")
    private String userName; //注册的用户名

    @NotBlank(message = "密码不能为空")
    @Length(message = "密码只能是6~16位",min = 6,max = 16)
    private  String password;  //注册的密码

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email; //注册的邮箱
}
