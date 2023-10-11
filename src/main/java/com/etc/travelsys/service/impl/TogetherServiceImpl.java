package com.etc.travelsys.service.impl;

import com.etc.travelsys.mapper.TogetherMapper;
import com.etc.travelsys.model.entity.TogetherEntity;
import com.etc.travelsys.service.ITogetherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("togetherService")
public class TogetherServiceImpl implements ITogetherService {

    //自动注入TogetherMapper对象
    @Autowired
    private TogetherMapper togetherMapper;

    //搜索并分页查询结伴游列表
    @Override
    public List<TogetherEntity> queryTogether(String searchAddress) {
        return togetherMapper.queryTogether(searchAddress);
    }

    //查询结伴游详情
    @Override
    public TogetherEntity queryTogetherDetail(int togetherid) {
        return togetherMapper.queryTogetherDetail(togetherid);
    }

    //添加结伴游
    @Override
    public boolean addTogether(TogetherEntity together) {
        return togetherMapper.addTogether(together);
    }

    //分页查询我发布的结伴游
    @Override
    public List<TogetherEntity> queryMyTogether(int userid) {
        return togetherMapper.queryMyTogether(userid);
    }

    //删除结伴游
    @Override
    public boolean delTogether(int togetherid) {
        return togetherMapper.delTogether(togetherid);
    }

    //修改结伴游
    @Override
    public boolean updateTogether(TogetherEntity together) {
        return togetherMapper.updateTogether(together);
    }
}
