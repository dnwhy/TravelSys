package com.etc.travelsys.service.impl;

import com.etc.travelsys.mapper.MemoryMapper;
import com.etc.travelsys.model.entity.CommentEntity;
import com.etc.travelsys.model.entity.MemoryEntity;
import com.etc.travelsys.model.entity.MemoryTypeEntity;
import com.etc.travelsys.model.vo.CityNumVo;
import com.etc.travelsys.service.IMemoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("memoryService")
public class MemoryServiceImpl implements IMemoryService {

    @Autowired
    private MemoryMapper memoryMapper;

    //查询首页前四条旅游记忆
    public List<MemoryEntity> queryIndexMemory() {
        //调用方法: 参数:无   返回值: List <MemoryEntity>
        return memoryMapper.queryIndexMemory();
    }

    //查询每个类型的前四条旅游记忆
    @Override
    public List<MemoryTypeEntity> queryTypeMemory() {
        //1、调用mapper中查询所有类型的方法   参数:无   返回值: List<memoryTypeEntity>
        List<MemoryTypeEntity> memoryTypes = memoryMapper.queryTypeMemory();

        //2.遍历所有类型的集合
        for (int i = 0; i < memoryTypes.size(); i++) {
            //3、查询每种类型对应的旅游记忆集合   参数，类型id --tid   返回值: List<MemoryEntity>
            List<MemoryEntity> memories = memoryMapper.queryMemoryByTid(memoryTypes.get(i).getTid());
            //4、设置旅游记忆集合到 MemoryTypeEntity对象中
            memoryTypes.get(i).setMemories(memories);
        }
        //5.返回值
        return memoryTypes;
    }

    //查询旅游记忆详情
    @Override
    public MemoryEntity queryMemoryDetail(int mid) {
        return memoryMapper.queryMemoryDetail(mid);
    }

    //查询此旅游记忆中所有评论
    @Override
    public List<CommentEntity> queryCommentList(int mid) {
        return memoryMapper.queryCommentList(mid);
    }

    //添加评论
    @Override
    public boolean addComment(CommentEntity comment) {
        return memoryMapper.addComment(comment);
    }

    //查询所有类型的方法
    @Override
    public List<MemoryTypeEntity> queryMoreList() {
        return memoryMapper.queryTypeMemory();
    }

    //查询类型对应的分页数据
    @Override
    public List<MemoryEntity> queryTypePage(int tid) {
        return memoryMapper.queryTypePage(tid);
    }

    //添加旅游记忆，上传图片
    @Override
    public boolean addMemory(MemoryEntity memory) {
        return memoryMapper.addMemory(memory);
    }

    //分页查询我发布的所有旅游记忆列表
    @Override
    public List<MemoryEntity> queryAllMemory(int userid) {
        return memoryMapper.queryAllMemory(userid);
    }

    //删除旅游记忆
    @Override
    public boolean delMemory(int mid) {
        return memoryMapper.delMemory(mid);
    }

    //修改旅游记忆详情
    @Override
    public void updateMemory(MemoryEntity memory) {
        memoryMapper.updateMemory(memory);
    }

    //查询旅游攻略的城市景点统计
    @Override
    public List<CityNumVo> queryCityNum() {
        return memoryMapper.queryCityNum();
    }
}
