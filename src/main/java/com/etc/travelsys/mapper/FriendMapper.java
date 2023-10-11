package com.etc.travelsys.mapper;

import com.etc.travelsys.model.entity.UserEntity;
import com.etc.travelsys.model.vo.UserVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface FriendMapper {

    //分页并搜索 展示好友列表
    List<UserEntity> queryFriendList(@RequestParam("userid") int userid,
                                     @RequestParam("searchName") String searchName);

    //取消好友关注
    boolean delAttention(@Param("userid") int userid, @Param("friendId") int friendId);

    //关注好友---  查询作者详情
    UserVo queryFriendDetail(@Param("userid") int userid, @Param("publishid") int publishid);

    //关注好友
    boolean attention(@Param("userid") int userid, @Param("friendid") int friendid);
}
