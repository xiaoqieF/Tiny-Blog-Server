package com.fxd.tinyBlogServer.service;

import com.fxd.tinyBlogServer.pojo.Blog;
import com.fxd.tinyBlogServer.pojo.inter.BlogInfo;
import com.github.pagehelper.PageInfo;

import java.util.List;


public interface BlogService {

    int addBlog(Blog blog);
    List<BlogInfo> getAllBlogs(boolean published);
    PageInfo<BlogInfo> findBlogsByPage(Integer page, Integer offset, boolean published);
    Blog getBlogById(Long id);
    Blog getRawBlogById(Long id);
    int deleteBlogById(Long id);
    int updateBlog(Blog blog);
    List<BlogInfo> getRecommendBlog();
    int increaseBlogViews(Long blogId);
}
