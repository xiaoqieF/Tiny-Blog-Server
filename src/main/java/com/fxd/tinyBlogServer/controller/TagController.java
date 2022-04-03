package com.fxd.tinyBlogServer.controller;

import com.fxd.tinyBlogServer.pojo.inter.MetaData;
import com.fxd.tinyBlogServer.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

// 博客标签控制器
@Slf4j
@RestController
public class TagController {

    @Autowired
    private TagService tagService;

    // 获取全部标签
    @GetMapping("/public/tags")
    public Map<String, Object> getAllTags() {
        Map<String, Object> res = new HashMap<>();
        res.put("data", tagService.getAllTags());
        res.put("meta", new MetaData(200, "请求成功！"));
        return res;
    }

    // 添加标签
    @PostMapping("/private/tags")
    public Map<String, Object> addTag(@RequestBody Map<String, Object> params) {
        Map<String, Object> res = new HashMap<>();
        int num = tagService.addTag((String) params.get("name"));
        if (num > 0) {
            res.put("meta", new MetaData(200, "请求成功！"));
        } else {
            res.put("meta", new MetaData(400, "该标签已存在！"));
        }
        return res;
    }

    // 删除标签
    @DeleteMapping("/private/tags/{tagId}")
    public Map<String, Object> removeTag(@PathVariable("tagId") long id) {
        log.info("[removeTag] pathVariable:{}", id);
        Map<String, Object> res = new HashMap<>();
        int num = tagService.deleteTag(id);
        if (num > 0) {
            res.put("meta", new MetaData(200, "请求成功！"));
        } else {
            res.put("meta", new MetaData(400, "删除失败！"));
        }
        return res;
    }
}
