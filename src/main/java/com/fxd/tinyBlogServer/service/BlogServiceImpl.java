package com.fxd.tinyBlogServer.service;

import com.fxd.tinyBlogServer.dao.BlogMapper;
import com.fxd.tinyBlogServer.pojo.Blog;
import com.fxd.tinyBlogServer.pojo.Tag;
import com.fxd.tinyBlogServer.pojo.inter.BlogInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class BlogServiceImpl implements BlogService{
    @Autowired
    private BlogMapper mapper;

    @Override
    public int addBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        // 插入博客后，会自动给blog中的id赋值，这个id值用于下面的addBlogTag使用
        int num = mapper.addBlog(blog);
        log.info("插入博客：{}", blog);
        // 向中间表中插入关联数据
        mapper.addBlogTag(blog);
        return num;
    }

    @Override
    public List<BlogInfo> getAllBlogs() {
        return mapper.getAllBlogs();
    }

    @Override
    public PageInfo<BlogInfo> findBlogsByPage(Integer page, Integer offset) {
        // 未传参时，将全部页面返回
        if (page == null || offset == null) {
            return new PageInfo<>(mapper.getAllBlogs());
        }
        PageHelper.startPage(page, offset);
        List<BlogInfo> blogInfos = mapper.getAllBlogs();
        return new PageInfo<>(blogInfos);
    }

    @Override
    public Blog getBlogById(Long id) {
        Blog blog = mapper.getBlogById(id);
        if (blog == null) {
            return null;
        }
        // 填充typeId和tagId和userId字段
        blog.setTypeId(blog.getType().getId());
        blog.setUserId(blog.getUser().getId());
        List<Long> tagIds = new ArrayList<>();
        List<Tag> tags = blog.getTags();
        tags.forEach( tag -> {
            tagIds.add(tag.getId());
        });
        blog.setTagId(tagIds);
        return blog;
    }

    @Override
    public int deleteBlogById(Long id) {
        // 删除博客时，先删除博客标签的对应关系
        mapper.deleteBlogTag(id);
        return mapper.deleteBlogById(id);
    }

    @Override
    public int updateBlog(Blog blog) {
        blog.setUpdateTime(new Date());
        int num = mapper.updateBlog(blog);
        // 操作（博客--标签）关系表，先删除博客对应的原标签，后加入新标签
        mapper.deleteBlogTag(blog.getId());
        mapper.addBlogTag(blog);
        return num;
    }
}
