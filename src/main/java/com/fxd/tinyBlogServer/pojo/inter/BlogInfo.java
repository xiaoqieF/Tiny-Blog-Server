package com.fxd.tinyBlogServer.pojo.inter;

import com.fxd.tinyBlogServer.pojo.Tag;
import com.fxd.tinyBlogServer.pojo.Type;
import com.fxd.tinyBlogServer.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// 简要博客信息类
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogInfo {
    private Long id;
    private String title;
    private String description;
    // 文首图片
    private String firstPicture;
    // 标记（转载，原创，翻译）
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

    private Type type;
    private User user;
    // 标签列表
    private List<Tag> tags = new ArrayList<>();
}
