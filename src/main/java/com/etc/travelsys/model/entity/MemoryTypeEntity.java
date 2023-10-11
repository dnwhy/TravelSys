package com.etc.travelsys.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemoryTypeEntity {

    private int tid; //旅游记忆类型id
    private String tname; // 旅游记忆类型名称
    private String tintroduce; //旅游记忆类型描述
    private Timestamp addtime; //旅游记忆类型时间
    private List<MemoryEntity> memories; //每种类型对应的旅游记忆列表

}
