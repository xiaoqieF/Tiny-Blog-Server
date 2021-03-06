package com.fxd.tinyBlogServer.service;

import com.fxd.tinyBlogServer.pojo.User;

public interface UserService {
    User findUserByNameAndPassword(String username, String password);
    User getUserById(Long id);
    int updateUser(User user);

    User getDefaultUser();
}
