package com.fxd.tinyBlogServer.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    private Long id;
    private String title;
    private String description;
    private String content;
    // 文首图片
    private String firstPicture;
    // 标记（转载，原创）
    private String flag;
    // 浏览次数
    private Integer views;
    // 开启赞赏
    private boolean appreciation;
    // 转载声明是否开启
    private boolean shareStatement;
    private boolean commentEnable;
    // 是否发布
    private boolean published;
    private boolean recommend;
    private Date createTime;
    private Date updateTime;

    // 类型id，外键
    private Long typeId;
    // 用户id，外键
    private Long userId;
}
