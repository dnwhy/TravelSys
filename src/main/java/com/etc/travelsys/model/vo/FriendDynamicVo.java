package com.etc.travelsys.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendDynamicVo {
    private int dynamicid;
    private String dcontent;
    private Timestamp publishtime;
    private int publishid;
    private String publishname;
    private String headimg;
}
