package com.fxd.tinyBlogServer.service;

import com.fxd.tinyBlogServer.pojo.Comment;

import java.util.List;

public interface CommentService {
    int addComment(Comment comment);
    List<Comment> getCommentByBlogId(Long blogId);
}
