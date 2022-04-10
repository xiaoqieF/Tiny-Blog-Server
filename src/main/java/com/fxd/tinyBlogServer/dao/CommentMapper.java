package com.fxd.tinyBlogServer.dao;

import com.fxd.tinyBlogServer.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    int addComment(Comment comment);
    List<Comment> getCommentByBlogId(Long blogId);
}
