package com.fxd.tinyBlogServer.service;

import com.fxd.tinyBlogServer.dao.BlogMapper;
import com.fxd.tinyBlogServer.pojo.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public interface BlogService {

    public int addBlog(Blog blog);
}
