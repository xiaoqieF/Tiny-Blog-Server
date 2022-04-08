package com.fxd.tinyBlogServer.pojo.inter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 基本信息类
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticsInfo {
    // 文章归档总数量
    private int archivesNum;
    // 文章分类总数量
    private int categoriesNum;
    // 文章标签总数量
    private int tagsNum;
}
