package com.fxd.tinyBlogServer.controller;

import com.fxd.tinyBlogServer.pojo.Blog;
import com.fxd.tinyBlogServer.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogController {

    @Autowired
    BlogService blogService;

    @RequestMapping("/private/blog/add")
    public String addBlog() {
        Blog blog = new Blog();
        blog.setAppreciation(true);
        blog.setContent("内容");
        int res = blogService.addBlog(blog);
        return "add :" + res + "条";
    }
}
