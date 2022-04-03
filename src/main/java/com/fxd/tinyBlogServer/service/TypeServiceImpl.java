package com.fxd.tinyBlogServer.service;

import com.fxd.tinyBlogServer.dao.TypeMapper;
import com.fxd.tinyBlogServer.pojo.Tag;
import com.fxd.tinyBlogServer.pojo.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService{

    @Autowired
    private TypeMapper mapper;

    @Override
    public List<Type> getAllTypes() {
        return mapper.getAllTypes();
    }

    @Override
    public int addType(String name) {
        Type type = mapper.getTypeByName(name);
        System.out.println(type);
        if (type == null) {
            return mapper.addType(name);
        }
        return 0;
    }

    @Override
    public int deleteType(long id) {
        return mapper.deleteType(id);
    }


}
