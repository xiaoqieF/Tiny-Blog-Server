package com.fxd.tinyBlogServer.pojo;

import com.fxd.tinyBlogServer.pojo.inter.BlogInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tag {
    private Long id;
    private String name;

    private List<BlogInfo> blogInfos;
}
