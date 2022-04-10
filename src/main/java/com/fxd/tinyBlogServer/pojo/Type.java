package com.fxd.tinyBlogServer.pojo;

import com.fxd.tinyBlogServer.pojo.inter.BlogInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Type {
    private Long id;
    private String name;

    // 属于该分类的博客信息
    List<BlogInfo> blogInfos;
}
