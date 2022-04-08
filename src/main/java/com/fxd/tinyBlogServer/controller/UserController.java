package com.fxd.tinyBlogServer.controller;

import com.fxd.tinyBlogServer.Utils.FileUploadUtil;
import com.fxd.tinyBlogServer.Utils.JWTUtil;
import com.fxd.tinyBlogServer.pojo.User;
import com.fxd.tinyBlogServer.pojo.inter.MetaData;
import com.fxd.tinyBlogServer.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    // 登录请求
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
            String token = JWTUtil.getToken(payload, 40 * 60);

            res.put("meta", new MetaData(200, "登录成功！"));
            Map<String ,Object> data = new HashMap<>();
            data.put("token", token);
            data.put("user", user1);
            res.put("data", data);
        }
        return res;
    }

    // 查询用户请求
    @GetMapping("/private/user/{id}")
    public Map<String, Object> getUserInfo(@PathVariable("id") Long id) {
        Map<String, Object> res = new HashMap<>();
        User user = userService.getUserById(id);
        if (user == null) {
            res.put("meta", new MetaData(401, "用户不存在！"));
        } else {
            res.put("meta", new MetaData(200, "获取用户信息成功！"));
            res.put("data", user);
        }
        return res;
    }

    // 查询首页默认用户信息
    @GetMapping("/public/user/default")
    public Map<String, Object> getUserInfo() {
        Map<String, Object> res = new HashMap<>();
        User user = userService.getDefaultUser();
        if (user == null) {
            res.put("meta", new MetaData(401, "用户不存在！"));
        } else {
            res.put("meta", new MetaData(200, "获取用户信息成功！"));
            res.put("data", user);
        }
        return res;
    }

    // 更改用户信息
    @PutMapping("/private/user/{id}")
    public Map<String, Object> updateUserInfo(@PathVariable("id") Long id, @RequestBody User user) {
        Map<String, Object> res = new HashMap<>();
        int num = userService.updateUser(user);
        if (num > 0) {
            res.put("meta", new MetaData(200, "修改用户信息成功！"));
        } else {
            res.put("meta", new MetaData(401, "修改用户信息失败！"));
        }
        return res;
    }

    // 上传头像
    @PostMapping("/private/user/upload")
    public Map<String, Object> uploadAvatar(@RequestParam("file") MultipartFile uploadFile, HttpServletRequest req) throws IOException {
        return FileUploadUtil.saveTo("/uploadAvatar", uploadFile, req);
    }
}
