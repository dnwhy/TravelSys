package com.etc.travelsys.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemoryEntity {
    private int mid;     //旅游记忆id
    private int tid;        //旅游记忆类型id
    private String mtitle;  //旅游记忆标题
    private int publishid;  //发布人id
    private String publishname;  //发布者姓名
    private Timestamp publishtime;  //发布时间
    private String mcontent; //旅游记忆内容
    private String img;  //旅游记忆图片
    private String tname; //旅游记忆类型名称
}
