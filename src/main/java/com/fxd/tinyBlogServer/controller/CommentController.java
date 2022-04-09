package com.fxd.tinyBlogServer.controller;

import com.fxd.tinyBlogServer.pojo.Comment;
import com.fxd.tinyBlogServer.pojo.inter.MetaData;
import com.fxd.tinyBlogServer.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    // 新增评论
    @PostMapping("public/comment")
    public Map<String, Object> addComment(@RequestBody Comment comment) {
        log.info("新增评论：{}", comment);
        int num = commentService.addComment(comment);
        Map<String, Object> res = new HashMap<>();
        if (num <= 0) {
            res.put("meta", new MetaData(401, "添加评论失败"));
        } else {
            res.put("meta", new MetaData(200, "评论成功"));
        }
        return res;
    }

    // 根据博客id获取博客的评论
    @GetMapping("public/comment/{blogId}")
    public Map<String, Object> getCommentByBlogId(@PathVariable Long blogId) {
        Map<String, Object> res = new HashMap<>();
        List<Comment> comments = commentService.getCommentByBlogId(blogId);
        if (comments == null) {
            res.put("meta", new MetaData(401, "获取评论失败！"));
        } else {
            res.put("meta", new MetaData(200, "获取评论成功！"));
            res.put("data", comments);
        }
        return res;
    }
}
