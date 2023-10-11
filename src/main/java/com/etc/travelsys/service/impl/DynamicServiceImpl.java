package com.etc.travelsys.service.impl;

import com.etc.travelsys.mapper.DynamicMapper;
import com.etc.travelsys.model.entity.DynamicEntity;
import com.etc.travelsys.model.vo.FriendDynamicVo;
import com.etc.travelsys.service.IDynamicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("dynamicService")
public class DynamicServiceImpl implements IDynamicService {
    @Autowired
    private DynamicMapper dynamicMapper;

    //我的动态
    @Override
    public List<DynamicEntity> queryMyDynamic(int userid) {
        return dynamicMapper.queryMyDynamic(userid);
    }

    //发表动态
    @Override
    public boolean addDynamic(DynamicEntity dynamic) {
        return dynamicMapper.addDynamic(dynamic);
    }

    //删除动态
    @Override
    public boolean delDynamic(int dynamicid) {
        return dynamicMapper.delDynamic(dynamicid);
    }

    //好友动态
    @Override
    public List<FriendDynamicVo> queryFriendDynamic(int userid) {
        return dynamicMapper.queryFriendDynamic(userid);
    }
}
