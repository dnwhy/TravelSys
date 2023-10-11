package com.etc.travelsys.service;

import com.etc.travelsys.model.dto.UserDto;
import com.etc.travelsys.model.entity.UserEntity;

public interface IUserService {

    //登录的抽象方法
    UserEntity login(String username, String password);

    //验证用户名是否存在
    boolean checkUserName(String username);

    //注册
    boolean register(UserDto userDto);

    //修改用户基本信息
    boolean updateUser(UserEntity user);

    //修改头像
    boolean updateImg(String img, int userid);

    //修改密码
    boolean updatePwd(String newPwd, int userid);

    //人脸注册
    void updateIsFace(int userid);

    //人脸登录
    UserEntity queryUserById(int userId);
}
