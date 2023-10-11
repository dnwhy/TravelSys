package com.etc.travelsys.service;

import com.etc.travelsys.model.entity.TogetherEntity;

import java.util.List;

public interface ITogetherService {
    //搜索并分页查询结伴游列表
    List<TogetherEntity> queryTogether(String searchAddress);

    //查询结伴游详情
    TogetherEntity queryTogetherDetail(int togetherid);

    //添加结伴游
    boolean addTogether(TogetherEntity together);

    //分页查询我发布的结伴游
    List<TogetherEntity> queryMyTogether(int userid);

    //删除结伴游
    boolean delTogether(int togetherid);

    //修改结伴游
    boolean updateTogether(TogetherEntity together);
}
