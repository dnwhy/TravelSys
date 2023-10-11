package com.etc.travelsys.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuideEntity {

    private int gid; //旅游攻略id
    private String gtitle; //旅游攻略标题
    private String time; //适宜时间
    private String crowd; //适宜人群
    private String img; //图片
    private int tid; //类型id
    private String gintroduce; //旅游攻略介绍
    private String equipment; //必备装备
    private String tips; //注意事项
    private String glocation; //城市
    private Timestamp addtime; //添加时间
    private int publishid; //发布者id
    private String publishname; //发布者姓名
    private String tname; //旅游攻略名称

}
