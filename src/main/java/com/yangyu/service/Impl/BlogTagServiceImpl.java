package com.yangyu.service.Impl;

import com.yangyu.mapper.BlogTagMapper;
import com.yangyu.service.BlogTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Arya
 */
@Service
public class BlogTagServiceImpl implements BlogTagService {

    @Autowired
    BlogTagMapper blogTagMapper;

    @Override
    public int addBlogTag(Long blogId, Long tagId) {
        return blogTagMapper.addBlogTag(blogId,tagId);
    }

    @Override
    public int updateTag(Long tagId) {
        return blogTagMapper.updateTag(tagId);
    }

    @Override
    public int deleteBlogTag(Long id) {
        return blogTagMapper.deleteBlogTag(id);
    }
}
