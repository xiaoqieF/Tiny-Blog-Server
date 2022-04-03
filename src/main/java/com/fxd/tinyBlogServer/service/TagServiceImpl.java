package com.fxd.tinyBlogServer.service;

import com.fxd.tinyBlogServer.dao.TagMapper;
import com.fxd.tinyBlogServer.pojo.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService{

    @Autowired
    private TagMapper mapper;

    @Override
    public List<Tag> getAllTags() {
        return mapper.getAllTags();
    }

    @Override
    public int addTag(String name) {
        Tag tag = mapper.getTagByName(name);
        System.out.println(tag);
        if (tag == null) {
            return mapper.addTag(name);
        }
        return 0;
    }

    @Override
    public int deleteTag(long id) {
        return mapper.deleteTag(id);
    }
}
