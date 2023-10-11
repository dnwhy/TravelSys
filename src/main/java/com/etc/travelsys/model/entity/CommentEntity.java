package com.etc.travelsys.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentEntity {
    private int cid;  //评论id
    private int userid;  //评论人id
    private String username;  //评论人姓名
    private Timestamp ctime;  //评论添加时间
    private int mid;  //旅游记忆id
    private String ccontent;  //评论内容
    private String headimg;   //评论人头像
}
