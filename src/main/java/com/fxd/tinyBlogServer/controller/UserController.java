package com.fxd.tinyBlogServer.controller;

import com.fxd.tinyBlogServer.Utils.JWTUtil;
import com.fxd.tinyBlogServer.pojo.User;
import com.fxd.tinyBlogServer.pojo.inter.MetaData;
import com.fxd.tinyBlogServer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/public/login")
    public Map<String, Object> login(@RequestBody User user) {
        Map<String, Object> res = new HashMap<>();
        String username = user.getUsername();
        String password = user.getPassword();
        User user1 = userService.findUserByNameAndPassword(username, password);
        if (user1 == null) {
            res.put("meta", new MetaData(401, "用户名不存在或密码错误！"));
        } else {
            // 获取token
            Map<String, String> payload = new HashMap<>();
            payload.put("id", user1.getId().toString());
            payload.put("username", user1.getUsername());
            payload.put("password", user1.getPassword());
            // 20 min 过期
            String token = JWTUtil.getToken(payload, 20 * 60);

            res.put("meta", new MetaData(200, "登录成功！"));
            Map<String ,Object> data = new HashMap<>();
            data.put("token", token);
            data.put("user", user1);
            res.put("data", data);
        }
        return res;
    }
}
