package com.fxd.tinyBlogServer.service;

import com.fxd.tinyBlogServer.dao.BlogMapper;
import com.fxd.tinyBlogServer.pojo.Blog;
import com.fxd.tinyBlogServer.pojo.inter.BlogInfo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface BlogService {

    int addBlog(Blog blog);
    List<BlogInfo> getAllBlogs();
    PageInfo<BlogInfo> findBlogsByPage(Integer page, Integer offset);
    Blog getBlogById(Long id);
    int deleteBlogById(Long id);
    int updateBlog(Blog blog);
    List<BlogInfo> getRecommendBlog();
}
