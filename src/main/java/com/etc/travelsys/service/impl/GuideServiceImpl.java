package com.etc.travelsys.service.impl;

import com.etc.travelsys.mapper.GuideMapper;
import com.etc.travelsys.model.entity.GuideEntity;
import com.etc.travelsys.model.entity.GuideTypeEntity;
import com.etc.travelsys.service.IGuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("guideService")
public class GuideServiceImpl implements IGuideService {

    @Autowired
    private GuideMapper guideMapper;

    //查询所有旅游攻略类型
    @Override
    public List<GuideTypeEntity> queryAllType() {
        return guideMapper.queryAllType();
    }

    //添加旅游记忆攻略 -- 富文本编辑器
    @Override
    public boolean addGuide(GuideEntity guide) {
        return guideMapper.addGuide(guide);
    }

    //查询所有类型以及类型下的旅游攻略列表
    @Override
    public List<GuideTypeEntity> queryAllGuide() {
        return guideMapper.queryAllGuide();
    }

    //查询旅游攻略详情
    @Override
    public GuideEntity queryGuideDetail(int gid) {
        return guideMapper.queryGuideDetail(gid);
    }

    //查询所有的旅游攻略
    @Override
    public List<GuideEntity> queryAll() {
        return guideMapper.queryAll();
    }

    //删除攻略
    @Override
    public boolean delGuide(int gid) {
        return guideMapper.delGuide(gid);
    }
}
