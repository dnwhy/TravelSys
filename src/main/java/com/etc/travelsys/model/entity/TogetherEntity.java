package com.etc.travelsys.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TogetherEntity {
    private int togetherid; //结伴游id
    private int publishid; //发起人id
    private String publishname; //发起人姓名
    private Timestamp publishtime; //发起时间
    private String togethertitle; //结伴游标题
    private String address; //目的地
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date starttime; //活动开始时间

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endtime; //互动结束时间
    private String togetherdetail; //结伴游详情
    private String togethermoblie; //联系电话
    private String img; //图片
}
