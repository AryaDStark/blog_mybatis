package com.yangyu.service.Impl;

import com.yangyu.mapper.TagMapper;
import com.yangyu.po.Tag;
import com.yangyu.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    TagMapper tagMapper;

    @Override
    public List<Tag> findTopTags(Long userId) {
        return tagMapper.findTopTags(userId);
    }

    @Override
    public List<Tag> findAllTags(int pageNum,Long userId) {
        int n1=pageNum*3+1;
        return tagMapper.findAllTags(n1,userId);
    }

    @Override
    public void saveTag(String name,Long userId) {
        tagMapper.saveTag(name,userId);
    }

    @Override
    public void deleteTag(Long id) {
        tagMapper.deleteTag(id);
    }

    @Override
    public Tag getById(Long id) {
        return tagMapper.getById(id);
    }

    @Override
    public Tag getByName(String name,Long userId) {
        return tagMapper.getByName(name,userId);
    }

    @Override
    public void updateTag(Tag tag) {
           tagMapper.updateTag(tag);
    }
}
