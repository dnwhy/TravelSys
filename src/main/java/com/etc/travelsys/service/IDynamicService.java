package com.etc.travelsys.service;

import com.etc.travelsys.model.entity.DynamicEntity;
import com.etc.travelsys.model.vo.FriendDynamicVo;

import java.util.List;

public interface IDynamicService {

    //我的动态
    List<DynamicEntity> queryMyDynamic(int userid);

    //发表动态
    boolean addDynamic(DynamicEntity dynamic);

    //删除动态
    boolean delDynamic(int dynamicid);

    //好友动态
    List<FriendDynamicVo> queryFriendDynamic(int userid);
}
