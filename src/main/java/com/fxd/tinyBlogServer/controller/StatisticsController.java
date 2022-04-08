package com.fxd.tinyBlogServer.controller;

import com.fxd.tinyBlogServer.pojo.inter.MetaData;
import com.fxd.tinyBlogServer.pojo.inter.StatisticsInfo;
import com.fxd.tinyBlogServer.service.BlogService;
import com.fxd.tinyBlogServer.service.TagService;
import com.fxd.tinyBlogServer.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

// 其它类型的控制器（统计信息等）
@RestController
public class StatisticsController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    @GetMapping("/public/statistics")
    public Map<String, Object> getStatisticsInfo() {
        StatisticsInfo info = new StatisticsInfo();
        info.setArchivesNum(blogService.getAllBlogs().size());
        info.setCategoriesNum(typeService.getAllTypes().size());
        info.setTagsNum(tagService.getAllTags().size());

        Map<String, Object> res = new HashMap<>();
        res.put("meta", new MetaData(200, "获取统计信息成功！"));
        res.put("data", info);
        return res;
    }
}
