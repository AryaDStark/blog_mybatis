package com.yangyu.service;

import com.yangyu.po.Tag;

import java.util.List;

public interface TagService {
    List<Tag> findTopTags(Long userId);
    List<Tag> findAllTags(int pageNum,Long userId);
    void      saveTag(String name,Long userId);
    void      deleteTag(Long id);
    Tag       getById(Long id);
    Tag       getByName(String name,Long userId);
    void      updateTag(Tag tag);
}
