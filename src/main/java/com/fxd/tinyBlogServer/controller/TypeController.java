package com.fxd.tinyBlogServer.controller;

import com.fxd.tinyBlogServer.pojo.Type;
import com.fxd.tinyBlogServer.pojo.inter.MetaData;
import com.fxd.tinyBlogServer.service.TypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


//博客分类管理器
@Slf4j
@RestController
public class TypeController {

    @Autowired
    TypeService typeService;

    // 查询全部分类
    @GetMapping("/public/categories")
    public Map<String, Object> getAllCate() {
        Map<String, Object> res = new HashMap<>();
        res.put("data", typeService.getAllTypes());
        res.put("meta", new MetaData(200, "请求成功！"));
        return res;
    }

    // 添加分类
    @PostMapping("/private/categories")
    public Map<String, Object> addCate(@RequestBody Map<String, Object> params) {
        Map<String, Object> res = new HashMap<>();
        int num = typeService.addType((String) params.get("name"));
        if (num > 0) {
            res.put("meta", new MetaData(200, "请求成功！"));
        } else {
            res.put("meta", new MetaData(400, "该分类已存在！"));
        }
        return res;
    }

    // 删除分类
    @DeleteMapping("/private/categories/{cateId}")
    public Map<String, Object> removeCate(@PathVariable("cateId") long id) {
        log.info("[removeCate] pathVariable:{}", id);
        Map<String, Object> res = new HashMap<>();
        int num = typeService.deleteType(id);
        if (num > 0) {
            res.put("meta", new MetaData(200, "请求成功！"));
        } else {
            res.put("meta", new MetaData(400, "删除失败！"));
        }
        return res;
    }
}
