package com.etc.travelsys.mapper;

import com.etc.travelsys.model.entity.CommentEntity;
import com.etc.travelsys.model.entity.MemoryEntity;
import com.etc.travelsys.model.entity.MemoryTypeEntity;
import com.etc.travelsys.model.vo.CityNumVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemoryMapper {

    //查询首页前四条旅游记忆
    List<MemoryEntity> queryIndexMemory();

    //查询所有旅游记忆类型
    List<MemoryTypeEntity> queryTypeMemory();

    //根据每个旅游记忆类型，查询旅游记忆的前四条数据
    List<MemoryEntity> queryMemoryByTid(int tid);

    //查询旅游记忆详情
    MemoryEntity queryMemoryDetail(int mid);

    //查询此旅游记忆中所有评论
    List<CommentEntity> queryCommentList(int mid);

    //添加评论
    boolean addComment(CommentEntity comment);


    //******查询类型对应的分页数据
    List<MemoryEntity> queryTypePage(int tid);

    //添加旅游记忆，上传图片
    boolean addMemory(MemoryEntity memory);

    //分页查询我发布的所有旅游记忆列表
    List<MemoryEntity> queryAllMemory(int userid);

    //删除旅游记忆
    boolean delMemory(int mid);

    //修改旅游记忆详情
    void updateMemory(MemoryEntity memory);

    //查询旅游攻略的城市景点统计
    List<CityNumVo> queryCityNum();
}
