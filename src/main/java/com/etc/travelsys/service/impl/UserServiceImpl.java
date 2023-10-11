package com.etc.travelsys.service.impl;

import com.etc.travelsys.mapper.UserMapper;
import com.etc.travelsys.model.dto.UserDto;
import com.etc.travelsys.model.entity.UserEntity;
import com.etc.travelsys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService") //用于标注业务层组件
public class UserServiceImpl implements IUserService {

    //自动注入UserMapper对象
    @Autowired
    private UserMapper userMapper;

    //重写接口的登录方法
    @Override
    public UserEntity login(String username, String password) {

        return userMapper.login(username,password);
    }

    //验证用户名是否存在
    @Override
    public boolean checkUserName(String username) {
        //1.调用方法 参数：username 返回值：UserEntity
        UserEntity user =  userMapper.checkUserName(username);
        //2.处理结果并返回  UserEntity是否为null
        //true--不存在，可以注册  false--存在，用户名被占用
        return user == null;
    }

    //注册
    @Override
    public boolean register(UserDto userDto) {
        //1.调用方法  参数：UserDto  返回值：int 受影响的行数  ,处理结果并返回
        return userMapper.register(userDto) > 0;
    }

    //修改用户基本信息
    @Override
    public boolean updateUser(UserEntity user) {
        return userMapper.updateUser(user);
    }

    //修改头像
    @Override
    public boolean updateImg(String img, int userid) {
        return userMapper.updateImg(img,userid);
    }

    //修改密码
    @Override
    public boolean updatePwd(String newPwd, int userid) {
        return userMapper.updatePwd(newPwd,userid);
    }

    //人脸注册
    @Override
    public void updateIsFace(int userid) {
        userMapper.updateIsFace(userid);
    }

    //人脸登录
    @Override
    public UserEntity queryUserById(int userId) {
        return userMapper.queryUserById(userId);
    }
}
