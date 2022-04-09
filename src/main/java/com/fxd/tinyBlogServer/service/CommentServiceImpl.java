package com.fxd.tinyBlogServer.service;

import com.fxd.tinyBlogServer.dao.CommentMapper;
import com.fxd.tinyBlogServer.pojo.Comment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentMapper mapper;

    @Override
    public int addComment(Comment comment) {
        comment.setCreateTime(new Date());
        return mapper.addComment(comment);
    }

    @Override
    public List<Comment> getCommentByBlogId(Long blogId) {
        return mapper.getCommentByBlogId(blogId);
    }
}
