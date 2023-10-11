package com.etc.travelsys.service;

import com.etc.travelsys.model.entity.UserEntity;
import com.etc.travelsys.model.vo.UserVo;

import java.util.List;

public interface IFriendService {


    //分页并搜索 展示好友列表
    List<UserEntity> queryFriendList(int userid, String searchName);

    //取消好友关注
    boolean delAttention(int userid, int friendId);

    //关注好友---  查询作者详情
    UserVo queryFriendDetail(int userid, int publishid);

    //关注好友和取消关注
    boolean updateAttention(int friendid, String isAttention, int userid);
}
