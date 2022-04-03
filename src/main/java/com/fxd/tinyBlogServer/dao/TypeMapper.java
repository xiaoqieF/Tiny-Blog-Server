package com.fxd.tinyBlogServer.dao;

import com.fxd.tinyBlogServer.pojo.Tag;
import com.fxd.tinyBlogServer.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TypeMapper {
    List<Type> getAllTypes();
    int addType(@Param("name") String name);
    int deleteType(@Param("id") long id);
    Type getTypeByName(@Param("name") String name);
}
