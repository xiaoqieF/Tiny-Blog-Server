package com.fxd.tinyBlogServer.dao;

import com.fxd.tinyBlogServer.pojo.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TagMapper {
    List<Tag> getAllTags();
    int addTag(@Param("name") String name);
    int deleteTag(@Param("id") long id);
    Tag getTagByName(@Param("name") String name);
}
