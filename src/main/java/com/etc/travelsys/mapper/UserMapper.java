package com.etc.travelsys.mapper;

import com.etc.travelsys.model.dto.UserDto;
import com.etc.travelsys.model.entity.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;




@Repository
public interface UserMapper {

    //登录抽象方法,如果是两个以上的参数，可以使用@Param注解;如果是一个参数--不需要
    UserEntity login(@Param("username") String username,
                     @Param("password") String password);

    //验证用户名是否存在
    UserEntity checkUserName(String username);

    //注册功能
    int register(UserDto userDto);

    //修改用户基本信息
    boolean updateUser(UserEntity user);

    //修改头像
    boolean updateImg(@Param("img") String img, @Param("userid") int userid);

    //修改密码
    boolean updatePwd(@Param("newPwd") String newPwd, @Param("userid") int userid);

    //人脸注册
    void updateIsFace(int userid);

    //人脸登录
    UserEntity queryUserById(int userId);
}