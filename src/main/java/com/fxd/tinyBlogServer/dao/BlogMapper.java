package com.fxd.tinyBlogServer.dao;

import com.fxd.tinyBlogServer.pojo.Blog;
import com.fxd.tinyBlogServer.pojo.inter.BlogInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlogMapper {
    // 添加博客
    int addBlog(Blog blog);
    // 添加博客标签
    int addBlogTag(Blog blog);
    // 获取所有博客的简要信息，(除内容)
    List<BlogInfo> getAllBlogs(boolean published);
    // 通过id查找博客
    Blog getBlogById(Long id);
    // 通过博客id删除博客
    int deleteBlogById(Long id);
    // 通过博客id删除相关的（博客--标签）中间表记录
    int deleteBlogTag(Long blog_id);
    // 更新博客信息
    int updateBlog(Blog blog);
    // 获取推荐的博客简要信息
    List<BlogInfo> getRecommendBlog();

    int updateBlogViews(Long blogId);
}
