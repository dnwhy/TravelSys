package com.etc.travelsys.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DynamicEntity {
    private int dynamicid;
    private String dcontent;
    private Timestamp publishtime;
    private int publishid;
    private String publishname;
}
