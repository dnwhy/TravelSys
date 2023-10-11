package com.etc.travelsys.service;


import com.etc.travelsys.model.entity.GuideEntity;
import com.etc.travelsys.model.entity.GuideTypeEntity;

import java.util.List;

public interface IGuideService {

    //查询所有旅游攻略类型
    List<GuideTypeEntity> queryAllType();

    //添加旅游记忆攻略 -- 富文本编辑器
    boolean addGuide(GuideEntity guide);

    //查询所有类型以及类型下的旅游攻略列表
    List<GuideTypeEntity> queryAllGuide();

    //查询旅游攻略详情
    GuideEntity queryGuideDetail(int gid);

    //查询所有的旅游攻略
    List<GuideEntity> queryAll();

    //删除攻略
    boolean delGuide(int gid);
}
