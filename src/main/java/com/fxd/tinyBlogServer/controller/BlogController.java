package com.fxd.tinyBlogServer.controller;

import com.fxd.tinyBlogServer.Utils.FileUploadUtil;
import com.fxd.tinyBlogServer.pojo.Blog;
import com.fxd.tinyBlogServer.pojo.inter.BlogInfo;
import com.fxd.tinyBlogServer.pojo.inter.MetaData;
import com.fxd.tinyBlogServer.service.BlogService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class BlogController {

    @Autowired
    BlogService blogService;

    // 获取博客列表信息
    @GetMapping("/private/blog")
    public Map<String, Object> getAllBlogs(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        // List<BlogInfo> blogInfos = blogService.getAllBlogs();
        PageInfo<BlogInfo> pageInfo = blogService.findBlogsByPage(pageNum, pageSize);
        List<BlogInfo> blogInfos = pageInfo.getList();
        log.info("get blogs：{}", blogInfos.size());

        Map<String, Object> data = new HashMap<>();
        data.put("blogList", blogInfos);
        data.put("total", pageInfo.getTotal());

        Map<String, Object> res = new HashMap<>();
        res.put("meta", new MetaData(200, "获取数据成功"));
        res.put("data", data);
        return res;
    }

    // 添加博客
    @PostMapping("/private/blog")
    public Map<String, Object> addBlog(@RequestBody Blog blog) {
        log.info("接收上传博客：{}", blog);
        int num = blogService.addBlog(blog);
        Map<String, Object> res = new HashMap<>();
        if (num <= 0) {
            res.put("meta", new MetaData(401, "上传失败"));
        } else {
            res.put("meta", new MetaData(200, "上传成功"));
        }
        return res;
    }

    // 上传博客首图
    @PostMapping("/private/blog/upload")
    public Map<String, Object> uploadAvatar(@RequestParam("file") MultipartFile uploadFile, HttpServletRequest req) throws IOException {
        return FileUploadUtil.saveTo("/uploadImg", uploadFile, req);
    }

    // 根据id查询博客
    @GetMapping("/public/blog/{blogId}")
    public Map<String, Object> getBlogById(@PathVariable("blogId") Long blogId) {
        Map<String, Object> res = new HashMap<>();
        Blog blog = blogService.getBlogById(blogId);
        if (blog == null) {
            res.put("meta", new MetaData(401, "获取博客失败"));
        } else {
            res.put("meta", new MetaData(200, "获取博客成功"));
            res.put("data", blog);
        }
        return res;
    }

    // 根据id删除博客
    @DeleteMapping("/private/blog/{blogId}")
    public Map<String, Object> removeBlog(@PathVariable("blogId") Long blogId) {
        int num = blogService.deleteBlogById(blogId);
        Map<String, Object> res = new HashMap<>();
        if (num <= 0) {
            res.put("meta", new MetaData(401, "删除失败"));
        } else {
            res.put("meta", new MetaData(200, "删除成功"));
        }
        return res;
    }

    @PutMapping("/private/blog")
    public Map<String, Object> updateBlog(@RequestBody Blog blog) {
        int num = blogService.updateBlog(blog);
        log.info("编辑博客{}，更新：{}", blog.getId(), num);
        Map<String, Object> res = new HashMap<>();
        if (num <= 0) {
            res.put("meta", new MetaData(401, "编辑失败"));
        } else {
            res.put("meta", new MetaData(200, "编辑成功"));
        }
        return res;
    }

}
