package com.fxd.tinyBlogServer.service;

import com.fxd.tinyBlogServer.pojo.Tag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TagService {

    List<Tag> getAllTags();
    int addTag(String name);
    int deleteTag(long id);
}
