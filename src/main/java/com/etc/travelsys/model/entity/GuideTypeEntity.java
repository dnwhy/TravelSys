package com.etc.travelsys.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuideTypeEntity {

    private int tid; //旅游攻略类型id
    private String tname; //旅游攻略类型名称
    private String tintroduce; //旅游攻略类型介绍
    private List<GuideEntity> guides; //一个类型下的旅游攻略列表

}
