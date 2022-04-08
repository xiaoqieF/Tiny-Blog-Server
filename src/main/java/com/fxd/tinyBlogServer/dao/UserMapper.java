package com.fxd.tinyBlogServer.dao;

import com.fxd.tinyBlogServer.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    User findUserByNameAndPassword(@Param("username") String username, @Param("password") String password);
    User getUserById(@Param("id") Long id);
    int updateUser(User user);
    User getDefaultUser();
}
