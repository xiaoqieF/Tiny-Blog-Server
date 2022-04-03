package com.fxd.tinyBlogServer.dao;

import com.fxd.tinyBlogServer.pojo.Blog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BlogMapper {
    public int addBlog(Blog blog);

}
