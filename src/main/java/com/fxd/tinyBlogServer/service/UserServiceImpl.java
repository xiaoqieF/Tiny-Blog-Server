package com.fxd.tinyBlogServer.service;

import com.fxd.tinyBlogServer.dao.UserMapper;
import com.fxd.tinyBlogServer.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper mapper;

    @Override
    public User findUserByNameAndPassword(String username, String password) {
        return mapper.findUserByNameAndPassword(username, password);
    }
}
