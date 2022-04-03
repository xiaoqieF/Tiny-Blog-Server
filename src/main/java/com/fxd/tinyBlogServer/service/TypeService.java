package com.fxd.tinyBlogServer.service;

import com.fxd.tinyBlogServer.pojo.Type;

import java.util.List;

public interface TypeService {

    List<Type> getAllTypes();
    int addType(String name);
    int deleteType(long id);
}
