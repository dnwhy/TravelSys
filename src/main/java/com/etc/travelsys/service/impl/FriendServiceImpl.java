package com.etc.travelsys.service.impl;

import com.etc.travelsys.mapper.FriendMapper;
import com.etc.travelsys.model.entity.UserEntity;
import com.etc.travelsys.model.vo.UserVo;
import com.etc.travelsys.service.IFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("friendService")
public class FriendServiceImpl implements IFriendService {
    @Autowired
    private FriendMapper friendMapper;

    //分页并搜索 展示好友列表
    @Override
    public List<UserEntity> queryFriendList(int userid, String searchName) {
        return friendMapper.queryFriendList(userid,searchName);
    }

    //取消好友关注
    @Override
    public boolean delAttention(int userid, int friendId) {
        return friendMapper.delAttention(userid,friendId);
    }

    //关注好友---  查询作者详情
    @Override
    public UserVo queryFriendDetail(int userid, int publishid) {
        return friendMapper.queryFriendDetail(userid,publishid);
    }

    //关注好友和取消关注
    @Override
    public boolean updateAttention(int friendid, String isAttention, int userid) {
        if ("未关注".equals(isAttention)){
            //2.isAttention == 未关注   调用mapper中的方法  参数：userid  friendid   返回值：Boolean
            return friendMapper.attention(userid,friendid);
        }else {
            //3.isAttention == 已关注   调用mapper中的方法  参数：userid  friendid   返回值：Boolean
            return friendMapper.delAttention(userid,friendid);
        }
    }
}
